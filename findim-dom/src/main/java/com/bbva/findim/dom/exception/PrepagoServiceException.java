package com.bbva.findim.dom.exception;

public class PrepagoServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;

	/**
	 *  
	 */
	public PrepagoServiceException() {
		super();
	}

	/**
	 * @param message
	 */
	public PrepagoServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PrepagoServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PrepagoServiceException(Throwable cause) {
		super(cause);
	}	
}
