package com.bbva.findim.bck.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

import com.bbva.findim.bck.configuration.domain.error.TestErrorHandler;
import com.bbva.findim.bck.domain.communs.ErrorService;
import com.bbva.findim.bck.domain.communs.Status;
import com.bbva.findim.bck.domain.communs.Term;
import com.bbva.findim.bck.domain.communs.Value;
import com.bbva.findim.bck.domain.customers.Address;
import com.bbva.findim.bck.domain.customers.AddressType;
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
import com.bbva.findim.bck.domain.customers.OwnershipType;
import com.bbva.findim.bck.domain.customers.PersonalTitle;
import com.bbva.findim.bck.domain.customers.Residence;
import com.bbva.findim.bck.domain.customers.ResidenceType;
import com.bbva.findim.bck.domain.customers.WorkPlace;
import com.bbva.findim.bck.service.CustomersService;
import com.bbva.findim.bck.service.ProposalService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.util.ConstantesConection;
import com.bbva.findim.bck.util.PropertyUtilCnx;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.ClienteConstant;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.GENERAL;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.DireccionBean;
import com.bbva.findim.dom.DireccionClienteBean;
import com.bbva.findim.dom.DireccionDetalleclienteBean;
import com.bbva.findim.dom.DocumentoIdentidadBean;
import com.bbva.findim.dom.GrupoGeografico;
import com.bbva.findim.dom.UbicacionDireccionBean;

@Service
public class CustomersServiceImpl extends BaseServiceBackImpl implements CustomersService {	
	
	@Autowired
	private PropertyUtilCnx propertyUtilCnx;
	
	public ClienteBean obtenerDatosCliente(String tSec, String tipoDocumento, String numeroDocumento) throws ParseException {
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
						clienteBean.setIdCliente(Integer.parseInt(customerResult.getData().get(i).getCustomerId()));
						clienteBean.setApellidoPaterno(customerResult.getData().get(i).getLastName());
						clienteBean.setPrimerNombre(customerResult.getData().get(i).getFirstName());
						clienteBean.setApellidoMaterno(customerResult.getData().get(i).getSurnames());
						
						if(customerResult.getData().get(i).getIdentityDocuments().size()>0){ //TODO: considerar como lista
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
									String direccionCompleta = GENERAL.ESPACIO;	
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
														direccionCompleta = direccionCompleta  + customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getGeographicGroupType().getName() + GENERAL.ESPACIO;
												}
												if(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getName()!=null){
													direccionDetalle.setNbGrupoGeo(customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getName());
													if(esAnexoDireccion)
														direccionCompleta = direccionCompleta  + customerResult.getData().get(i).getAddresses().get(i).getLocation().getGeographicGroups().get(j2).getName() + GENERAL.ESPACIO;
		
												}
											
												direccionDetalle.setVwDescripcion(direccionCompleta);
												clienteBean.getDireccionCliente().getUbicacion().getLstDetalleDireccion().add(direccionDetalle);
												}
											clienteBean.getDireccionCliente().getUbicacion().setDsDireccionCompleta(direccionCompleta(clienteBean.getDireccionCliente().getUbicacion()));
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
				ErrorService obj = null;
				
				try {
					if(!cadenaRptaError.equals("")){
						obj = mapper.readValue(new ErrorService().toString(cadenaRptaError), ErrorService.class);
						clienteBean.setDescError(obj.getSystemErrorCause());
					}
					
				} catch (JsonParseException eA) {
					LOGGER.info("\t"+ "\t"+"\t" + eA.getStackTrace());
					clienteBean.setDescError("Sucedio un Error inesperado.");
				} catch (JsonMappingException eB) {
					LOGGER.info("\t"+ "\t"+"\t" + eB.getStackTrace());
					clienteBean.setDescError("Sucedio un Error inesperado.");
				} catch (IOException eC) {
					LOGGER.info("\t"+ "\t"+"\t" + eC.getStackTrace());
					clienteBean.setDescError("Sucedio un Error inesperado.");
				}
	}

		return clienteBean;
	}
	
	@Override
	public String altaCliente(String tSec, ClienteBean clienteNuevo, Customer customerIn) {

		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		
		try {
			
			Customer customerBack = new Customer();
			if(clienteNuevo!=null){
				customerBack = mapperCopyCustomerForClienteBean(customerBack, clienteNuevo);
			}else if(customerIn!=null){
				customerBack=customerIn;
			}
			
			String url = propertyUtilCnx.getString(ClienteConstant.CODIGO_URL_CUSTOMERS_CREATE).toString();
			UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).build();		
			HttpEntity<Customer> entity = new HttpEntity<Customer>(customerBack, headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.POST, entity, String.class);
			return responseEntity.getHeaders().get("ResponseWarningDescription").toString();
		} catch (Exception e) {
			restTemplate.setErrorHandler(new TestErrorHandler());
			return "[SUCEDIO ERROR]";
			
		}

	}
	
	public Customer mapperCopyCustomerForClienteBean(Customer clienteBack, ClienteBean clienteNuevo) throws ParseException{
		
		clienteBack.setFirstName(StringUtils.abbreviate(clienteNuevo.getPrimerNombre(), 20));
		clienteBack.setLastName(StringUtils.abbreviate(clienteNuevo.getApellidoPaterno(), 20));
		clienteBack.setSurnames(StringUtils.abbreviate(clienteNuevo.getApellidoMaterno(), 20));
		
		BirthData nacimiento = new BirthData();
		nacimiento.setCountry(new Country());
		nacimiento.getCountry().setId(ConstantesConection.VALUE_PERU.getId());//TODO : VER SI ES CONSTANTE
//		nacimiento.setBirthDate(DateFormatUtils.format(clienteNuevo.getFechaNacimiento(), "yyyy-MM-dd"));
		nacimiento.setBirthDate("1990-01-01");
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
//				identityDocuments.setExpirationDate(DateFormatUtils.format(clienteNuevo.getLstDocumentoIdentidadBean().get(i).getFechaExpiracion(),"yyyy-MM-dd"));
				identityDocuments.setExpirationDate("2019-01-01");
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
		clienteBack.getResidence().getResidenceType().setId(CustomersService.Residencia.PERMANENTE.getTipo());//TODO : validar si es constante
	
		
		List<Address> lstAddress = null;
		if(clienteNuevo.getLstDireccionBean()!=null && clienteNuevo.getLstDireccionBean().size()>0){
			 lstAddress = new ArrayList<Address>();
			for (int i = 0; i < clienteNuevo.getLstDireccionBean().size(); i++) {
				Address  addresses = new Address();
				DireccionBean direccion = clienteNuevo.getLstDireccionBean().get(i);
				//Set TypeAddress
				addresses.setAddressType(new AddressType());
				addresses.getAddressType().setId(direccion.getIdTipoDireccion());
				//Set Owner
				addresses.setOwnershipType(new OwnershipType());
				addresses.getOwnershipType().setId(direccion.getIdTipoPropiedad());
				//Set Location
				if(direccion.getDsDireccion()!=null){
					addresses.setLocation(new Location());
					addresses.getLocation().setAddressName(direccion.getDsDireccion());
					if(direccion.getIdCountry()!=null){
						addresses.getLocation().setCountry(new Country());
						addresses.getLocation().getCountry().setId(direccion.getIdCountry());
					}
					if(direccion.getRfAdicionalInfo()!=null)
					addresses.getLocation().setAdditionalInformation(direccion.getRfAdicionalInfo());
				}
				//Set GeoGrafica
				if(direccion.getLstGrupoGeografico()!=null && direccion.getLstGrupoGeografico().size()>0){
					List<GeographicGroup> lstGeoGroup = new ArrayList<GeographicGroup>();
						for (int j = 0; j < direccion.getLstGrupoGeografico().size() ; j++) {
							GeographicGroup geographicGroup = new GeographicGroup();
							geographicGroup.setGeographicGroupType(new GeographicGroupType());
							geographicGroup.getGeographicGroupType().setId(determinarIndicadorDireccion(direccion.getLstGrupoGeografico().get(j).getId()));
							if(direccion.getLstGrupoGeografico().get(j).getNombre()!=null)
								geographicGroup.setName(direccion.getLstGrupoGeografico().get(j).getNombre());
							if(direccion.getLstGrupoGeografico().get(j).getCode()!=null){
								geographicGroup.setCode(direccion.getLstGrupoGeografico().get(j).getCode());
							}
							lstGeoGroup.add(geographicGroup);
						}
						//set grupo geografico
						addresses.getLocation().setGeographicGroups(lstGeoGroup);
				}
				//Agregamos un dirección
				lstAddress.add(addresses);
			}
		}
		//Agregamos la lista de direccion
		clienteBack.setAddresses(lstAddress);
		
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
			estadoCivil = new Value(CustomersService.EstadoCivilBack.SOLTERO.getEstado());
		} else if ("2".equals(cliente.getMaritalStatus().getId())) {
			estadoCivil = new Value(CustomersService.EstadoCivilBack.CASADO.getEstado());
		} else if ("3".equals(cliente.getMaritalStatus().getId())) {
			estadoCivil = new Value(CustomersService.EstadoCivilBack.DIVORCIADO.getEstado());
		} else {// TODO En reniec existe Divorciado en BBVA no | En BBVA esiste
				// Conviviente en Reniec no. Pro defecto se pone Casado.
			estadoCivil = new Value(CustomersService.EstadoCivilBack.CASADO.getEstado());
		}
		return estadoCivil;
	}

	private Value determinarEstadoCivilFront(String value) {
		Value estadoCivil;
		if (value.equals("SINGLE")) {
			estadoCivil = new Value(CustomersService.EstadoCivilFront.SOLTERO.getEstado());
		} else if (value.equals("MARRIED")) {
			estadoCivil = new Value(CustomersService.EstadoCivilFront.CASADO.getEstado());
		} else if (value.equals("COHABITANT")) {
			estadoCivil = new Value(CustomersService.EstadoCivilFront.CASADO.getEstado());
		}else if (value.equals("WIDOWED")) {
				estadoCivil = new Value(CustomersService.EstadoCivilFront.VIUDO.getEstado());
		}else {// TODO En reniec existe Divorciado en BBVA no | En BBVA esiste
				// Conviviente en Reniec no. Pro defecto se pone Casado.
			estadoCivil = new Value(CustomersService.EstadoCivilFront.CASADO.getEstado());
		}
		return estadoCivil;
	}
	
	/*
	 * Genero (Reniec) 1 = Masculino 2 = Femenino
	 */



	private Value determinarGenero(String generoCliente) {
		Value genero = null;
		if (CustomersService.GeneroFront.FEMALE.getDescipcion().equals(generoCliente)) {
			genero = new Value(CustomersService.Genero.MASCULINO.getDescipcion());

		}else if(CustomersService.GeneroFront.MALE.getDescipcion().equals(generoCliente)) {
			genero = new Value(CustomersService.Genero.FENEMINO.getDescipcion());
		}else if(CustomersService.GeneroFront.FEMALE.toString().equals(generoCliente)) {
			genero = new Value(CustomersService.GeneroFront.FEMALE.getDescipcion());
		}else if(CustomersService.GeneroFront.MALE.toString().equals(generoCliente)) {
			genero = new Value(CustomersService.GeneroFront.MALE.getDescipcion());
		}
		
		return genero;
	}
	
	private String determinarIndicadorDireccion(String idDireccion) {
		String direccion="";
		if (idDireccion.equals(ProposalService.IndicadorDireccion.AGR.name())) {
			direccion =  (ProposalService.IndicadorDireccion.AGR.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.AHH.name())) {
			direccion =  (ProposalService.IndicadorDireccion.AHH.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.ALM.name())) {
			direccion =  (ProposalService.IndicadorDireccion.ALM.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.AV.name())) {
			direccion =  (ProposalService.IndicadorDireccion.AV.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.BAJ.name())) {
			direccion =  (ProposalService.IndicadorDireccion.BAJ.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.CAL.name())) {
			direccion =  (ProposalService.IndicadorDireccion.CAL.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.CC.name())) {
			direccion =  (ProposalService.IndicadorDireccion.CC.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.CRT.name())) {
			direccion =  (ProposalService.IndicadorDireccion.CRT.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.GAL.name())) {
			direccion =  (ProposalService.IndicadorDireccion.GAL.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.JR.name())) {
			direccion =  (ProposalService.IndicadorDireccion.JR.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.MAL.name())) {
			direccion =  (ProposalService.IndicadorDireccion.MAL.getIndicadorDireccion());
		}else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.OVA.name())) {
			direccion =  (ProposalService.IndicadorDireccion.OVA.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.PAS.name())) {
			direccion =  (ProposalService.IndicadorDireccion.PAS.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.PLZ.name())) {
			direccion =  (ProposalService.IndicadorDireccion.PLZ.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.POR.name())) {
			direccion =  (ProposalService.IndicadorDireccion.POR.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.PQE.name())) {
			direccion =  (ProposalService.IndicadorDireccion.PQE.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.PRL.name())) {
			direccion =  (ProposalService.IndicadorDireccion.PRL.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.PSJ.name())) {
			direccion =  (ProposalService.IndicadorDireccion.PSJ.getIndicadorDireccion());
		} else
		if (idDireccion.equals(ProposalService.IndicadorDireccion.PTE.name())) {
			direccion =  (ProposalService.IndicadorDireccion.PTE.getIndicadorDireccion());
		} else{
			direccion=idDireccion;
		}
		return direccion;
	}
	
	private Value determinarTitle(Customer cliente) {
		Value title = new Value();

		if (CustomersService.Genero.MASCULINO.getDescipcion().equals(cliente.getGender().getId())) {
			title.setValue(CustomersService.TituloPersona.SR.getTitulo());
		} else if (CustomersService.Genero.FENEMINO.getDescipcion().equals(cliente.getGender().getId())) {
			if (CustomersService.EstadoCivilBack.SOLTERO.getEstado().equals(cliente.getMaritalStatus().getId())) {
				title.setValue(CustomersService.TituloPersona.SRTA.getTitulo());
			} else if (CustomersService.EstadoCivilBack.CASADO.getEstado().equals(cliente.getMaritalStatus().getId())
					|| CustomersService.EstadoCivilBack.DIVORCIADO.getEstado().equals(cliente.getMaritalStatus().getId())) {
				title.setValue(CustomersService.TituloPersona.SRA.getTitulo());
			}
		}

		if (title.getValue() == null) {
			title.setValue(CustomersService.TituloPersona.SR_A.getTitulo());
		}

		return title;
	}
	
	public String direccionCompleta(UbicacionDireccionBean ubicacion){
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
							strVia = detalle.getNbTipoGrupoGeo();
							strDesVia = detalle.getNbGrupoGeo();
						}else if(esAnexoDireccion(arrCogigoZona, detalle.getIdTipoGrupoGeo())){
							strZona =  detalle.getNbTipoGrupoGeo();
							strDesZona = detalle.getNbGrupoGeo();;
						}else if(detalle.getIdTipoGrupoGeo().equals("EXTERIOR_NUMBER")){
							strDesNumExt = "Nro Ext.";
							strNumExt  = detalle.getNbGrupoGeo();;
						}else if(detalle.getIdTipoGrupoGeo().equals("INTERIOR_NUMBER")){
							strDesNumInt = "Nro Int.";
							strNumInt  = detalle.getNbGrupoGeo();
						}
					}
				}
				
			}
		}
		
		String direccion = 	(strVia.isEmpty()?"":strVia) + " " + (strDesVia.isEmpty()?"":strDesVia) + " " +  
							(strDesNumExt.isEmpty()?"":strDesNumExt)+ " " + (strNumExt.isEmpty()?"":strNumExt) + " " + 
							(strDesNumInt.isEmpty()?"":strDesNumInt) + " " + (strNumInt.isEmpty()?"":strNumInt) + " " + 
							(strZona.isEmpty()?"":strZona) + " " + (strDesZona.isEmpty()?"":strDesZona);
		
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
}
