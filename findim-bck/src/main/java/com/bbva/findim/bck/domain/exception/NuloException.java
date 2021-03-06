package com.bbva.findim.bck.domain.exception;

import java.text.MessageFormat;

public class NuloException extends ValidacionException {
	
	/**
	 * @author tgagliuffi
	 */
	private static final long serialVersionUID = 8728222627596876125L;
	private static final String MENSAJE = "Dato nulo.";
	private static final String FORMATO_LOG = MENSAJE + " El nombre del dato es: ''{0}''.";
	
	public NuloException(String nombreCampo) {
		super(CODIGO_CAMPO_NULO, MENSAJE);
		LOGGER.info(MessageFormat.format(FORMATO_LOG, nombreCampo));
	}

}
