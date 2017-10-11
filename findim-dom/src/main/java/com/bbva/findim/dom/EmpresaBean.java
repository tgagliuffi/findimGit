package com.bbva.findim.dom;

import java.util.List;

public class EmpresaBean {
	
	private Long idEmpresa;
	private String cdEmpresa;
	private String nombreEmpresa;
	private String indenticador;
	private String cdProdExt;
	
	private String fechaInscripcion;
	private String fechaExpiracion;
	
	private List<EmpresaDetalle> lstEmpresaDetalle;
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getCdEmpresa() {
		return cdEmpresa;
	}
	public void setCdEmpresa(String cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getIndenticador() {
		return indenticador;
	}
	public void setIndenticador(String indenticador) {
		this.indenticador = indenticador;
	}
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public String getFechaExpiracion() {
		return fechaExpiracion;
	}
	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}
	public List<EmpresaDetalle> getLstEmpresaDetalle() {
		return lstEmpresaDetalle;
	}
	public void setLstEmpresaDetalle(List<EmpresaDetalle> lstEmpresaDetalle) {
		this.lstEmpresaDetalle = lstEmpresaDetalle;
	}
	public String getCdProdExt() {
		return cdProdExt;
	}
	public void setCdProdExt(String cdProdExt) {
		this.cdProdExt = cdProdExt;
	}
	
}
