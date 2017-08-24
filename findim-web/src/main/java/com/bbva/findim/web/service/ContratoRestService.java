package com.bbva.findim.web.service;

import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.LogContratoBean;

public interface ContratoRestService {

	ClienteBean obtenerDatosComplementarios(ClienteBean clienteBean);

	ClienteBean busquedaContrato(ClienteBean clienteBean);

	ClienteBean filtroCliente(ClienteBean clienteBean);

	ClienteBean generarPDF(ClienteBean clienteBean);

	Integer registrarLogContrato(LogContratoBean logContratoBean);

}
