package com.bbva.findim.bck.domain.exception;

import java.text.MessageFormat;

public class EnBlancoException extends ValidacionException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5737010720242671360L;
	private static final String MENSAJE = "Dato en blando.";
	private static final String FORMATO_LOG = MENSAJE + " El nombre del dato es: ''{0}''.";

	public EnBlancoException(String nombreCampo) {
		super(CODIGO_CAMPO_NULO, MENSAJE);
		LOGGER.info(MessageFormat.format(FORMATO_LOG, nombreCampo));
	}

}
