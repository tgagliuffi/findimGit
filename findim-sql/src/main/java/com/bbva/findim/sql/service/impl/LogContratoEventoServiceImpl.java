package com.bbva.findim.sql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.EventoContratoBean;
import com.bbva.findim.sql.dao.LogContratoEventoDao;
import com.bbva.findim.sql.service.LogContratoEventoService;

@Service("logContratoEventoService")
public class LogContratoEventoServiceImpl implements LogContratoEventoService{

	@Autowired
	private LogContratoEventoDao logContratoEventoDao;
	
	public Integer registrarEventoContrato(EventoContratoBean eventoContratoBean) {
		return logContratoEventoDao.registrarEventoContrato(eventoContratoBean);
	}

}
