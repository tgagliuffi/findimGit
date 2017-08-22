package com.bbva.findim.dom;

import java.io.Serializable;

public class UsuarioAccionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cdAccion;
	private String nemonico;

	public Long getCdAccion() {
		return cdAccion;
	}

	public void setCdAccion(Long cdAccion) {
		this.cdAccion = cdAccion;
	}

	public String getNemonico() {
		return nemonico;
	}

	public void setNemonico(String nemonico) {
		this.nemonico = nemonico;
	}

}
