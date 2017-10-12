package com.bbva.findim.sql.dao;

import java.util.List;

import com.bbva.findim.dom.ProcesoTareaBean;

public interface ProcesoTareaDao {


	ProcesoTareaBean obtenerProcesoTareaDao(String idTarea);
	
	List<ProcesoTareaBean> listarTareas();

}
