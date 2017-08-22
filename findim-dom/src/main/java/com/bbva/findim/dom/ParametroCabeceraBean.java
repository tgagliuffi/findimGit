package com.bbva.findim.dom;

import java.util.List;

public class ParametroCabeceraBean {
	private Integer idPadre;
	private String nb_paramcabecera;
	private List<ParametroDetalleBean> listaParametrosDetalle;
	
	private Integer tipoRespuesta;
	private String  detalleFuncional;
	private String rutaServicioRest;
	
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	public String getNb_paramcabecera() {
		return nb_paramcabecera;
	}
	public void setNb_paramcabecera(String nb_paramcabecera) {
		this.nb_paramcabecera = nb_paramcabecera;
	}
	public List<ParametroDetalleBean> getListaParametrosDetalle() {
		return listaParametrosDetalle;
	}
	public void setListaParametrosDetalle(List<ParametroDetalleBean> listaParametrosDetalle) {
		this.listaParametrosDetalle = listaParametrosDetalle;
	}
	public Integer getTipoRespuesta() {
		return tipoRespuesta;
	}
	public void setTipoRespuesta(Integer tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
	public String getDetalleFuncional() {
		return detalleFuncional;
	}
	public void setDetalleFuncional(String detalleFuncional) {
		this.detalleFuncional = detalleFuncional;
	}
	public String getRutaServicioRest() {
		return rutaServicioRest;
	}
	public void setRutaServicioRest(String rutaServicioRest) {
		this.rutaServicioRest = rutaServicioRest;
	}
	
	
}
