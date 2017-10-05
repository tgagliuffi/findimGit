package com.bbva.findim.sql.dao;

import java.util.List;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ParametroCabeceraBean;
import com.bbva.findim.dom.ParametroDetalleBean;

public interface ParametroDao {

	public List<ParametroBean> listarParametrosDetalle(Integer idPadre);
	public List<ParametroCabeceraBean> listaDatosParametros();
	public ParametroDetalleBean gestionarParametrosDetalle(ParametroDetalleBean parametroDetalleBean);
	public ParametroBean obtenerParametroDetallePorIdParamDetalle(Integer idPadre, Integer idParamDetalle);
	public ParametroBean obtenerParametroDetallePorNbParamDetalle(Integer idPadre, String nbParamdetalle);
	public String obtenerDireccionIngles(String direccion);
}
