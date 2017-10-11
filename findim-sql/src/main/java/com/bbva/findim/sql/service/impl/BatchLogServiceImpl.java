package com.bbva.findim.sql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.DatosReniecBean;
import com.bbva.findim.dom.ProcesoBatchLogBean;
import com.bbva.findim.sql.dao.BatchLogDao;
import com.bbva.findim.sql.dao.DatoReniecDao;
import com.bbva.findim.sql.service.BatchLogService;
import com.bbva.findim.sql.service.DatoReniecService;

@Service("batchLogService")
public class BatchLogServiceImpl implements BatchLogService{

	@Autowired
	BatchLogDao batchLogDao;

	public ProcesoBatchLogBean obtenerProcesoBatch(String cdProceso) {
		// TODO Auto-generated method stub
		return batchLogDao.obtenerBatchLogDao(cdProceso);
	}

	
}
