package com.bbva.findim.ws.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.File;
import com.bbva.findim.sql.service.OficinaService;

@Controller
public class OficinaController {
	
	private static final Logger LOGGER = LogManager.getLogger(OficinaController.class);		
	
	@Autowired
	OficinaService oficinaService;
	
	@RequestMapping(value = "/obtenerOficina/{contrato}/{tipoCliente}",method = RequestMethod.GET,headers="Accept=application/json")		
	@ResponseBody	
	public String obtenerOficina(@PathVariable("contrato") ContratoAltaBean contrato,
								 @PathVariable("tipoCliente") int cdDist) {
		String cdOficina = null;
		try {		
			cdOficina = oficinaService.oficinaAsignadaPorUbigeoReniec(contrato, cdDist);
			
		} catch (Exception e) {		
			LOGGER.error(e.getMessage(), e);	
			return cdOficina;
		}			
		return cdOficina;	
	}
	
	@RequestMapping(value = "/oficina/uploadOficina",method = RequestMethod.POST,headers="Accept=application/json")		
	@ResponseBody	
    public  Map<String, Object>  uploadFile(@RequestBody File file) throws IOException {
		List<String> lstlogCarga = null;
		Map<String, Object> respuesta = new HashMap<String, Object>();
		try {
			if(file!=null)
				lstlogCarga = oficinaService.cargarOficina(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		respuesta.put("lstLog", lstlogCarga);
		return respuesta;
        
    }


}
