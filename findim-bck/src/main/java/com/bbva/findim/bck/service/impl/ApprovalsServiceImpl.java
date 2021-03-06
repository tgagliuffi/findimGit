package com.bbva.findim.bck.service.impl;

import java.io.IOException;

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
import com.bbva.findim.bck.service.ApprovalsService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.util.PropertyUtilCnx;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.CalificacionConstant;
import com.bbva.findim.dom.CalificacionClienteBean;

@Service
public class ApprovalsServiceImpl extends BaseServiceBackImpl implements ApprovalsService {
	
	@Autowired
	private PropertyUtilCnx propertyUtilCnx;
	@Override
	public CalificacionClienteBean evaluarCliente(String tipoDocumento, String numDocumento, String tSec)throws Exception {
		String cadenaRptaError = "";
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		String url = propertyUtilCnx.getString(CalificacionConstant.CODIGO_URL_APPROVALS).toString();
		String numKey = propertyUtilCnx.getString(CalificacionConstant.CODIGO_NUMID_APPROVALS).toString();
		String typeKey = propertyUtilCnx.getString(CalificacionConstant.CODIGO_TYPEID_APPROVALS).toString();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	
		if(StringUtils.isNotBlank(tipoDocumento)){
			params.add(typeKey, tipoDocumento);
		}
		if(StringUtils.isNotBlank(numKey)){
			params.add(numKey, numDocumento);
		}
		
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();	
		ResponseEntity<com.bbva.findim.bck.domain.creditApprovals.Calificacion> responseEntity = null;
		CalificacionClienteBean salida = new CalificacionClienteBean();
		try {
			responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, new HttpEntity<String>(headers), com.bbva.findim.bck.domain.creditApprovals.Calificacion.class);
			com.bbva.findim.bck.domain.creditApprovals.Calificacion approvalsResult = responseEntity.getBody();

			salida.setTipoRespuesta(CalificacionConstant.TIPO_RESPUESTA_OK);
				salida.setNuNumeroDocumento(numDocumento);
				salida.setTpTipoDocumento(tipoDocumento);
				if(approvalsResult!=null){
					if(approvalsResult.getData().getQualification()!=null && !approvalsResult.getData().getQualification().equals("")){
						salida.setIdResultado(approvalsResult.getData().getQualification().getResult());
						salida.setDsResultado(approvalsResult.getData().getQualification().getReason());
						if(salida.getIdResultado().equals(CalificacionConstant.APROBADO)){
							salida.setCodigoResultado(CalificacionConstant.CODIGO_RESULTADO_APROBADO);
							salida.setTituloMostrar("Aprobado");
						}else if(salida.getIdResultado().equals(CalificacionConstant.RECHAZADO) || salida.getIdResultado().equals(CalificacionConstant.REJECTED)){
							salida.setCodigoResultado(CalificacionConstant.CODIGO_RESULTADO_RECHAZADO);
							salida.setTituloMostrar("Rechazado");
						}
					}
				}else{//cliente no existe provisional
					salida.setCodigoResultado(CalificacionConstant.CODIGO_RESULTADO_RECHAZADO);
//					salida.setTituloMostrar("Cliente no existe en tabla personas");
					salida.setTituloMostrar("Rechazado");
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
			if(!cadenaRptaError.equals("")){
				ObjectMapper mapper = new ObjectMapper();
				ErrorService obj = null;
				try {
					if(!cadenaRptaError.equals("")){
						obj = mapper.readValue(new ErrorService().toString(cadenaRptaError), ErrorService.class);
						salida.setDescErrorServicio(obj.getSystemErrorCause());
						salida.setTituloMostrar(obj.getSystemErrorCause());
					}else{
						salida.setDescErrorServicio("Sucedio un Error inesperado.");
					}
					
				} catch (JsonParseException eA) {
					LOGGER.info("\t"+ "\t"+"\t" + eA.getStackTrace());
					salida.setDescErrorServicio("Sucedio un Error inesperado.");
				} catch (JsonMappingException eB) {
					LOGGER.info("\t"+ "\t"+"\t" + eB.getStackTrace());
					salida.setDescErrorServicio("Sucedio un Error inesperado.");
				} catch (IOException eC) {
					LOGGER.info("\t"+ "\t"+"\t" + eC.getStackTrace());
					salida.setDescErrorServicio("Sucedio un Error inesperado.");
				}
			}	

		
		}
		
		return salida;
	}

}
