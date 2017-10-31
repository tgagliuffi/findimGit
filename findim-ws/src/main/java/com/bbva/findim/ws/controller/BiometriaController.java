package com.bbva.findim.ws.controller;		
		
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbva.findim.dom.BiometriaBean;
import com.bbva.findim.dom.BiometriaValidaMaxIntentosBean;
import com.bbva.findim.dom.ResultadoBiometriaBean;
import com.bbva.findim.ws.service.BiometriaService;		
	
		
//@RestController		
//@RequestMapping("biometria")	
@Controller
public class BiometriaController {		
		
	@Autowired		
//	@Qualifier(value="biometriaService")
	BiometriaService biometriaService;		
		
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)		
	public Long grabarBiometria(@RequestBody BiometriaBean biometriaBean) {		
		return biometriaService.registrar(biometriaBean);		
	}		
		
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)		
	public String actualizarBiometria(@RequestBody BiometriaBean biometriaBean) {		
		return biometriaService.actualizar(biometriaBean);		
	}		
		
	@RequestMapping(value = "/resultado", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)		
	public ResultadoBiometriaBean obtenerResultado(@RequestBody BiometriaBean biometriaBean) {		
		return biometriaService.obtenerResultado(biometriaBean);		
	}		
		
	@RequestMapping(value = "/validarMaximoIntentos/{numeroDocumento}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)		
	public BiometriaValidaMaxIntentosBean obtenerResultado(@PathVariable("numeroDocumento") String numeroDocumento) {		
		return biometriaService.validarMaximoIntentos(numeroDocumento);		
	}		
		
}