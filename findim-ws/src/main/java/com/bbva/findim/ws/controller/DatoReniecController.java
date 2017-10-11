package com.bbva.findim.ws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.findim.dom.DatosReniecBean;
import com.bbva.findim.sql.service.DatoReniecService;

@Controller
public class DatoReniecController {
	
	@Autowired
	DatoReniecService datoReniecService;
	private static final Logger LOGGER = LogManager.getLogger(DatoReniecController.class);
	
	@RequestMapping(value = "/obtenerDatoReniec/{numeroDni}",method = RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public DatosReniecBean obtenerDato(@PathVariable("numeroDni") String numeroDni) {
		DatosReniecBean datosReniecBean = null;
		try {
			datosReniecBean = datoReniecService.obtenerDatosReniecPersona(numeroDni);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}	
		return datosReniecBean;
	}
	
	
}
