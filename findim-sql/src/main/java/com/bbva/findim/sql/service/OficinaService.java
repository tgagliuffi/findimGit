package com.bbva.findim.sql.service;

import com.bbva.findim.dom.ContratoAltaBean;

public interface OficinaService {

	String oficinaAsignadaPorUbigeoReniec(ContratoAltaBean contrato, int tipoCliente) throws Exception;
	
}
