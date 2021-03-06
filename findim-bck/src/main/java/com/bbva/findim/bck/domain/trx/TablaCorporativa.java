package com.bbva.findim.bck.domain.trx;

import java.util.List;

public class TablaCorporativa extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1799615836854710671L;
	private String codigo;
	private String descripcion;
	private List<Valor> valores;
	
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
	public List<Valor> getValores() {
		return valores;
	}
	public void setValores(List<Valor> valores) {
		this.valores = valores;
	}
	
	
}
