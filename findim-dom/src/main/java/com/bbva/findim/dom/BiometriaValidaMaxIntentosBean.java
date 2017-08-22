package com.bbva.findim.dom;

import java.io.Serializable;

public class BiometriaValidaMaxIntentosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean resultado;
	private String mensaje;
	private String mensajeTecnico;
	
	
	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "BiometriaValidaMaxIntentosBean [resultado=" + resultado + ", mensaje=" + mensaje + ", mensajeTecnico="
				+ mensajeTecnico + "]";
	}	
	
}
