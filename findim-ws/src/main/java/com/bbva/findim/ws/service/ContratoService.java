package com.bbva.findim.ws.service;

import java.text.ParseException;

import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.FirmaContratoBean;

public interface ContratoService {
	
	public ClienteBean generarPDF(ClienteBean clienteBean) throws ParseException, Exception;
	public int  firmarContrato(FirmaContratoBean firmaContratoBean);
}
