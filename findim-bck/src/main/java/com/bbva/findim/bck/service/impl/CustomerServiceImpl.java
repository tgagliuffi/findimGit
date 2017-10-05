package com.bbva.findim.bck.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bbva.findim.bck.domain.communs.ErrorService;
import com.bbva.findim.bck.domain.communs.Status;
import com.bbva.findim.bck.domain.communs.Term;
import com.bbva.findim.bck.domain.communs.Value;
import com.bbva.findim.bck.domain.customers.Address;
import com.bbva.findim.bck.domain.customers.BirthData;
import com.bbva.findim.bck.domain.customers.ContactDetail;
import com.bbva.findim.bck.domain.customers.ContactType;
import com.bbva.findim.bck.domain.customers.Country;
import com.bbva.findim.bck.domain.customers.Customer;
import com.bbva.findim.bck.domain.customers.CustomerResult;
import com.bbva.findim.bck.domain.customers.DocumentType;
import com.bbva.findim.bck.domain.customers.EconomicData;
import com.bbva.findim.bck.domain.customers.Gender;
import com.bbva.findim.bck.domain.customers.GeographicGroup;
import com.bbva.findim.bck.domain.customers.GeographicGroupType;
import com.bbva.findim.bck.domain.customers.IdentityDocument;
import com.bbva.findim.bck.domain.customers.Income;
import com.bbva.findim.bck.domain.customers.IncomeType;
import com.bbva.findim.bck.domain.customers.IncomeValue;
import com.bbva.findim.bck.domain.customers.Location;
import com.bbva.findim.bck.domain.customers.MaritalStatus;
import com.bbva.findim.bck.domain.customers.Nationality;
import com.bbva.findim.bck.domain.customers.OccupationType;
import com.bbva.findim.bck.domain.customers.PersonalTitle;
import com.bbva.findim.bck.domain.customers.Residence;
import com.bbva.findim.bck.domain.customers.ResidenceType;
import com.bbva.findim.bck.domain.customers.WorkPlace;
import com.bbva.findim.bck.service.CustomerService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.util.ConstantesConection;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.ClienteConstant;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.GENERAL;
import com.bbva.findim.bck.util.PropertyUtilCnx;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.DireccionClienteBean;
import com.bbva.findim.dom.DireccionDetalleclienteBean;
import com.bbva.findim.dom.DocumentoIdentidadBean;
import com.bbva.findim.dom.GrupoGeografico;
import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.RespuestaService;
import com.bbva.findim.dom.UbicacionDireccionBean;
import com.bbva.findim.dom.common.ConstantResponseMessage;

@Service
public class CustomerServiceImpl extends BaseServiceBackImpl implements CustomerService {	
	
	@Autowired
	private PropertyUtilCnx propertyUtilCnx;
	
	public ClienteBean obtenerDatosCliente(String tSec, String tipoDocumento, String numeroDocumento, List<ParametroBean> lstIndicadoresVia) throws ParseException {
		String cadenaRptaError = "";
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		ClienteBean clienteBean = new ClienteBean();
		
		String url = propertyUtilCnx.getString(ClienteConstant.CODIGO_URL_CUSTOMERS_LIST).toString();
		String tipoDoiKey = propertyUtilCnx.getString(ClienteConstant.CODIGO_TIPDOI_CUSTOMERS_KEY).toString();
		String numDoiKey = propertyUtilCnx.getString(ClienteConstant.CODIGO_NUMDOI_CUSTOMERS_KEY).toString();
		
		MultiValueMap<String, String> params = null;
		
		if(StringUtils.isNotBlank(tipoDocumento) && StringUtils.isNotBlank(numeroDocumento) ){
			params = new LinkedMultiValueMap<String, String>();
			if(StringUtils.isNotBlank(numeroDocumento) && StringUtils.isNotBlank(tipoDocumento)){
				params.add(numDoiKey, numeroDocumento);
				params.add(tipoDoiKey, tipoDocumento);
				params.add("expands","addresses,contactDetails,indicators,terms,economicData,classifications");
			}
		}
		
		CustomerResult customerResult = null;
		UriComponents uriComponents = null;
		try {
			uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();	
			ResponseEntity<CustomerResult> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, new HttpEntity<String>(headers), CustomerResult.class);
			customerResult = responseEntity.getBody();	
			
			if(customerResult!=null){
				if(customerResult.getData()!=null){
					for (int i = 0; i < customerResult.getData().size(); i++) {
						clienteBean = new ClienteBean();
						clienteBean.setRespuestaService(new RespuestaService());
						clienteBean.getRespuestaService().setExitoCode(ConstantResponseMessage.CODE_RPTA_OK);
						clienteBean.getRespuestaService().setExitoDescription(ConstantResponseMessage.MSJ_OK_CUSTOMER);
						
						clienteBean.setIdCliente(Integer.parseInt(customerResult.getData().get(i).getCustomerId()));
						clienteBean.setApellidoPaterno(customerResult.getData().get(i).getLastName());
						clienteBean.setPrimerNombre(customerResult.getData().get(i).getFirstName());
						clienteBean.setApellidoMaterno(customerResult.getData().get(i).getSurnames());
						clienteBean.setNombreCompleto(customerResult.getData().get(i).getLastName() + " " + customerResult.getData().get(i).getFirstName() + " " + customerResult.getData().get(i).getSurnames());
						
						if(customerResult.getData().get(i).getIdentityDocuments().size()>0){ 
							clienteBean.setLstDocumentoIdentidadBean(new ArrayList<DocumentoIdentidadBean>());
							for (int j = 0; j < customerResult.getData().get(i).getIdentityDocuments().size(); j++) {
								DocumentoIdentidadBean documentoIdentidadBean = new DocumentoIdentidadBean();
								documentoIdentidadBean.setNroDocumento(customerResult.getData().get(i).getIdentityDocuments().get(j).getDocumentNumber());
								documentoIdentidadBean.setTipoDocumentoIdentidad(customerResult.getData().get(i).getIdentityDocuments().get(j).getDocumentType().getId());
								//documentoIdentidadBean.setFechaExpiracion(UtilDate.convertStringToSqlDate(customerResult.getData().get(i).getIdentityDocuments().get(j).getExpirationDate(), "yyyy-MM-dd"));
								documentoIdentidadBean.setFechaExpiracion(customerResult.getData().get(i).getIdentityDocuments().get(j).getExpirationDate());
								clienteBean.getLstDocumentoIdentidadBean().add(documentoIdentidadBean);
							}
							clienteBean.setFechaNacimiento(customerResult.getData().get(i).getBirthData().getBirthDate());
						}
						if(customerResult.getData().get(i).getAddresses().get(0).getLocation().getGeographicGroups().size()>0){ 
							//TODO: considerar como lista
							clienteBean.setLstGrupoGeografico(new ArrayList<GrupoGeografico>());
							List<GeographicGroup> listGeographicgroup = customerResult.getData().get(i).getAddresses().get(0).getLocation().getGeographicGroups();
								for (GeographicGroup geographicGroup : listGeographicgroup) {
									GrupoGeografico grupoGeografico = new GrupoGeografico();
									grupoGeografico.setId(geographicGroup.getGeographicGroupType().getId());
									grupoGeografico.setCode(geographicGroup.getCode());
									grupoGeografico.setNombre(geographicGroup.getName());
									clienteBean.getLstGrupoGeografico().add(grupoGeografico);
								}

						}
						
					    clienteBean.setGenero(
					    		determinarGenero(customerResult.getData().get(i).getGender().getId())!=null?
					    		determinarGenero(customerResult.getData().get(i).getGender().getId()).getId():"");
					    
						clienteBean.setEstadoCivil(Integer.parseInt(determinarEstadoCivilFront(customerResult.getData().get(i).getMaritalStatus().getId()).getId()));
						
						if(customerResult.getData().get(i).getBirthData()!=null){
							clienteBean.setCodPaisOrigen(customerResult.getData().get(i).getBirthData().getCountry()!=null?customerResult.getData().get(i).getBirthData().getCountry().getId():null);
							//clienteBean.setFechaNacimiento(UtilDate.convertStringToSqlDate(customerResult.getData().get(i).getBirthData().getBirthDate(),"yyyy-MM-dd"));
							clienteBean.setFechaNacimiento(customerResult.getData().get(i).getBirthData().getBirthDate());
						}
						
						if(customerResult.getData().get(i).getNationalities().size()>0){// TODO: considerar como lista
							for (int j = 0; j < customerResult.getData().get(i).getNationalities().size(); j++) {
								clienteBean.setCodNacionalidad(customerResult.getData().get(i).getNationalities().get(j).getId());
							}
						}
						
						if(customerResult.getData().get(i).getEconomicData()!=null){
							if( customerResult.getData().get(i).getEconomicData().getOccupationType()!=null)
								clienteBean.setIdTipoOcupacion(customerResult.getData().get(i).getEconomicData().getOccupationType().getId());
							if( customerResult.getData().get(i).getEconomicData().getWorkPlace()!=null)
								clienteBean.setCentroTrabajo(customerResult.getData().get(i).getEconomicData().getWorkPlace().getName());

						}
						
						if(customerResult.getData().get(i).getAddresses()!=null){
							clienteBean.setDireccionCliente(new DireccionClienteBean());
							for (int j = 0; j < customerResult.getData().get(i).getAddresses().size(); j++) {
								clienteBean.getDireccionCliente().setIdDireccion(customerResult.getData().get(i).getAddresses().get(j).getAddressId());
								if(customerResult.getData().get(i).getAddresses().get(j).getAddressType()!=null){
									clienteBean.getDireccionCliente().setIdTipoDireccion(customerResult.getData().get(i).getAddresses().get(j).getAddressType().getId());
									clienteBean.getDireccionCliente().setNbTipoDireccion(customerResult.getData().get(i).getAddresses().get(j).getAddressType().getName());
								}
								if(customerResult.getData().get(i).getAddresses().get(i).getOwnershipType()!=null){
									clienteBean.getDireccionCliente().setIdTipoPropiedad(customerResult.getData().get(i).getAddresses().get(j).getOwnershipType().getId());
									clienteBean.getDireccionCliente().setNbTipoPropiedad(customerResult.getData().get(i).getAddresses().get(j).getOwnershipType().getName());
								}
								
								if(customerResult.getData().get(i).getAddresses().get(i).getLocation()!=null){
									clienteBean.getDireccionCliente().setUbicacion(new UbicacionDireccionBean());
									clienteBean.getDireccionCliente().getUbicacion().setDsReferencia(customerResult.getData().get(i).getAddresses().get(i).getLocation().getAdditionalInformation());

									if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getCountry()!=null){
										clienteBean.getDireccionCliente().getUbicacion().setNbPais(customerResult.getData().get(i).getAddresses().get(i).getLocation().getCountry().getName());
									}
									String direccionCompleta = GENERAL.STR_VACIO;	
									if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups()!=null){	
										clienteBean.getDireccionCliente().getUbicacion().setLstDetalleDireccion(new ArrayList<DireccionDetalleclienteBean>());
										boolean esAnexoDireccion = false;
									
											for (int j2 = 0; j2 < customerResult.getData().get(i).getAddresses().get(j).getLocation().getGeographicGroups().size(); j2++) {
												DireccionDetalleclienteBean direccionDetalle = new DireccionDetalleclienteBean();
												esAnexoDireccion = false;
												
												if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getCode()!=null){
													direccionDetalle.setIdCodeGrupoGeo(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getCode());
												}else{
													esAnexoDireccion = true;
												}
												if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType()!=null){
													if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getId().equals(GENERAL.DEPARTMENT))
														direccionDetalle.setNbDepartamento(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getName());
													else if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getId().equals(GENERAL.PROVINCE)) 
														direccionDetalle.setNbDepartamento(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getName());
													else if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getId().equals(GENERAL.DISTRICT)) 
														direccionDetalle.setNbDepartamento(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getName());
													else{
														direccionDetalle.setIdTipoGrupoGeo(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getId());
														direccionDetalle.setNbTipoGrupoGeo(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getName());		
													}
													if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getId().equals(GENERAL.COD_UBIGEO)){
														direccionDetalle.setUbigeo(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getName());
													}
													if(esAnexoDireccion)
														direccionCompleta = (direccionCompleta!=null?direccionCompleta:GENERAL.STR_VACIO)  + 
																			 (customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getName()!=null?customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getName():GENERAL.STR_VACIO)+		
																			 GENERAL.STR_ESPACIO;
												}
												if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getName()!=null){
													direccionDetalle.setNbGrupoGeo(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getName());
													if(esAnexoDireccion)
														direccionCompleta = (direccionCompleta!=null?direccionCompleta:GENERAL.STR_VACIO)  + customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getName() + GENERAL.STR_ESPACIO;
		
												}
											
												direccionDetalle.setVwDescripcion(direccionCompleta);
												clienteBean.getDireccionCliente().getUbicacion().getLstDetalleDireccion().add(direccionDetalle);
												}
											clienteBean.getDireccionCliente().getUbicacion().setDsDireccionCompleta(direccionCompleta(clienteBean.getDireccionCliente().getUbicacion(), lstIndicadoresVia));
									}
								}
							}
						}
					}
				}
			}		
		} catch (HttpClientErrorException e) {
			LOGGER.info("\t"+ "\t"+"\t" + e.getResponseHeaders().values());
		 	cadenaRptaError = e.getResponseBodyAsString();
		}catch (HttpServerErrorException e2) {
			LOGGER.info("\t"+ "\t"+"\t" + e2.getResponseHeaders().values());
			cadenaRptaError = e2.getResponseBodyAsString();
		}catch (Exception e3) {
			LOGGER.info("\t"+ "\t"+"\t" + "ERROR" , e3);
		}finally {
			ObjectMapper mapper = new ObjectMapper();
			ErrorService error = null;
			try {
				if(!cadenaRptaError.equals("")){
					error = mapper.readValue(new ErrorService().toString(cadenaRptaError), ErrorService.class);
					clienteBean.setRespuestaService(new RespuestaService());
					clienteBean.getRespuestaService().setErrorCode(error.getErrorCode());
					clienteBean.getRespuestaService().setErrorDescription(error.getSystemErrorCause());
					return clienteBean;
				}
			} catch (JsonParseException eA) {
				LOGGER.info("\t"+ "\t"+"\t" + eA.getStackTrace());
				clienteBean.setRespuestaService(new RespuestaService());
				clienteBean.getRespuestaService().setErrorCode(ConstantResponseMessage.CODE_RPTA_ERROR);
				clienteBean.getRespuestaService().setErrorDescription(ConstantResponseMessage.CODE_RPTA_ERROR_DESCRIP);
				return clienteBean;
			} catch (JsonMappingException eB) {
				LOGGER.info("\t"+ "\t"+"\t" + eB.getStackTrace());
				clienteBean.setRespuestaService(new RespuestaService());
				clienteBean.getRespuestaService().setErrorCode(ConstantResponseMessage.CODE_RPTA_ERROR);
				clienteBean.getRespuestaService().setErrorDescription(ConstantResponseMessage.CODE_RPTA_ERROR_DESCRIP);
				return clienteBean;
			} catch (IOException eC) {
				LOGGER.info("\t"+ "\t"+"\t" + eC.getStackTrace());
				clienteBean.setRespuestaService(new RespuestaService());
				clienteBean.getRespuestaService().setErrorCode(ConstantResponseMessage.CODE_RPTA_ERROR);
				clienteBean.getRespuestaService().setErrorDescription(ConstantResponseMessage.CODE_RPTA_ERROR_DESCRIP);
				return clienteBean;
			}
		}
		return clienteBean;
	}
	
	@Override
	public ClienteBean altaCliente(String tSec, ClienteBean clienteAltaBean, Customer customerIn) {

		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		String cadenaRptaError = null;
		try {
			
			Customer customerBack = new Customer();
			if(clienteAltaBean!=null){
				customerBack = mapperCopyCustomerForClienteBean(customerBack, clienteAltaBean);
			}else if(customerIn!=null){
				customerBack=customerIn;
			}
			ObjectMapper mapper = new ObjectMapper();
			   //Object to JSON in file
			mapper.writeValue(new File("D:\\file.json"), customerBack);
			//Object to JSON in String
			@SuppressWarnings("unused")
			String jsonInString = mapper.writeValueAsString(customerBack);
			
			String url = propertyUtilCnx.getString(ClienteConstant.CODIGO_URL_CUSTOMERS_CREATE).toString();
			UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).build();		
			HttpEntity<Customer> entity = new HttpEntity<Customer>(customerBack, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, String.class);
			clienteAltaBean.getRespuestaService().setExitoDescription(responseEntity.getHeaders().get("ResponseWarningDescription").toString());
		
		} catch (HttpClientErrorException e) {
			LOGGER.info("\t"+ "\t"+"\t" + e.getResponseHeaders().values());
		 	cadenaRptaError = e.getResponseBodyAsString();
		}catch (HttpServerErrorException e2) {
			LOGGER.info("\t"+ "\t"+"\t" + e2.getResponseHeaders().values());
			cadenaRptaError = e2.getResponseBodyAsString();
		}catch (Exception e3) {
			LOGGER.info("\t"+ "\t"+"\t" + "ERROR" , e3);
		}finally {
			ObjectMapper mapper = new ObjectMapper();
			ErrorService error = null;
			try {
				if(cadenaRptaError!=null){
					error = mapper.readValue(new ErrorService().toString(cadenaRptaError), ErrorService.class);
					clienteAltaBean.setRespuestaService(new RespuestaService());
					clienteAltaBean.getRespuestaService().setErrorCode(error.getErrorCode());
					clienteAltaBean.getRespuestaService().setErrorDescription(error.getSystemErrorCause());
					return clienteAltaBean;
				}
			} catch (JsonParseException eA) {
				LOGGER.info("\t"+ "\t"+"\t" + eA.getStackTrace());
				clienteAltaBean.setRespuestaService(new RespuestaService());
				clienteAltaBean.getRespuestaService().setErrorCode(ConstantResponseMessage.CODE_RPTA_ERROR);
				clienteAltaBean.getRespuestaService().setErrorDescription(ConstantResponseMessage.CODE_RPTA_ERROR_DESCRIP);
				return clienteAltaBean;
			} catch (JsonMappingException eB) {
				LOGGER.info("\t"+ "\t"+"\t" + eB.getStackTrace());
				clienteAltaBean.setRespuestaService(new RespuestaService());
				clienteAltaBean.getRespuestaService().setErrorCode(ConstantResponseMessage.CODE_RPTA_ERROR);
				clienteAltaBean.getRespuestaService().setErrorDescription(ConstantResponseMessage.CODE_RPTA_ERROR_DESCRIP);
				return clienteAltaBean;
			} catch (IOException eC) {
				LOGGER.info("\t"+ "\t"+"\t" + eC.getStackTrace());
				clienteAltaBean.setRespuestaService(new RespuestaService());
				clienteAltaBean.getRespuestaService().setErrorCode(ConstantResponseMessage.CODE_RPTA_ERROR);
				clienteAltaBean.getRespuestaService().setErrorDescription(ConstantResponseMessage.CODE_RPTA_ERROR_DESCRIP);
				return clienteAltaBean;
			}
		}
		return clienteAltaBean;
	}
	
	public Customer mapperCopyCustomerForClienteBean(Customer clienteBack, ClienteBean clienteNuevo) throws ParseException{
		
		clienteBack.setFirstName(StringUtils.abbreviate(clienteNuevo.getPrimerNombre(), 20));
		clienteBack.setLastName(StringUtils.abbreviate(clienteNuevo.getApellidoPaterno(), 20));
		clienteBack.setSurnames(StringUtils.abbreviate(clienteNuevo.getApellidoMaterno(), 20));
		
		BirthData nacimiento = new BirthData();
		nacimiento.setCountry(new Country());
		nacimiento.getCountry().setId(ConstantesConection.VALUE_PERU.getId());//TODO : VER SI ES CONSTANTE
		nacimiento.setBirthDate(clienteNuevo.getFechaNacimiento());
		clienteBack.setBirthData(nacimiento);
		
		//Lista de documentos
		if(clienteNuevo.getLstDocumentoIdentidadBean()!=null && clienteNuevo.getLstDocumentoIdentidadBean().size()>0){
			clienteBack.setIdentityDocuments(new ArrayList<IdentityDocument>());
			for (int i = 0; i < clienteNuevo.getLstDocumentoIdentidadBean().size(); i++) {
				IdentityDocument identityDocuments = new IdentityDocument();
				identityDocuments.setDocumentType(new DocumentType());
				identityDocuments.getDocumentType().setId(clienteNuevo.getLstDocumentoIdentidadBean().get(i).getTipoDocumentoIdentidad());
				identityDocuments.setStatus(new Status());
				identityDocuments.getStatus().setId(clienteNuevo.getLstDocumentoIdentidadBean().get(i).getEstado());
				identityDocuments.setCountry(new Country());
				identityDocuments.getCountry().setId(clienteNuevo.getLstDocumentoIdentidadBean().get(i).getPais());
				identityDocuments.setDocumentNumber(clienteNuevo.getLstDocumentoIdentidadBean().get(i).getNroDocumento());
				identityDocuments.setExpirationDate(clienteNuevo.getLstDocumentoIdentidadBean().get(i).getFechaExpiracion());
//			identityDocuments.setExpirationDate("2019-01-01");
				clienteBack.getIdentityDocuments().add(identityDocuments);
			}
		}
		
		clienteBack.setGender(new Gender());
		clienteBack.getGender().setId(determinarGenero(clienteNuevo.getGenero()).getId());
		clienteBack.setMaritalStatus(new MaritalStatus());
		clienteBack.getMaritalStatus().setId(clienteNuevo.getEstadoCivil()+"");
		clienteBack.getMaritalStatus().setId(determinarEstadoCivilBack(clienteBack).getId());
		clienteBack.setPersonalTitle(new PersonalTitle());
		clienteBack.getPersonalTitle().setId(clienteNuevo.getTituloCliente());
		clienteBack.getPersonalTitle().setId(determinarTitle(clienteBack).getValue());

		if(clienteNuevo.getNacionalidades()!=null && clienteNuevo.getNacionalidades().size()>0){
			clienteBack.setNationalities(new ArrayList<Nationality>());
			for (int i = 0; i < clienteNuevo.getNacionalidades().size(); i++) {
				Nationality nationality = new Nationality();
				nationality.setId(clienteNuevo.getNacionalidades().get(i));
				clienteBack.getNationalities().add(nationality);
			}
		}
		
		clienteBack.setResidence(new Residence());
		clienteBack.getResidence().setCountry(new Country());
		clienteBack.getResidence().getCountry().setId(ConstantesConection.VALUE_PERU.getId());
		clienteBack.getResidence().setResidenceType(new ResidenceType());
		clienteBack.getResidence().getResidenceType().setId(CustomerService.Residencia.PERMANENTE.getTipo());//TODO : validar si es constante
	
		String  idUrbano  = "EXTERIOR_NUMBER";
		if(esUrbano(clienteNuevo.getDireccion(), idUrbano)){
			clienteBack.setAddresses(setDireccionCliente(clienteNuevo, true));
		}else{
			clienteBack.setAddresses(setDireccionCliente(clienteNuevo, false));
		}
		
		if(clienteNuevo.getLstContacto()!=null && clienteNuevo.getLstContacto().size()>0){
			clienteBack.setContactDetails(new ArrayList<ContactDetail>());
			for (int j = 0; j < clienteNuevo.getLstContacto().size(); j++) {
				ContactDetail contactDetail = new ContactDetail();
				if(clienteNuevo.getLstContacto().get(j).getDtContacto()!=null)
					contactDetail.setContact(clienteNuevo.getLstContacto().get(j).getDtContacto());
				
				if(clienteNuevo.getLstContacto().get(j).getTpContato()!=null){
					contactDetail.setContactType(new ContactType());
					contactDetail.getContactType().setId(clienteNuevo.getLstContacto().get(j).getTpContato());
				}
				if(clienteNuevo.getLstContacto().get(j).getPaisContacto()!=null){
					contactDetail.setCountry(new Country());
					contactDetail.getCountry().setId(clienteNuevo.getLstContacto().get(j).getPaisContacto());
				}
				clienteBack.getContactDetails().add(contactDetail);
			}
			
		}
		
		//Set EconomicData
		if(clienteNuevo.getDatosLaboralesBean()!=null){
			clienteBack.setEconomicData(new EconomicData());
			if(clienteNuevo.getDatosLaboralesBean().getCentroLaboral()!=null){
				clienteBack.getEconomicData().setWorkPlace(new WorkPlace());
				clienteBack.getEconomicData().getWorkPlace().setName(clienteNuevo.getDatosLaboralesBean().getCentroLaboral());
			}
			if(clienteNuevo.getDatosLaboralesBean().getIdOcupacion()!=null){
				clienteBack.getEconomicData().setOccupationType(new OccupationType());
				clienteBack.getEconomicData().getOccupationType().setId(clienteNuevo.getDatosLaboralesBean().getIdOcupacion());
			}
			if(clienteNuevo.getDatosLaboralesBean().getLstIngresos()!=null && clienteNuevo.getDatosLaboralesBean().getLstIngresos().size()>0){
				clienteBack.getEconomicData().setIncomes(new ArrayList<Income>());
				for (int i = 0; i < clienteNuevo.getDatosLaboralesBean().getLstIngresos().size(); i++) {
					Income income = new Income();
					if(clienteNuevo.getDatosLaboralesBean().getLstIngresos().get(i).getIdTipoIngreso()!=null){
						income.setIncomeType(new IncomeType());
						income.getIncomeType().setId(clienteNuevo.getDatosLaboralesBean().getLstIngresos().get(i).getIdTipoIngreso());
					}
					if(clienteNuevo.getDatosLaboralesBean().getLstIngresos().get(i).getLstValorIngreso()!=null &&
					   clienteNuevo.getDatosLaboralesBean().getLstIngresos().get(i).getLstValorIngreso().size()>0){
						income.setIncomeValues(new ArrayList<IncomeValue>());
						for (int j = 0; j < clienteNuevo.getDatosLaboralesBean().getLstIngresos().get(i).getLstValorIngreso().size(); j++) {
							IncomeValue incomeValue = new IncomeValue();
							incomeValue.setAmount(clienteNuevo.getDatosLaboralesBean().getLstIngresos().get(i).getLstValorIngreso().get(j).getMtoIngreso());
							incomeValue.setCurrency(clienteNuevo.getDatosLaboralesBean().getLstIngresos().get(i).getLstValorIngreso().get(j).getMonedaIngreso());
							income.getIncomeValues().add(incomeValue);
						}
					}
					clienteBack.getEconomicData().getIncomes().add(income);
					
				}
			}
		}
		
		//Set condiciones 
		if(clienteNuevo.getLstCondiciones()!=null && clienteNuevo.getLstCondiciones().size()>0){
			clienteBack.setTerms(new ArrayList<Term>());
			for (int i = 0; i < clienteNuevo.getLstCondiciones().size(); i++) {
				Term term = new Term();
				term.setTermId(clienteNuevo.getLstCondiciones().get(i));
				clienteBack.getTerms().add(term);
			}
		}
		
		return clienteBack;
	}

	/*
	 * Estado Civil (Reniec) 1 - Soltero 2 - Casado 3 - Viudo 4 - Divorciado
	 */
	private Value determinarEstadoCivilBack(Customer cliente) {
		Value estadoCivil;
		if ("1".equals(cliente.getMaritalStatus().getId())) {
			estadoCivil = new Value(CustomerService.EstadoCivilBack.SOLTERO.getEstado());
		} else if ("2".equals(cliente.getMaritalStatus().getId())) {
			estadoCivil = new Value(CustomerService.EstadoCivilBack.CASADO.getEstado());
		} else if ("3".equals(cliente.getMaritalStatus().getId())) {
			estadoCivil = new Value(CustomerService.EstadoCivilBack.DIVORCIADO.getEstado());
		} else {// TODO En reniec existe Divorciado en BBVA no | En BBVA esiste
				// Conviviente en Reniec no. Pro defecto se pone Casado.
			estadoCivil = new Value(CustomerService.EstadoCivilBack.CASADO.getEstado());
		}
		return estadoCivil;
	}

	private Value determinarEstadoCivilFront(String value) {
		Value estadoCivil;
		if (value.equals("SINGLE")) {
			estadoCivil = new Value(CustomerService.EstadoCivilFront.SOLTERO.getEstado());
		} else if (value.equals("MARRIED")) {
			estadoCivil = new Value(CustomerService.EstadoCivilFront.CASADO.getEstado());
		} else if (value.equals("COHABITANT")) {
			estadoCivil = new Value(CustomerService.EstadoCivilFront.CASADO.getEstado());
		}else if (value.equals("WIDOWED")) {
				estadoCivil = new Value(CustomerService.EstadoCivilFront.VIUDO.getEstado());
		}else {// TODO En reniec existe Divorciado en BBVA no | En BBVA esiste
				// Conviviente en Reniec no. Pro defecto se pone Casado.
			estadoCivil = new Value(CustomerService.EstadoCivilFront.CASADO.getEstado());
		}
		return estadoCivil;
	}
	
	/*Genero (Reniec) 1 = Masculino 2 = Femenino */
	private Value determinarGenero(String generoCliente) {
		Value genero = null;
		if (CustomerService.GeneroFront.FEMALE.getDescipcion().equals(generoCliente)) {
			genero = new Value(CustomerService.Genero.MASCULINO.getDescipcion());
		}else if(CustomerService.GeneroFront.MALE.getDescipcion().equals(generoCliente)) {
			genero = new Value(CustomerService.Genero.FENEMINO.getDescipcion());
		}else if(CustomerService.GeneroFront.FEMALE.toString().equals(generoCliente)) {
			genero = new Value(CustomerService.GeneroFront.FEMALE.getDescipcion());
		}else if(CustomerService.GeneroFront.MALE.toString().equals(generoCliente)) {
			genero = new Value(CustomerService.GeneroFront.MALE.getDescipcion());
		}
		
		return genero;
	}
		
	private Value determinarTitle(Customer cliente) {
		Value title = new Value();
		if (CustomerService.Genero.MASCULINO.getDescipcion().equals(cliente.getGender().getId())) {
			title.setValue(CustomerService.TituloPersona.SR.getTitulo());
		} else if (CustomerService.Genero.FENEMINO.getDescipcion().equals(cliente.getGender().getId())) {
			if (CustomerService.EstadoCivilBack.SOLTERO.getEstado().equals(cliente.getMaritalStatus().getId())) {
				title.setValue(CustomerService.TituloPersona.SRTA.getTitulo());
			} else if (CustomerService.EstadoCivilBack.CASADO.getEstado().equals(cliente.getMaritalStatus().getId())
					|| CustomerService.EstadoCivilBack.DIVORCIADO.getEstado().equals(cliente.getMaritalStatus().getId())) {
				title.setValue(CustomerService.TituloPersona.SRA.getTitulo());
			}
		}

		if (title.getValue() == null) {
			title.setValue(CustomerService.TituloPersona.SR_A.getTitulo());
		}

		return title;
	}
	
	public String traducirDireccion(String alias, List<ParametroBean> lstIndicadoresVia) {
		String rpta = GENERAL.STR_VACIO;
		for (int i = 0; i < lstIndicadoresVia.size(); i++) {
			if(lstIndicadoresVia.get(i).getNb_paramdetalle().equals(alias)) {
				rpta= lstIndicadoresVia.get(i).getNb_valorparamdeta();
				break;
			}
			 
		}
		return rpta;
	}
	
	
	public String direccionCompleta(UbicacionDireccionBean ubicacion, List<ParametroBean> lstIndicadoresVia){
		String[] arrCodigoVia = GENERAL.codigosTipoVia;
		String[] arrCogigoZona = GENERAL.codigosTipoZona;
		String strVia = "", strDesVia = "", strZona = "", strDesZona = "", strDesNumExt = "", strNumExt = "", strDesNumInt = "",strNumInt = "";
		if(ubicacion!=null){
			if(!ubicacion.getLstDetalleDireccion().isEmpty()){
				for (int i = 0; i < ubicacion.getLstDetalleDireccion().size(); i++) {
					DireccionDetalleclienteBean detalle = new DireccionDetalleclienteBean();
					detalle = ubicacion.getLstDetalleDireccion().get(i);
					
					if(detalle.getIdTipoGrupoGeo()!=null){
						if(esAnexoDireccion(arrCodigoVia, detalle.getIdTipoGrupoGeo())){
							strVia = traducirDireccion(detalle.getIdTipoGrupoGeo(), lstIndicadoresVia);
							strDesVia =  detalle.getNbGrupoGeo();
						}else if(esAnexoDireccion(arrCogigoZona, detalle.getIdTipoGrupoGeo())){
							strZona =  traducirDireccion(detalle.getIdTipoGrupoGeo(), lstIndicadoresVia);
							strDesZona = detalle.getNbGrupoGeo();
						}else if(detalle.getIdTipoGrupoGeo().equals("EXTERIOR_NUMBER")){
							strDesNumExt = "Nro Ext.";
							strNumExt  = detalle.getNbGrupoGeo()!=null?detalle.getNbGrupoGeo():GENERAL.STR_VACIO;
						}else if(detalle.getIdTipoGrupoGeo().equals("INTERIOR_NUMBER")){
							strDesNumInt = "Nro Int.";
							strNumInt  = detalle.getNbGrupoGeo()!=null?detalle.getNbGrupoGeo():GENERAL.STR_VACIO;
						}
					}
				}
				
			}
		}
		
		String direccion = 	(strDesVia.isEmpty()?GENERAL.STR_VACIO:strVia + GENERAL.STR_ESPACIO + strDesVia) + GENERAL.STR_ESPACIO +  
							(strNumExt.isEmpty()?GENERAL.STR_VACIO:strDesNumExt + GENERAL.STR_ESPACIO + strNumExt)  + GENERAL.STR_ESPACIO +
							(strNumInt.isEmpty()?GENERAL.STR_VACIO:strDesNumInt + GENERAL.STR_ESPACIO + strNumInt)  + GENERAL.STR_ESPACIO + 
							(strDesZona.isEmpty()?GENERAL.STR_VACIO:strZona + GENERAL.STR_ESPACIO + strDesZona) ;
		
		return direccion;
		
	}
	
	public Boolean esAnexoDireccion(String[] arreglo, String cod){
		for (int j = 0; j < arreglo.length; j++) {
			if(arreglo[j].equals(cod)){
				return true;
			}
		}
		return false;
	}
	
	public static List<Address> setDireccionCliente(ClienteBean cliente, Boolean esUrbano){
		String[] datosDireccion;
		List<Address> lstAddress = null;
		
		if(cliente.getDireccionCliente()!=null){
			if(cliente.getDireccionCliente().getStrDireccionInputTlf()!=null){
				lstAddress = new ArrayList<>();
			
				datosDireccion = cliente.getDireccionCliente().getStrDireccionInputTlf().split(Pattern.quote("(|)"));
				Address  addresses = new Address();
//				addresses.setAddressType(new AddressType());
//				addresses.getAddressType().setId(cliente.getDireccionCliente().getIdTipoDireccion());
//				addresses.setOwnershipType(new OwnershipType());
//				addresses.getOwnershipType().setId(cliente.getDireccionCliente().getIdTipoPropiedad());
//				
				if(cliente.getDireccionCliente().getUbicacion()!=null){
					addresses.setLocation(new Location());
					addresses.getLocation().setCountry(new Country());
					addresses.getLocation().getCountry().setId(cliente.getDireccionCliente().getUbicacion().getNbPais());
					
					if(cliente.getDireccionCliente().getUbicacion().getDsReferencia() !=null)
						addresses.getLocation().setAdditionalInformation(cliente.getDireccionCliente().getUbicacion().getDsReferencia());

					if(datosDireccion.length==8){
						addresses.getLocation().setGeographicGroups(new ArrayList<GeographicGroup>());
						GeographicGroup geographicGroup = null;
						geographicGroup = new GeographicGroup();
						geographicGroup.setGeographicGroupType(new GeographicGroupType());
						geographicGroup.getGeographicGroupType().setId("DEPARTMENT");
						geographicGroup.setCode(cliente.getDireccionCliente().getUbicacion().getDsUbigeo().substring(0,2));
						addresses.getLocation().getGeographicGroups().add(geographicGroup);
						geographicGroup = new GeographicGroup();
						geographicGroup.setGeographicGroupType(new GeographicGroupType());
						geographicGroup.getGeographicGroupType().setId("PROVINCE");
						geographicGroup.setCode(cliente.getDireccionCliente().getUbicacion().getDsUbigeo().substring(2,4));
						addresses.getLocation().getGeographicGroups().add(geographicGroup);
						geographicGroup = new GeographicGroup();
						geographicGroup.setGeographicGroupType(new GeographicGroupType());
						geographicGroup.getGeographicGroupType().setId("DISTRICT");
						geographicGroup.setCode(cliente.getDireccionCliente().getUbicacion().getDsUbigeo().substring(4));
						addresses.getLocation().getGeographicGroups().add(geographicGroup);
						Boolean esCompuesto = false;
						for (int i = 0; i < datosDireccion.length; i++) {
							if(datosDireccion[i]!=null) {
										 geographicGroup = new GeographicGroup();
										 geographicGroup.setGeographicGroupType(new GeographicGroupType());
										 if(esUrbano) {
											 if(datosDireccion[i].indexOf("INTERIOR_NUMBER")>-1) {
												 	String[] arregloRefInterna = datosDireccion[i].split(Pattern.quote("/"));
													geographicGroup.getGeographicGroupType().setId("INTERIOR_NUMBER");
													if(!arregloRefInterna[0].equals("INTERIOR_NUMBER ")) {
														geographicGroup.setName(arregloRefInterna[1].replaceAll("[^0-9]", "").trim());
													}else {
														geographicGroup.setName(arregloRefInterna[0].replaceAll("[^0-9]", "").trim());
													}
												}
												else if(datosDireccion[i].indexOf("EXTERIOR_NUMBER")>-1) {
													geographicGroup.getGeographicGroupType().setId("EXTERIOR_NUMBER");
													geographicGroup.setName(datosDireccion[i].replaceAll("[^0-9]", "").trim());
												}else {
													geographicGroup.getGeographicGroupType().setId(datosDireccion[i]);
													geographicGroup.setName(datosDireccion[i+1]);
													i++;
												}
										 }else {
											 String[] aregloNoUrbano = datosDireccion[3].split(Pattern.quote("/"));
											 if(datosDireccion[i].indexOf("BLOCK.")>-1 && !esCompuesto) {
												 if(aregloNoUrbano.length>=1) {
													geographicGroup.getGeographicGroupType().setId("BLOCK");
													geographicGroup.setName(aregloNoUrbano[0].replaceAll("BLOCK.", "").trim());
													esCompuesto=true;
													i--;
												 }
											}else if(datosDireccion[i].indexOf("LOT.")>-1 && esCompuesto) {
												 if(aregloNoUrbano.length>=1) {
													geographicGroup.getGeographicGroupType().setId("LOT");
													geographicGroup.setName(aregloNoUrbano[1].replaceAll("LOT.", "").trim());
												 }
											}else {
												    if(!datosDireccion[i].equals("")) {
												    	geographicGroup.getGeographicGroupType().setId(datosDireccion[i]);
												    	geographicGroup.setName(datosDireccion[i+1]);
												    	i++;
												    }
												}
										 }
										 
										 if(geographicGroup.getGeographicGroupType().getId()!=null) {
											 if(esUrbano && i!=3 && i!=4)
											 addresses.getLocation().getGeographicGroups().add(geographicGroup);
										 }
							}
						}
					}
				}
				lstAddress.add(addresses);
			}
		}	
		return lstAddress;
	}
	
	public static Boolean esUrbano(String  direccion,String idUrbano){
		if(direccion.indexOf(idUrbano)>-1){
			return true;
		}
		return false;
	}
	
}
