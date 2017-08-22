package com.bbva.findim.bck.util;

import com.bbva.findim.dom.common.Constantes;

public class Util {


	//private static final Logger LOGGER = LogManager.getLogger(Util.class);

	private Util() {
	}

	public static String generarNombreConsolidadoPDF(String nroDNI, String codContrato){
		String nombrePDF = Constantes.DOCUMENTO_T + nroDNI + codContrato+ Constantes.EXTENSION_PDF;
		return nombrePDF;
	}
	
}
