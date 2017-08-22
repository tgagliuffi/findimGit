package com.bbva.findim.sql.service;


import com.bbva.findim.dom.DatosReniecBean;


public interface DatoReniecService {

	DatosReniecBean obtenerDatosReniecPersona(String numeroDni);
	
	void guardarDatoReniecPersona(DatosReniecBean datosReniecBean);

}
