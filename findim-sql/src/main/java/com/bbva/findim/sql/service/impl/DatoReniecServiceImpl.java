package com.bbva.findim.sql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.DatosReniecBean;
import com.bbva.findim.sql.dao.DatoReniecDao;
import com.bbva.findim.sql.service.DatoReniecService;

@Service("datoReniecService")
public class DatoReniecServiceImpl implements DatoReniecService{

	@Autowired
	DatoReniecDao datoReniecDao;

	
	public DatosReniecBean obtenerDatosReniecPersona(String numeroDni) {
		// TODO Auto-generated method stub
		return datoReniecDao.obtenerDatosReniec(numeroDni);
	}


	public void guardarDatoReniecPersona(DatosReniecBean datosReniecBean) {
		// TODO Auto-generated method stub
		datoReniecDao.guardarDatoReniec(datosReniecBean);
	}
	
	
	
}
