package com.bbva.findim.sql.dao;

import java.util.List;

import com.bbva.findim.dom.ProcesoSubTareaBean;

public interface ProcesoSubTareaDao {


	List<ProcesoSubTareaBean> listarSubtareas(String idTarea);

}
