package com.bbva.findim.ws.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.sql.service.EmpresaService;

@Controller
public class EmpresaController {
	
	@Autowired
	EmpresaService empresaService;
	private static final Logger LOGGER = LogManager.getLogger(EmpresaController.class);
	
	@RequestMapping(value = "/empresaService/obtenerEmpresa/{cdEmpresa}",method = RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public EmpresaBean obtenerDato(@PathVariable("cdEmpresa") String cdEmpresa) {
		EmpresaBean empresaBean = null;
		try {
			empresaBean = empresaService.obtenerEmpresa(cdEmpresa);
			if(empresaBean!=null){
				empresaBean.setLstEmpresaDetalle(empresaService.listarEmpresa(empresaBean.getIdEmpresa(), empresaBean.getCdEmpresa()));
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}	
		return empresaBean;
	}
}
