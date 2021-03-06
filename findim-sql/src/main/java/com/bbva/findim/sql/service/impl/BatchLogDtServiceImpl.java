package com.bbva.findim.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.ProcesoBatchLogDtBean;
import com.bbva.findim.sql.dao.BatchLogDtDao;
import com.bbva.findim.sql.service.BatchLogDtService;

@Service("batchLogDtService")
public class BatchLogDtServiceImpl implements BatchLogDtService{

	@Autowired
	BatchLogDtDao batchLogDtDao;

	public List<ProcesoBatchLogDtBean> listarDetallesProcesoBatch(String idProceso,String tarea) {
		// TODO Auto-generated method stub
		return batchLogDtDao.listarDetalleProceso(idProceso,tarea);
	}
	
	
	
}
