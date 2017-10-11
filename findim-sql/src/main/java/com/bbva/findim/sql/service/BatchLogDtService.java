package com.bbva.findim.sql.service;

import java.util.List;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ParametroCabeceraBean;
import com.bbva.findim.dom.ParametroDetalleBean;
import com.bbva.findim.dom.ProcesoBatchLogDtBean;

public interface BatchLogDtService {
	public List<ProcesoBatchLogDtBean> listarDetallesProcesoBatch(String idProceso);
//	public List<ParametroDetalleBean> listaTipoDocumentos();
}
