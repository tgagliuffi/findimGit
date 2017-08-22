package com.bbva.findim.bck.domain.trx;

public class Valor extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3450436405349133830L;
	private String codigo;
	private String descripcion;
	private String valor;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
