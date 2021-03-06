package com.bbva.findim.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.LogContratoBean;
import com.bbva.findim.web.common.Constantes;
import com.bbva.findim.dom.util.PropertyUtil;
import com.bbva.findim.web.service.ContratoRestService;
import com.bbva.findim.web.util.RestUtil;

@Service
public class ContratoRestServiceImpl implements ContratoRestService {

	private static final Logger logger = LogManager.getLogger(ContratoRestServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate = null;
	
	@Autowired
	private PropertyUtil prop;
	
	public ClienteBean obtenerDatosComplementarios(ClienteBean clienteBean) {
		String uri=null;
		try{	
			uri  = clienteBean.getRutaServicioRest()+"obtenerDatosComplementarios/"+String.valueOf(clienteBean.getIdTipoEnvio())+"/"+clienteBean.getIdTipoOcupacion()+"/"+clienteBean.getIdTipoModalidad();		
			Map<String, String> map = new HashMap<>();
	        ResponseEntity<ClienteBean> responseEntity = restTemplate.getForEntity(uri, ClienteBean.class, map);	        
	        clienteBean=responseEntity.getBody(); 
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		return clienteBean;
	}
	
	public ClienteBean busquedaContrato(ClienteBean clienteBean) {
		String uri = null;		
		try{
			logger.debug("Inicio");
			uri = clienteBean.getRutaServicioRest()
								+"busquedaContrato/"
								+String.valueOf(clienteBean.getTipoDocumento())
								+"/"+ clienteBean.getNumeroDocumento()
				  				+"/"+ clienteBean.getSeguridad_data()
								+"/"+ clienteBean.getSeguridad_user()
								+"/"+ clienteBean.getSeguridad_usertype()
								+"/"+ clienteBean.getSeguridad_channel()
								+"/"+ clienteBean.getSeguridad_message()
								+"/"+ clienteBean.getSeguridad_status();						
			
			logger.debug("Invocando con esta URI==>"+ uri);
			
			Map<String, String> map = new HashMap<>();
			
	        ResponseEntity<ClienteBean> responseEntity = restTemplate.getForEntity(uri, ClienteBean.class, map);
	        logger.debug("devolviendo el MAP==>"+map);
	        logger.debug("Devolviendo el responseEntity==>"+responseEntity.toString());
	        clienteBean=responseEntity.getBody();
	        logger.info("clienteBean:"+clienteBean);
		}catch(Exception e){
			clienteBean.setTipoRespuesta(Constantes.TipoRespuestaWS.Error.getTipoRespuestaWS());
			clienteBean.setTipoError(Constantes.TipoError.HttpException.getError());
			logger.error("Error:busquedaContrato->", e);
		}
		return clienteBean;
	}
	
	public ClienteBean filtroCliente(ClienteBean clienteBean) {
		String uri=null;
		try{		
			uri =clienteBean.getRutaServicioRest()+"filtroCliente/"+String.valueOf(clienteBean.getTipoDocumento())+"/"+clienteBean.getNumeroDocumento();		

			Map<String, String> map = new HashMap<>();
			
	        ResponseEntity<ClienteBean> responseEntity = restTemplate.getForEntity(uri, ClienteBean.class, map);	        
	        clienteBean=responseEntity.getBody();
	        clienteBean.setTipoRespuesta(Constantes.TipoRespuestaWS.Satisfactorio.getTipoRespuestaWS());
		}catch(Exception e){
			clienteBean.setTipoRespuesta(Constantes.TipoRespuestaWS.Error.getTipoRespuestaWS());
			clienteBean.setTipoError(Constantes.TipoError.HttpException.getError());
			logger.error(e.getMessage(), e);
		}
		return clienteBean;
	}
	
	public ClienteBean generarPDF(ClienteBean clienteBean) {
		try {
			String uri = clienteBean.getRutaServicioRest() + "generarPDF";

			ResponseEntity<ClienteBean> responseEntity = restTemplate.postForEntity(uri, RestUtil.getHttpEntity(clienteBean), ClienteBean.class);
			clienteBean = responseEntity.getBody();

			logger.debug("Envio de responseEntity:" + responseEntity.getBody());
			clienteBean.setTipoRespuesta(Constantes.TipoRespuestaWS.Satisfactorio.getTipoRespuestaWS());
			logger.debug("Envio de setTipoRespuesta:" + Constantes.TipoRespuestaWS.Satisfactorio.getTipoRespuestaWS());
			logger.debug("clienteBean:" + clienteBean);
		} catch (Exception e) {
			clienteBean.setTipoRespuesta(Constantes.TipoRespuestaWS.Error.getTipoRespuestaWS());
			clienteBean.setTipoError(Constantes.TipoError.HttpException.getError());
			logger.error("envio del error: ", e);
		}
		logger.debug("respuest del ClienteBean" + clienteBean);
		return clienteBean;
	}

	@Override
	public Integer registrarLogContrato(LogContratoBean logContratoBean) {
		Integer result = 0;
		try {
			String uri = prop.getString("sistema.uriservicio") + "registrarLogContrato";
			ResponseEntity<Integer> responseEntity = restTemplate.postForEntity(uri, RestUtil.getHttpEntity(logContratoBean), Integer.class);
			result = responseEntity == null ? 0 : responseEntity.getBody();
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return result;
	}

}
