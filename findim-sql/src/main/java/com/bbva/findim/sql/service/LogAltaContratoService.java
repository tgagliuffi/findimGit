package com.bbva.findim.sql.service;



import java.util.List;

import com.bbva.findim.dom.AltaContratoRequest;
import com.bbva.findim.dom.LogContratoBean;
import com.bbva.findim.dom.RespuestaBean;

public interface LogAltaContratoService {

	Long insert(AltaContratoRequest input);
	Integer update(Long id, RespuestaBean output);
	
	List<LogContratoBean> listaClientesAprobadosDesaprobados(String inicio,String fin);

}
