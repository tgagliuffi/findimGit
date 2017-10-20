package com.bbva.findim.sql.service;

import java.util.List;

import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.OficinaBean;

public interface OficinaService {

	String oficinaAsignadaPorUbigeoReniec(ContratoAltaBean contrato, int tipoCliente) throws Exception;
	List<String> cargarOficina(List<OficinaBean> lstOficinas) throws Exception;
	
}
