package com.bbva.findim.sql.dao;

import java.util.List;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ParametroCabeceraBean;
import com.bbva.findim.dom.ParametroDetalleBean;
import com.bbva.findim.dom.ProcesoBatchLogDtBean;

public interface BatchLogDtDao {

	public List<ProcesoBatchLogDtBean> listarDetalleProceso(String cdProceso);
//	ProcesoBatchLogDtBean obtenerDetalleProceso(Integer idPadre, Integer idParamDetalle);

}