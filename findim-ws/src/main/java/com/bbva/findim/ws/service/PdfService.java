package com.bbva.findim.ws.service;

import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoBean;

public interface PdfService {
	
	String generacionPDF(ClienteBean clienteBean, ContratoBean contratoBean, Boolean esRegeneracion);

	
}
