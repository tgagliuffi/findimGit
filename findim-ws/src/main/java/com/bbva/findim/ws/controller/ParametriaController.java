package com.bbva.findim.ws.controller;		
		
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.sql.service.ParametroService;		
		
@Controller		
public class ParametriaController {		
		
	private static final Logger LOGGER = LogManager.getLogger(ParametriaController.class);		
			
			
	@Autowired		
	private ParametroService parametroService;		
			
			
	@RequestMapping(value = "/listaDetalleParametro/{idPadre}",method = RequestMethod.GET,headers="Accept=application/json")		
	@ResponseBody		
	public List<ParametroBean> listaParametros(@PathVariable("idPadre") Integer idPadre) {		
		List<ParametroBean> listaParametroBean = null;		
		try {		
			LOGGER.info("[InicioController contratoService.busquedaContrato]"); 			
			//Oracle					
			listaParametroBean = parametroService.listarParametrosDetalle(idPadre);		
			LOGGER.info("[InicioController listaParametroBean]:" + listaParametroBean); 		
		} catch (Exception e) {		
			LOGGER.error(e.getMessage(), e);		
		}			
		return listaParametroBean;		
	}	
	@RequestMapping(value = "/obtenerDireccionHost/{dirAndocs}",method = RequestMethod.GET,headers="Accept=application/json")		
	@ResponseBody	
	public String obtenerEquivalenciaH(@PathVariable("dirAndocs") String nroDni) {
		String dirIngles = null;
		try {		
			LOGGER.info("[ParametriaController Inicio :  obtenerDirCastIngles]"); 			
			dirIngles = parametroService.obtenerDireccionIngles(nroDni);
			LOGGER.info("[ParametriaController Fin : obtenerDirCastIngles]:" + dirIngles); 		
		} catch (Exception e) {		
			LOGGER.error(e.getMessage(), e);	
			return dirIngles;
		}			
		return dirIngles;	
	}
	
			
}