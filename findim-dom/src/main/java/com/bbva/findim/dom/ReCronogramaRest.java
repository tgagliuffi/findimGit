package com.bbva.findim.dom;

public class ReCronogramaRest {
	
	private String cdPropuesta; 
	private String nroDocumento; 
	private String tipoDocumento;
	private String nombreCompleto;	
	private String importeBien; 
	private String importeInicial;
	private String importePrestamo;  
	private String tipoEnvio;
	private String fechaNacimiento;
	private String cdTarifa;
	private String cdEmpresa; 
	private String moneda;
	private RespuestaService respuestaService;
	
	public String getCdPropuesta() {
		return cdPropuesta;
	}
	public void setCdPropuesta(String cdPropuesta) {
		this.cdPropuesta = cdPropuesta;
	}
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getImporteBien() {
		return importeBien;
	}
	public void setImporteBien(String importeBien) {
		this.importeBien = importeBien;
	}
	public String getImporteInicial() {
		return importeInicial;
	}
	public void setImporteInicial(String importeInicial) {
		this.importeInicial = importeInicial;
	}
	public String getImportePrestamo() {
		return importePrestamo;
	}
	public void setImportePrestamo(String importePrestamo) {
		this.importePrestamo = importePrestamo;
	}
	public String getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCdTarifa() {
		return cdTarifa;
	}
	public void setCdTarifa(String cdTarifa) {
		this.cdTarifa = cdTarifa;
	}
	public String getCdEmpresa() {
		return cdEmpresa;
	}
	public void setCdEmpresa(String cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public RespuestaService getRespuestaService() {
		return respuestaService;
	}
	public void setRespuestaService(RespuestaService respuestaService) {
		this.respuestaService = respuestaService;
	}


}
