package com.bbva.findim.sql.service;

import java.util.List;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ParametroCabeceraBean;
import com.bbva.findim.dom.ParametroDetalleBean;

public interface ParametroService {
	public List<ParametroBean> listarParametrosDetalle(Integer idPadre);

	public List<ParametroCabeceraBean> listaDatosParametros();

	public ParametroDetalleBean gestionarParametrosDetalle(ParametroDetalleBean parametroDetalleBean);

	ParametroBean obtenerParametroDetalle(Integer idPadre, Integer idParamDetalle);

//	public List<ParametroDetalleBean> listaTipoDocumentos();
}
