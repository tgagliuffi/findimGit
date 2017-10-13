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

import com.bbva.findim.dom.LogContratoBean;
import com.bbva.findim.sql.service.LogAltaContratoService;

@Controller
public class ReporteController {

	private static final Logger LOGGER = LogManager.getLogger(ReporteController.class);

	
	@Autowired
	private LogAltaContratoService logAltaContratoService;

	
	@RequestMapping(value = "/listarClientesAprobadosRechazados/{fhInicio}/{fhFin}",method = RequestMethod.GET,headers="Accept=application/json")		
	@ResponseBody		
	public List<LogContratoBean> listaParametros(@PathVariable("fhInicio") String fhInicio,@PathVariable("fhFin") String fhFin) {		
		List<LogContratoBean> lista = null;		
		try {		
			LOGGER.info("[InicioController contratoService.busquedaContrato]"); 			
			//Oracle					
			lista = logAltaContratoService.listaClientesAprobadosDesaprobados(fhInicio, fhFin);		
			LOGGER.info("[InicioController lista]:" + lista); 		
		} catch (Exception e) {		
			LOGGER.error(e.getMessage(), e);		
		}			
		return lista;		
	}
}