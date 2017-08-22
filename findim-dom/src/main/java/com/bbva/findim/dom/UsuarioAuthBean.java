package com.bbva.findim.dom;

import java.io.Serializable;
import java.util.List;

public class UsuarioAuthBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private UsuarioBean usuario;
	private List<UsuarioOpcionBean> opciones;
	private List<UsuarioAccionBean> acciones;

	public boolean hasAccion(String accionBuscada) {
		if (acciones == null) {
			return false;
		}
		for (UsuarioAccionBean accion : acciones) {
			if (accion.getNemonico().equals(accionBuscada)) {
				return true;
			}
		}
		return false;
	}

	public List<UsuarioOpcionBean> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<UsuarioOpcionBean> opciones) {
		this.opciones = opciones;
	}

	public List<UsuarioAccionBean> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<UsuarioAccionBean> acciones) {
		this.acciones = acciones;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

}
