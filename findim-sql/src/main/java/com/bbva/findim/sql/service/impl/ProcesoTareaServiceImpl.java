package com.bbva.findim.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.ProcesoTareaBean;
import com.bbva.findim.sql.dao.ProcesoTareaDao;
import com.bbva.findim.sql.service.ProcesoTareaService;

@Service("procesoTareaService")
public class ProcesoTareaServiceImpl implements ProcesoTareaService{

	@Autowired
	ProcesoTareaDao procesoTareaDao;

	public ProcesoTareaBean obtenerProcesoTarea(String idTarea) {
		// TODO Auto-generated method stub
		return procesoTareaDao.obtenerProcesoTareaDao(idTarea);
	}

	public List<ProcesoTareaBean> listarUltimasTareas() {
		// TODO Auto-generated method stub
		return procesoTareaDao.listarUltimosProceosTarea();
	}

	
}
