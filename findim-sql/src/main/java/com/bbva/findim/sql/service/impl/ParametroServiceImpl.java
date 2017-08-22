package com.bbva.findim.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ParametroCabeceraBean;
import com.bbva.findim.dom.ParametroDetalleBean;
import com.bbva.findim.sql.dao.ParametroDao;
import com.bbva.findim.sql.service.ParametroService;

@Service("parametroService")
public class ParametroServiceImpl implements ParametroService {

	@Autowired
	private ParametroDao parametroDAO;
	
	public List<ParametroBean> listarParametrosDetalle(Integer idPadre) {
		return parametroDAO.listarParametrosDetalle(idPadre);
	}

	public List<ParametroCabeceraBean> listaDatosParametros() {
		// TODO Auto-generated method stub
		return parametroDAO.listaDatosParametros();
	}

	public ParametroDetalleBean gestionarParametrosDetalle(ParametroDetalleBean parametroDetalleBean) {
		// TODO Auto-generated method stub
		return null;
	}

	public ParametroBean obtenerParametroDetalle(Integer idPadre, Integer idParamDetalle) {
		// TODO Auto-generated method stub
		return parametroDAO.obtenerParametroDetalle(idPadre, idParamDetalle);
	}

//	public List<ParametroDetalleBean> listaTipoDocumentos() {
//		// TODO Auto-generated method stub
//		return parametroDAO.listaTipoDocumentos();
//	}

}
