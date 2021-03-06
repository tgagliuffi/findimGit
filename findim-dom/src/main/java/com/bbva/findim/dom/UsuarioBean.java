package com.bbva.findim.dom;

import java.io.Serializable;

public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cdUsuario;
	private String nombreUsuario;
	private Long cdPerfil;
	private String nombrePerfil;

	public Long getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(Long cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Long getCdPerfil() {
		return cdPerfil;
	}

	public void setCdPerfil(Long cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	public String getNombrePerfil() {
		return nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

}
