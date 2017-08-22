package com.bbva.findim.ws.service;

import java.util.Map;

import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ComprobantePrepago;
import com.bbva.findim.dom.ContratoBean;
import com.bbva.findim.dom.DatosPdfBean;
import com.bbva.findim.dom.RutasPdfBean;

public interface PdfService {
	
	String generacionPDF(ClienteBean clienteBean, ContratoBean contratoBean);

	
}
