package com.bbva.findim.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.ProcesoSubTareaBean;
import com.bbva.findim.sql.dao.ProcesoSubTareaDao;
import com.bbva.findim.sql.service.ProcesoSubTareaService;

@Service("procesoSubTareaService")
public class ProcesoSubTareaServiceImpl implements ProcesoSubTareaService{

	@Autowired
	ProcesoSubTareaDao procesoSubTareaDao;


	public List<ProcesoSubTareaBean> listarSubTareas(String idTarea) {
		return procesoSubTareaDao.listarSubtareas(idTarea);
	}

	
}
