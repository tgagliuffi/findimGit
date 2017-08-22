package com.bbva.findim.sql.dao;

import com.bbva.findim.dom.DatosReniecBean;

public interface DatoReniecDao {
	
	DatosReniecBean obtenerDatosReniec(String numeroDni);
	
	void guardarDatoReniec(DatosReniecBean datosReniecBean);

}
