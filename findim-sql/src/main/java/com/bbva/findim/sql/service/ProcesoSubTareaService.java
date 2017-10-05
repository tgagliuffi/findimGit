package com.bbva.findim.sql.service;

import java.util.List;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ParametroCabeceraBean;
import com.bbva.findim.dom.ParametroDetalleBean;
import com.bbva.findim.dom.ProcesoBatchLogBean;
import com.bbva.findim.dom.ProcesoSubTareaBean;
import com.bbva.findim.dom.ProcesoTareaBean;

public interface ProcesoSubTareaService {
	List<ProcesoSubTareaBean> listarSubTareas(String idTarea);
}