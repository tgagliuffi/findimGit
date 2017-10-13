package com.bbva.findim.sql.dao;

import java.util.List;

import com.bbva.findim.dom.AltaContratoRequest;
import com.bbva.findim.dom.LogContratoBean;
import com.bbva.findim.dom.RespuestaBean;

public interface LogAltaContratoDao {

	Long insert(AltaContratoRequest input);
	Integer update(Long id, RespuestaBean output);
	
	List<LogContratoBean> obtenerClientesAprobadosRechazadosMes(String fhInicio,String fhFin);

}
