package com.bbva.findim.bck.domain.exception;

public class ValidacionException extends BaseException {

	
	private static final long serialVersionUID = 1L;
	public ValidacionException(String codigo, String message) {
		super(codigo, message);
	}
	
	public static final String CODIGO_CAMPO_NULO				= "V001";
	public static final String CODIGO_CAMPO_ENBLANCO	 		= "V002";
	public static final String CODIGO_VALOR_REQUERIDO	 		= "V003";

}
