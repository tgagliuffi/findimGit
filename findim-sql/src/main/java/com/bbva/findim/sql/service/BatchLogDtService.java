package com.bbva.findim.sql.service;

import java.util.List;

import com.bbva.findim.dom.ProcesoBatchLogDtBean;

public interface BatchLogDtService {
	public List<ProcesoBatchLogDtBean> listarDetallesProcesoBatch(String idProceso,String tarea);
//	public List<ParametroDetalleBean> listaTipoDocumentos();
}
