package com.bbva.findim.bck.domain.exception;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class BaseException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static final Logger LOGGER = LogManager.getLogger(BaseException.class);

	private String codigo;	

	public BaseException(String codigo, String message) {
		super(message);
		this.codigo = codigo;
	}

	public BaseException(String codigo, Throwable cause) {
		super(cause);
		this.codigo = codigo;
	}
	
	public BaseException(String codigo, String message, Throwable cause) {
		super(message, cause);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
}
