package com.bbva.findim.dom.exception;

public class PdfServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;

	/**
	 *  
	 */
	public PdfServiceException() {
		super();
	}

	/**
	 * @param message
	 */
	public PdfServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PdfServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PdfServiceException(Throwable cause) {
		super(cause);
	}	
}
