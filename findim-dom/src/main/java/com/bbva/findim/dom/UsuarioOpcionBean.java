package com.bbva.findim.dom;

import java.io.Serializable;
import java.util.List;

public class UsuarioOpcionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cdOpcion;
	private Long cdOpcionPadre;
	private String textoOpcion;
	private String enlaceOpcion;
	private Integer numeroOrden;
	private List<UsuarioOpcionBean> childs;

	public Long getCdOpcion() {
		return cdOpcion;
	}

	public void setCdOpcion(Long cdOpcion) {
		this.cdOpcion = cdOpcion;
	}

	public Long getCdOpcionPadre() {
		return cdOpcionPadre;
	}

	public void setCdOpcionPadre(Long cdOpcionPadre) {
		this.cdOpcionPadre = cdOpcionPadre;
	}

	public String getTextoOpcion() {
		return textoOpcion;
	}

	public void setTextoOpcion(String textoOpcion) {
		this.textoOpcion = textoOpcion;
	}

	public String getEnlaceOpcion() {
		return enlaceOpcion;
	}

	public void setEnlaceOpcion(String enlaceOpcion) {
		this.enlaceOpcion = enlaceOpcion;
	}

	public Integer getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(Integer numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public List<UsuarioOpcionBean> getChilds() {
		return childs;
	}

	public void setChilds(List<UsuarioOpcionBean> childs) {
		this.childs = childs;
	}

}
