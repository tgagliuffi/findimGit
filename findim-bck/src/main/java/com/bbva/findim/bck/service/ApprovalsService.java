package com.bbva.findim.bck.service;


import com.bbva.findim.dom.CalificacionClienteBean;


public interface ApprovalsService {
	
  CalificacionClienteBean evaluarCliente(String tipoDocumento, String numDocumento, String tSec)throws Exception ;

}
