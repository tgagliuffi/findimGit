package com.bbva.findim.dom;

public class ReporteFiltroPrepagoBean {
	private String fechaInicio;
	private String fechaFin;
	private String numeroDocumento;
	private Integer codigoContrato;
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Integer getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(Integer codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
}
