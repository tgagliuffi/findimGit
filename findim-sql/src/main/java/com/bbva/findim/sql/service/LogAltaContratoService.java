package com.bbva.findim.sql.service;



import com.bbva.findim.dom.AltaContratoRequest;
import com.bbva.findim.dom.RespuestaBean;

public interface LogAltaContratoService {

	Long insert(AltaContratoRequest input);
	Integer update(Long id, RespuestaBean output);

}
