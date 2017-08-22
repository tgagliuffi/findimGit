package com.bbva.findim.sql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.AltaContratoRequest;
import com.bbva.findim.dom.RespuestaBean;
import com.bbva.findim.sql.dao.LogAltaContratoDao;
import com.bbva.findim.sql.service.LogAltaContratoService;

@Service("logAltaContratoService")
public class LogAltaContratoServiceImpl implements LogAltaContratoService {

	@Autowired
	private LogAltaContratoDao logAltaContratoDao;

	public Long insert(AltaContratoRequest input) {
		return logAltaContratoDao.insert(input);
	}

	public Integer update(Long id, RespuestaBean output) {
		return logAltaContratoDao.update(id, output);
	}

}
