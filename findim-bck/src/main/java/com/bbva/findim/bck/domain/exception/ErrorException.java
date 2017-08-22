package com.bbva.findim.bck.domain.exception;

public class ErrorException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorException(String codigo, String message, Throwable cause) {
		super(codigo, message, cause);
	}
	
	public static final String CODIGO_ERROR_GENERAL		= "E001";

}
