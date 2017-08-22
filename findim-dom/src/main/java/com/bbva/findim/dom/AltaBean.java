package com.bbva.findim.dom;

import java.math.BigDecimal;
import java.util.Date;

public class AltaBean {
	private Integer codigoCliente;
	private String nombresCliente;
	private String correoCliente;
	private String codigoContrato;
	private String nombreContrato;
	private String llaveUnica;
	private Integer numeroVersion;
	private Integer tipoProducto;
	private Date fechaVenta;
	private Date fechaDesembolso;
	private Date fechaFormalizacion;
	private Integer estadoContrato;
	private Integer tipoEnvio;
	private BigDecimal montoFinanciar;
	private Integer numeroCuotas;
	private BigDecimal tea;
	private BigDecimal tcea;
	private Double valorCuota;
	private Integer indicadorFechaVenta;
	private Boolean resultado;
	private String desFuncionalEnvioCorreo;
	private String desTecnicaEnvioCorreo;
	
	private String nombrePdfGenerado;
	private DatosPdfBean datosPdfBean;
	private String nombreGenerado;
	private Integer	usuarioCreacion = new Integer(1);
	private String codigoContratoAuxiliar;
	
	public DatosPdfBean getDatosPdfBean() {
		return datosPdfBean;
	}
	public void setDatosPdfBean(DatosPdfBean datosPdfBean) {
		this.datosPdfBean = datosPdfBean;
	}
	public String getNombrePdfGenerado() {
		return nombrePdfGenerado;
	}
	public void setNombrePdfGenerado(String nombrePdfGenerado) {
		this.nombrePdfGenerado = nombrePdfGenerado;
	}
	public Boolean getResultado() {
		return resultado;
	}
	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}
	public String getDesFuncionalEnvioCorreo() {
		return desFuncionalEnvioCorreo;
	}
	public void setDesFuncionalEnvioCorreo(String desFuncionalEnvioCorreo) {
		this.desFuncionalEnvioCorreo = desFuncionalEnvioCorreo;
	}
	public String getDesTecnicaEnvioCorreo() {
		return desTecnicaEnvioCorreo;
	}
	public void setDesTecnicaEnvioCorreo(String desTecnicaEnvioCorreo) {
		this.desTecnicaEnvioCorreo = desTecnicaEnvioCorreo;
	}
	public Integer getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNombresCliente() {
		return nombresCliente;
	}
	public void setNombresCliente(String nombresCliente) {
		this.nombresCliente = nombresCliente;
	}
	public String getCorreoCliente() {
		return correoCliente;
	}
	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}
	public String getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	public String getNombreContrato() {
		return nombreContrato;
	}
	public void setNombreContrato(String nombreContrato) {
		this.nombreContrato = nombreContrato;
	}
	public Integer getNumeroVersion() {
		return numeroVersion;
	}
	public void setNumeroVersion(Integer numeroVersion) {
		this.numeroVersion = numeroVersion;
	}
	public Integer getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(Integer tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public Date getFechaDesembolso() {
		return fechaDesembolso;
	}
	public void setFechaDesembolso(Date fechaDesembolso) {
		this.fechaDesembolso = fechaDesembolso;
	}
	public Date getFechaFormalizacion() {
		return fechaFormalizacion;
	}
	public void setFechaFormalizacion(Date fechaFormalizacion) {
		this.fechaFormalizacion = fechaFormalizacion;
	}
	public Integer getEstadoContrato() {
		return estadoContrato;
	}
	public void setEstadoContrato(Integer estadoContrato) {
		this.estadoContrato = estadoContrato;
	}
	public Integer getTipoEnvio() {
		return tipoEnvio;
	}
	public void setTipoEnvio(Integer tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}
	public BigDecimal getMontoFinanciar() {
		return montoFinanciar;
	}
	public void setMontoFinanciar(BigDecimal montoFinanciar) {
		this.montoFinanciar = montoFinanciar;
	}
	public Integer getNumeroCuotas() {
		return numeroCuotas;
	}
	public void setNumeroCuotas(Integer numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}
	public BigDecimal getTea() {
		return tea;
	}
	public void setTea(BigDecimal tea) {
		this.tea = tea;
	}
	public BigDecimal getTcea() {
		return tcea;
	}
	public void setTcea(BigDecimal tcea) {
		this.tcea = tcea;
	}
	public Double getValorCuota() {
		return valorCuota;
	}
	public void setValorCuota(Double valorCuota) {
		this.valorCuota = valorCuota;
	}
	public Integer getIndicadorFechaVenta() {
		return indicadorFechaVenta;
	}
	public void setIndicadorFechaVenta(Integer indicadorFechaVenta) {
		this.indicadorFechaVenta = indicadorFechaVenta;
	}
	public String getNombreGenerado() {
		return nombreGenerado;
	}
	public void setNombreGenerado(String nombreGenerado) {
		this.nombreGenerado = nombreGenerado;
	}
	public String getLlaveUnica() {
		return llaveUnica;
	}
	public void setLlaveUnica(String llaveUnica) {
		this.llaveUnica = llaveUnica;
	}
	public Integer getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(Integer usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public String getCodigoContratoAuxiliar() {
		return codigoContratoAuxiliar;
	}
	public void setCodigoContratoAuxiliar(String codigoContratoAuxiliar) {
		this.codigoContratoAuxiliar = codigoContratoAuxiliar;
	}
}
