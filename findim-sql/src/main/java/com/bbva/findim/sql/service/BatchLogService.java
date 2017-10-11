package com.bbva.findim.sql.service;

import java.util.List;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ParametroCabeceraBean;
import com.bbva.findim.dom.ParametroDetalleBean;
import com.bbva.findim.dom.ProcesoBatchLogBean;

public interface BatchLogService {
	ProcesoBatchLogBean obtenerProcesoBatch(String cdProceso);
}
