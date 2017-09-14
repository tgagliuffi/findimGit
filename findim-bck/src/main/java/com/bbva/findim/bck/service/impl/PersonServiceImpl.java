package com.bbva.findim.bck.service.impl;

import java.io.IOException;
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

import com.bbva.findim.bck.domain.communs.ErrorService;
import com.bbva.findim.bck.domain.person.Address;
import com.bbva.findim.bck.domain.person.ContactsInformation;
import com.bbva.findim.bck.domain.person.ExtendedData;
import com.bbva.findim.bck.domain.person.GeographicGroup;
import com.bbva.findim.bck.domain.person.MaritalStatus;
import com.bbva.findim.bck.domain.person.Person;
import com.bbva.findim.bck.service.PersonService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.util.PropertyUtilCnx;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.PersonaConstant;
import com.bbva.findim.dom.DireccionBean;
import com.bbva.findim.dom.GrupoGeografico;
import com.bbva.findim.dom.PersonaBean;
import com.bbva.findim.dom.RespuestaService;

@Service
public class PersonServiceImpl extends BaseServiceBackImpl  implements PersonService {
	@Autowired
	private PropertyUtilCnx propertyUtilCnx;
	
	public PersonaBean buscarNoCliente(String tipoDocumento, String nroDocumento, String sourceValue, String tSec)throws Exception {
		String cadenaRptaError = "";
		PersonaBean personaBean = new PersonaBean();
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
	
		String url = propertyUtilCnx.getString(PersonaConstant.CODIGO_URL_PERSON).toString();
		String tipoDoiKey = propertyUtilCnx.getString(PersonaConstant.CODIGO_TYPEID_PERSON).toString();
		String numDoiKey = propertyUtilCnx.getString(PersonaConstant.CODIGO_NUMID_PERSON ).toString();
		String source = propertyUtilCnx.getString(PersonaConstant.CODIGO_SOURCE_PERSON).toString();
		String filter = propertyUtilCnx.getString(PersonaConstant.CODIGO_PARAMETER_FILTER).toString();
		String filterValue = null;
		
		MultiValueMap<String, String> params = null;
		
		if(StringUtils.isNotBlank(tipoDocumento) && StringUtils.isNotBlank(nroDocumento) ){
			filterValue = numDoiKey+"=="+nroDocumento+";"+tipoDoiKey+"=="+tipoDocumento;
			if(filterValue!=null){
				params = new LinkedMultiValueMap<String, String>();
				params.add(filter, filterValue);
				params.add(source, sourceValue);
			}
		}
		
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();
		ResponseEntity<Person> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, new HttpEntity<String>(headers), Person.class);
			Person personResult = responseEntity.getBody();
			String ubigeo = "";	
					if(personResult!=null){
						personaBean = new PersonaBean();
						personaBean.setCodigoCentral(personResult.getId());
						personaBean.setNombres(personResult.getName());
						personaBean.setPaterno(personResult.getLastName());
						personaBean.setMaterno(personResult.getMothersLastName());
						personaBean.setNacimiento(personResult.getBirthday());
						List<GrupoGeografico> lstGrupoGeografico= new ArrayList<>();
						
						List<Address> adresses=personResult.getAddresses();
						DireccionBean direccion = null;
						for (Address address : adresses) {
							if(address.getGeographicGroup()!=null){
								direccion = new DireccionBean();
								direccion.setDsDireccion(address.getName());
								List<GeographicGroup> ggcodes=address.getGeographicGroup();
								for (GeographicGroup geographicGroup : ggcodes) {
									GrupoGeografico ubigeoCode=new GrupoGeografico();
									ubigeoCode.setCode(geographicGroup.getCode());
									ubigeo=ubigeo+ubigeoCode.getCode();
									lstGrupoGeografico.add(ubigeoCode);
								}
								
								direccion.setLstGrupoGeografico(lstGrupoGeografico);
								
							}
						}
						personaBean.setLstDirecciones(new ArrayList<DireccionBean>());
						personaBean.getLstDirecciones().add(direccion);
						personaBean.setDsUbigeoComplet(ubigeo);
						if(personResult.getExtendedData()!=null && !personResult.getExtendedData().equals(""))
							personaBean.setCodigoSexo(personResult.getExtendedData().getSex());
						if(personResult.getExtendedData()!=null && !personResult.getExtendedData().equals(""))
							personaBean.setEstadoCivil(personResult.getExtendedData().getMaritalStatus()!=null?personResult.getExtendedData().getMaritalStatus().getId():null);
						personaBean.setFhNacimiento(personResult.getBirthday());
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
					personaBean.setRespuestaService(new RespuestaService());
					personaBean.getRespuestaService().setErrorCode(error.getErrorCode());
					personaBean.getRespuestaService().setErrorDescription(error.getSystemErrorCause());
					return personaBean;
				}
			} catch (JsonParseException eA) {
				LOGGER.info("\t"+ "\t"+"\t" + eA.getStackTrace());
				personaBean.setRespuestaService(new RespuestaService());
				personaBean.getRespuestaService().setErrorCode("000");
				personaBean.getRespuestaService().setErrorDescription("Sucedio un Error inesperado.");
				return personaBean;
			} catch (JsonMappingException eB) {
				LOGGER.info("\t"+ "\t"+"\t" + eB.getStackTrace());
				personaBean.setRespuestaService(new RespuestaService());
				personaBean.getRespuestaService().setErrorCode("000");
				personaBean.getRespuestaService().setErrorDescription("Sucedio un Error inesperado.");
				return personaBean;
			} catch (IOException eC) {
				LOGGER.info("\t"+ "\t"+"\t" + eC.getStackTrace());
				personaBean.setRespuestaService(new RespuestaService());
				personaBean.getRespuestaService().setErrorCode("000");
				personaBean.getRespuestaService().setErrorDescription("Sucedio un Error inesperado.");
				return personaBean;
			}
		}
		return personaBean;
	}
	
	public PersonaBean altaNoCliente(PersonaBean personaBean, String tSec)throws Exception{
		String cadenaRptaError = "";
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		List<Address> lstAddress = null;
		String url = propertyUtilCnx.getString(PersonaConstant.CODIGO_URL_PERSON_MODIFY).toString();

		if(StringUtils.isNotBlank(personaBean.getNumeroDocumento()) && StringUtils.isNotBlank(personaBean.getTipoDocumento()) ){
			url = url + personaBean.getTipoDocumento() + personaBean.getNumeroDocumento();
		}
		
		Person personBack = new Person();
		personBack.setName(personaBean.getNombres());
		personBack.setLastName(personaBean.getPaterno());
		personBack.setBirthday(personaBean.getNacimiento());
		personBack.setMothersLastName(personaBean.getMaterno());
		personBack.setExtendedData(new ExtendedData());
		personBack.getExtendedData().setSex(personaBean.getCodigoSexo());
		personBack.getExtendedData().setMaritalStatus(new MaritalStatus());
		personBack.getExtendedData().getMaritalStatus().setId(personaBean.getCodigoEstadoCivil());
		
		if(personaBean.getLstDirecciones().size()>0){
			lstAddress = new ArrayList<Address>();
			for (int i = 0; i <personaBean.getLstDirecciones().size(); i++) {
				Address address = new Address();
				address.setName(personaBean.getLstDirecciones().get(i).getDsDireccion());
				address.setGeographicGroup(new ArrayList<GeographicGroup>());
				if(personaBean.getLstDirecciones().get(0).getLstGrupoGeografico().size()>0){
					int j=0;
					for (GrupoGeografico grupoGeografico : personaBean.getLstDirecciones().get(0).getLstGrupoGeografico()) {
						GeographicGroup gg = new GeographicGroup() ;
						gg.setCode(grupoGeografico.getCode());
						address.getGeographicGroup().add(j, gg);
						j++;
					}
				}
				lstAddress.add(address);
			}
			personBack.setAddresses(lstAddress);
			personBack.setContactsInformation(new ArrayList<ContactsInformation>());
			personBack.getContactsInformation().add(0, new ContactsInformation());
			personBack.getContactsInformation().get(0).setName("");
		}
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).build();		
		HttpEntity<Person> entity = new HttpEntity<Person>(personBack, headers);
		
		try {
			ResponseEntity<Person> respuesta = restTemplate.exchange(uriComponents.toUri(), HttpMethod.PUT, entity, Person.class);
			Person personGrabada = respuesta.getBody();
			if(respuesta.getBody()!=null){
				if(respuesta.getBody().getId()!=null && !respuesta.getBody().getId().equals("")){
					personaBean.setId(personGrabada.getId());
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
					personaBean.setRespuestaService(new RespuestaService());
					personaBean.getRespuestaService().setErrorCode(error.getErrorCode());
					personaBean.getRespuestaService().setErrorDescription(error.getSystemErrorCause());
					return personaBean;
				}
			} catch (JsonParseException eA) {
				LOGGER.info("\t"+ "\t"+"\t" + eA.getStackTrace());
				personaBean.setRespuestaService(new RespuestaService());
				personaBean.getRespuestaService().setErrorCode("000");
				personaBean.getRespuestaService().setErrorDescription("Sucedio un Error inesperado.");
				return personaBean;
			} catch (JsonMappingException eB) {
				LOGGER.info("\t"+ "\t"+"\t" + eB.getStackTrace());
				personaBean.setRespuestaService(new RespuestaService());
				personaBean.getRespuestaService().setErrorCode("000");
				personaBean.getRespuestaService().setErrorDescription("Sucedio un Error inesperado.");
				return personaBean;
			} catch (IOException eC) {
				LOGGER.info("\t"+ "\t"+"\t" + eC.getStackTrace());
				personaBean.setRespuestaService(new RespuestaService());
				personaBean.getRespuestaService().setErrorCode("000");
				personaBean.getRespuestaService().setErrorDescription("Sucedio un Error inesperado.");
				return personaBean;
			}
		}
		
		return personaBean;
	
	}
	

	
}
	
	

