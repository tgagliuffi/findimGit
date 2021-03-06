package com.bbva.findim.bck.domain.exception;

import java.text.MessageFormat;

public class RequeridoException extends ValidacionException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8791308040452986347L;
	private static final String MENSAJE = "Valor requerido.";
	private static final String FORMATO_LOG = MENSAJE + " El valor requerido es: ''{0}''.";

	public RequeridoException(String nombreCampo) {
		super(CODIGO_VALOR_REQUERIDO, MENSAJE);
		LOGGER.info(MessageFormat.format(FORMATO_LOG, nombreCampo));
	}

}
