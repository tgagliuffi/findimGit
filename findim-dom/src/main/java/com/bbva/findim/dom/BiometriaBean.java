package com.bbva.findim.dom;

public class BiometriaBean {

	private Long numeroSolicitud;
	private String codError;
	private String descError;
	private String codErrorReniec;
	private String descErrorReniec;
	private String codPc;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombre;
	private String codRestriccion;
	private String descRestriccion;
	private String codVigencia;
	private String descVigencia;
	private String ip;
	private String mac;
	private String codTransaccion;
	private String numeroDocumento;
	private Integer codigoProceso;

	public Long getNumeroSolicitud() {
		return numeroSolicitud;
	}

	public void setNumeroSolicitud(Long numeroSolicitud) {
		this.numeroSolicitud = numeroSolicitud;
	}

	public String getCodError() {
		return codError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public String getDescError() {
		return descError;
	}

	public void setDescError(String descError) {
		this.descError = descError;
	}

	public String getCodErrorReniec() {
		return codErrorReniec;
	}

	public void setCodErrorReniec(String codErrorReniec) {
		this.codErrorReniec = codErrorReniec;
	}

	public String getDescErrorReniec() {
		return descErrorReniec;
	}

	public void setDescErrorReniec(String descErrorReniec) {
		this.descErrorReniec = descErrorReniec;
	}

	public String getCodPc() {
		return codPc;
	}

	public void setCodPc(String codPc) {
		this.codPc = codPc;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodRestriccion() {
		return codRestriccion;
	}

	public void setCodRestriccion(String codRestriccion) {
		this.codRestriccion = codRestriccion;
	}

	public String getDescRestriccion() {
		return descRestriccion;
	}

	public void setDescRestriccion(String descRestriccion) {
		this.descRestriccion = descRestriccion;
	}

	public String getCodVigencia() {
		return codVigencia;
	}

	public void setCodVigencia(String codVigencia) {
		this.codVigencia = codVigencia;
	}

	public String getDescVigencia() {
		return descVigencia;
	}

	public void setDescVigencia(String descVigencia) {
		this.descVigencia = descVigencia;
	}

	public String getCodTransaccion() {
		return codTransaccion;
	}

	public void setCodTransaccion(String codTransaccion) {
		this.codTransaccion = codTransaccion;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getCodigoProceso() {
		return codigoProceso;
	}

	public void setCodigoProceso(Integer codigoProceso) {
		this.codigoProceso = codigoProceso;
	}

	@Override
	public String toString() {
		return "BiometriaBean [numeroSolicitud=" + numeroSolicitud + ", codError=" + codError + ", descError="
				+ descError + ", codErrorReniec=" + codErrorReniec + ", descErrorReniec=" + descErrorReniec + ", codPc="
				+ codPc + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", nombre="
				+ nombre + ", codRestriccion=" + codRestriccion + ", descRestriccion=" + descRestriccion
				+ ", codVigencia=" + codVigencia + ", descVigencia=" + descVigencia + ", ip=" + ip + ", mac=" + mac
				+ ", codTransaccion=" + codTransaccion + "]";
	}

}
