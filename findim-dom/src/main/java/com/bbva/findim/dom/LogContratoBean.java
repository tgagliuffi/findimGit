package com.bbva.findim.dom;

import java.util.Date;

public class LogContratoBean {
	
	private Integer codigoContrato;
	private Integer tipoProducto;
	private Integer numeroVersion;
	private Integer codigoCliente;
	private String  nombreEstadoContrato;
	private Integer codigoResultado;
	private String detalleTecnico;
	private String detalleFuncional;
	private String rutaServicioRest;
	
	//reporte
	private String cdDocIdentidad;
	private String rtFiltro;
	private String nbMotivoRechazo;
	private Date fhPeticion;
	
	

	public Date getFhPeticion() {
		return fhPeticion;
	}
	public void setFhPeticion(Date fhPeticion) {
		this.fhPeticion = fhPeticion;
	}
	public String getCdDocIdentidad() {
		return cdDocIdentidad;
	}
	public void setCdDocIdentidad(String cdDocIdentidad) {
		this.cdDocIdentidad = cdDocIdentidad;
	}
	public String getRtFiltro() {
		return rtFiltro;
	}
	public void setRtFiltro(String rtFiltro) {
		this.rtFiltro = rtFiltro;
	}
	public String getNbMotivoRechazo() {
		return nbMotivoRechazo;
	}
	public void setNbMotivoRechazo(String nbMotivoRechazo) {
		this.nbMotivoRechazo = nbMotivoRechazo;
	}
	public Integer getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(Integer codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	public Integer getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(Integer tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public Integer getNumeroVersion() {
		return numeroVersion;
	}
	public void setNumeroVersion(Integer numeroVersion) {
		this.numeroVersion = numeroVersion;
	}
	public Integer getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNombreEstadoContrato() {
		return nombreEstadoContrato;
	}
	public void setNombreEstadoContrato(String nombreEstadoContrato) {
		this.nombreEstadoContrato = nombreEstadoContrato;
	}
	public Integer getCodigoResultado() {
		return codigoResultado;
	}
	public void setCodigoResultado(Integer codigoResultado) {
		this.codigoResultado = codigoResultado;
	}
	public String getDetalleTecnico() {
		return detalleTecnico;
	}
	public void setDetalleTecnico(String detalleTecnico) {
		this.detalleTecnico = detalleTecnico;
	}
	public String getDetalleFuncional() {
		return detalleFuncional;
	}
	public void setDetalleFuncional(String detalleFuncional) {
		this.detalleFuncional = detalleFuncional;
	}
	public String getRutaServicioRest() {
		return rutaServicioRest;
	}
	public void setRutaServicioRest(String rutaServicioRest) {
		this.rutaServicioRest = rutaServicioRest;
	}
}
