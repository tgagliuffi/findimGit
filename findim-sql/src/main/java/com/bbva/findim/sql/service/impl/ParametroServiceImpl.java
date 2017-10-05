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
		return parametroDAO.listaDatosParametros();
	}

	public ParametroDetalleBean gestionarParametrosDetalle(ParametroDetalleBean parametroDetalleBean) {
		return null;
	}

	public ParametroBean obtenerParametroDetallePorIdParamDetalle(Integer idPadre, Integer idParamDetalle) {
		return parametroDAO.obtenerParametroDetallePorIdParamDetalle(idPadre, idParamDetalle);
	}

	public String obtenerDireccionIngles(String nroDni) {
		return parametroDAO.obtenerDireccionIngles(nroDni);
	}
	
	public ParametroBean obtenerParametroDetallePorNbParamDetalle(Integer idPadre, String nbParamdetalle) {
		return parametroDAO.obtenerParametroDetallePorNbParamDetalle(idPadre, nbParamdetalle);
	}



}
