package com.bbva.findim.sql.service;

import java.util.List;

import com.bbva.findim.dom.ProcesoBatchLogBean;

public interface BatchLogService {
	ProcesoBatchLogBean obtenerProcesoBatch(String cdProceso);
	List<ProcesoBatchLogBean> listarUltimosProcesosBatch(String cantidad);
}
