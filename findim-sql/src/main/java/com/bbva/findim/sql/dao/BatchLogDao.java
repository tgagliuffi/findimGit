package com.bbva.findim.sql.dao;

import java.util.List;

import com.bbva.findim.dom.ProcesoBatchLogBean;

public interface BatchLogDao {


	ProcesoBatchLogBean obtenerBatchLogDao(String cdProceso);
	
	List<ProcesoBatchLogBean> listarUltimosProcesos(String cantidad);

}
