package com.bbva.findim.sql.service;

import java.util.List;

import com.bbva.findim.dom.ProcesoSubTareaBean;

public interface ProcesoSubTareaService {
	List<ProcesoSubTareaBean> listarSubTareas(String idTarea);
}
