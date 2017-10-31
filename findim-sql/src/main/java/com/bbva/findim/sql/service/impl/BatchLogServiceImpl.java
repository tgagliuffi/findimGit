package com.bbva.findim.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.ProcesoBatchLogBean;
import com.bbva.findim.sql.dao.BatchLogDao;
import com.bbva.findim.sql.service.BatchLogService;

@Service("batchLogService")
public class BatchLogServiceImpl implements BatchLogService{

	@Autowired
	BatchLogDao batchLogDao;

	public ProcesoBatchLogBean obtenerProcesoBatch(String cdProceso) {
		// TODO Auto-generated method stub
		return batchLogDao.obtenerBatchLogDao(cdProceso);
	}

	public List<ProcesoBatchLogBean> listarUltimosProcesosBatch(String cantidad) {
		// TODO Auto-generated method stub
		return batchLogDao.listarUltimosProcesos(cantidad);
	}

	
}
