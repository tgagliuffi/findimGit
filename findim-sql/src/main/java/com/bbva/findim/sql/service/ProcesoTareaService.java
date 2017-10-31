package com.bbva.findim.sql.service;

import java.util.List;

import com.bbva.findim.dom.ProcesoTareaBean;

public interface ProcesoTareaService {
	ProcesoTareaBean obtenerProcesoTarea(String idTarea);
	
	List<ProcesoTareaBean> listarTareas();
}
