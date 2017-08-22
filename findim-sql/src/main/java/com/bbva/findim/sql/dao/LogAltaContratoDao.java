package com.bbva.findim.sql.dao;

import com.bbva.findim.dom.AltaContratoRequest;
import com.bbva.findim.dom.RespuestaBean;

public interface LogAltaContratoDao {

	Long insert(AltaContratoRequest input);
	Integer update(Long id, RespuestaBean output);

}
