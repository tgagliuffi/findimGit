package com.bbva.findim.sql.dao;

import java.util.List;

import com.bbva.findim.dom.ProcesoBatchLogDtBean;

public interface BatchLogDtDao {

	public List<ProcesoBatchLogDtBean> listarDetalleProceso(String cdProceso,String tarea);
//	ProcesoBatchLogDtBean obtenerDetalleProceso(Integer idPadre, Integer idParamDetalle);

}
