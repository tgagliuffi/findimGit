package com.bbva.findim.bck.domain.exception;


public class ErrorGeneralException extends ErrorException {

	private static final long serialVersionUID = 1L;

	public ErrorGeneralException(String message, Throwable cause) {
		super(ErrorException.CODIGO_ERROR_GENERAL, message, cause);
		LOGGER.error(message, cause);
	}

}
