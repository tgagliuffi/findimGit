package com.bbva.findim.bck.configuration.domain.error;

public class Parametro {
	private String valor;
	private String codigoTipo;
	private String codigo;
	
	

	public Parametro(String codigoTipo, String codigo) {
		super();
		this.codigoTipo = codigoTipo;
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getCodigoTipo() {
		return codigoTipo;
	}

	public void setCodigoTipo(String codigoTipo) {
		this.codigoTipo = codigoTipo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
