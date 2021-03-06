package com.bbva.findim.bck.service;

import com.bbva.findim.dom.PersonaBean;

public interface PersonService {
	
	PersonaBean buscarNoCliente(String tipoDocumento, String nroDocumento, String sourceValue, String tSec)throws Exception;
	PersonaBean altaNoCliente(PersonaBean personBean, String tSec)throws Exception;
	
	
	public static final String SOLTERO = "1";
	public static final String CASADO="2";
	public static final String VIUDO="3";
	public static final String DIVORCIADO="4";
	
	public static final String MASCULINO="1";
	public static final String FEMENINO="2";
	
}

