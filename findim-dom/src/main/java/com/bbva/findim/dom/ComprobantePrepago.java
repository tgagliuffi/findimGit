package com.bbva.findim.dom;

import java.math.BigDecimal;
import java.util.Date;

public class ComprobantePrepago {

	private String numeroComprobantePago;
	private Integer codContrato;
	private Integer numeroCuota;
	private String emailCliente;
	private String nombreCompletoCliente;
	private String dniCliente;
	private String direccionCliente;
	private String nroPrestamo;
	private BigDecimal importePrestamo;
	private Date fechaDesembolso;
	private Integer nroCuotasPactadas;
	private String moneda;
	private BigDecimal tcea;
	private BigDecimal tea;
	private Integer nroCuotasPendientes;
	private BigDecimal importeTotalPagado;
	private BigDecimal saldoCapitalAnterior;
	private BigDecimal saldoCapitalActual;
	private Date fechaVencimientoCuota;
	private Date fechaPagoEfectivo;
	private BigDecimal amortizacionCapital;
	private BigDecimal interesesCorridos;
	private BigDecimal comisionEnvioEstadoCuenta;
	private BigDecimal penalidad;
	private BigDecimal cuotaSinSeguro;
	private BigDecimal importeCuota;
	private BigDecimal seguroDesgravamen;
	private Date fechaProximaVencimiento;
	private Integer nroCuotaProxima;
	private BigDecimal importeTotalCuota;

	private String nombreArchivoComprobante;
	private Integer codigoDomicilio;
	private String nombreDireccion;
	private String codigoUbigeo;
	private Integer tipoEnvio;
	private Integer codigoCliente;
	private Integer numeroVersion;
	private Integer tipoProducto;
	private Integer estadoEnvio;
	private Integer tipoPrepago;

	private RutasPdfBean RutasPdfBean;

	public Integer getCodContrato() {
		return codContrato;
	}

	public void setCodContrato(Integer codContrato) {
		this.codContrato = codContrato;
	}

	public Integer getNumeroCuota() {
		return numeroCuota;
	}

	public void setNumeroCuota(Integer numeroCuota) {
		this.numeroCuota = numeroCuota;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getNombreCompletoCliente() {
		return nombreCompletoCliente;
	}

	public void setNombreCompletoCliente(String nombreCompletoCliente) {
		this.nombreCompletoCliente = nombreCompletoCliente;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getNroPrestamo() {
		return nroPrestamo;
	}

	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	public BigDecimal getImportePrestamo() {
		return importePrestamo;
	}

	public void setImportePrestamo(BigDecimal importePrestamo) {
		this.importePrestamo = importePrestamo;
	}

	public Date getFechaDesembolso() {
		return fechaDesembolso;
	}

	public void setFechaDesembolso(Date fechaDesembolso) {
		this.fechaDesembolso = fechaDesembolso;
	}

	public Integer getNroCuotasPactadas() {
		return nroCuotasPactadas;
	}

	public void setNroCuotasPactadas(Integer nroCuotasPactadas) {
		this.nroCuotasPactadas = nroCuotasPactadas;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Integer getNroCuotasPendientes() {
		return nroCuotasPendientes;
	}

	public void setNroCuotasPendientes(Integer nroCuotasPendientes) {
		this.nroCuotasPendientes = nroCuotasPendientes;
	}

	public BigDecimal getImporteTotalPagado() {
		return importeTotalPagado;
	}

	public void setImporteTotalPagado(BigDecimal importeTotalPagado) {
		this.importeTotalPagado = importeTotalPagado;
	}

	public BigDecimal getSaldoCapitalAnterior() {
		return saldoCapitalAnterior;
	}

	public void setSaldoCapitalAnterior(BigDecimal saldoCapitalAnterior) {
		this.saldoCapitalAnterior = saldoCapitalAnterior;
	}

	public BigDecimal getSaldoCapitalActual() {
		return saldoCapitalActual;
	}

	public void setSaldoCapitalActual(BigDecimal saldoCapitalActual) {
		this.saldoCapitalActual = saldoCapitalActual;
	}

	public Date getFechaVencimientoCuota() {
		return fechaVencimientoCuota;
	}

	public void setFechaVencimientoCuota(Date fechaVencimientoCuota) {
		this.fechaVencimientoCuota = fechaVencimientoCuota;
	}

	public Date getFechaPagoEfectivo() {
		return fechaPagoEfectivo;
	}

	public void setFechaPagoEfectivo(Date fechaPagoEfectivo) {
		this.fechaPagoEfectivo = fechaPagoEfectivo;
	}

	public BigDecimal getAmortizacionCapital() {
		return amortizacionCapital;
	}

	public void setAmortizacionCapital(BigDecimal amortizacionCapital) {
		this.amortizacionCapital = amortizacionCapital;
	}

	public BigDecimal getInteresesCorridos() {
		return interesesCorridos;
	}

	public void setInteresesCorridos(BigDecimal interesesCorridos) {
		this.interesesCorridos = interesesCorridos;
	}

	public BigDecimal getComisionEnvioEstadoCuenta() {
		return comisionEnvioEstadoCuenta;
	}

	public void setComisionEnvioEstadoCuenta(BigDecimal comisionEnvioEstadoCuenta) {
		this.comisionEnvioEstadoCuenta = comisionEnvioEstadoCuenta;
	}

	public BigDecimal getPenalidad() {
		return penalidad;
	}

	public void setPenalidad(BigDecimal penalidad) {
		this.penalidad = penalidad;
	}

	public BigDecimal getCuotaSinSeguro() {
		return cuotaSinSeguro;
	}

	public void setCuotaSinSeguro(BigDecimal cuotaSinSeguro) {
		this.cuotaSinSeguro = cuotaSinSeguro;
	}

	public BigDecimal getSeguroDesgravamen() {
		return seguroDesgravamen;
	}

	public void setSeguroDesgravamen(BigDecimal seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}

	public Date getFechaProximaVencimiento() {
		return fechaProximaVencimiento;
	}

	public void setFechaProximaVencimiento(Date fechaProximaVencimiento) {
		this.fechaProximaVencimiento = fechaProximaVencimiento;
	}

	public BigDecimal getTcea() {
		return tcea;
	}

	public void setTcea(BigDecimal tcea) {
		this.tcea = tcea;
	}

	public Integer getNroCuotaProxima() {
		return nroCuotaProxima;
	}

	public void setNroCuotaProxima(Integer nroCuotaProxima) {
		this.nroCuotaProxima = nroCuotaProxima;
	}

	public String getNumeroComprobantePago() {
		return numeroComprobantePago;
	}

	public void setNumeroComprobantePago(String numeroComprobantePago) {
		this.numeroComprobantePago = numeroComprobantePago;
	}

	public BigDecimal getImporteTotalCuota() {
		return importeTotalCuota;
	}

	public void setImporteTotalCuota(BigDecimal importeTotalCuota) {
		this.importeTotalCuota = importeTotalCuota;
	}

	public BigDecimal getTea() {
		return tea;
	}

	public void setTea(BigDecimal tea) {
		this.tea = tea;
	}

	public Integer getCodigoDomicilio() {
		return codigoDomicilio;
	}

	public void setCodigoDomicilio(Integer codigoDomicilio) {
		this.codigoDomicilio = codigoDomicilio;
	}

	public String getNombreDireccion() {
		return nombreDireccion;
	}

	public void setNombreDireccion(String nombreDireccion) {
		this.nombreDireccion = nombreDireccion;
	}

	public String getCodigoUbigeo() {
		return codigoUbigeo;
	}

	public void setCodigoUbigeo(String codigoUbigeo) {
		this.codigoUbigeo = codigoUbigeo;
	}

	public Integer getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(Integer tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
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

	public Integer getEstadoEnvio() {
		return estadoEnvio;
	}

	public void setEstadoEnvio(Integer estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}

	public BigDecimal getImporteCuota() {
		return importeCuota;
	}

	public void setImporteCuota(BigDecimal importeCuota) {
		this.importeCuota = importeCuota;
	}

	public RutasPdfBean getRutasPdfBean() {
		return RutasPdfBean;
	}

	public void setRutasPdfBean(RutasPdfBean rutasPdfBean) {
		RutasPdfBean = rutasPdfBean;
	}

	public String getNombreArchivoComprobante() {
		return nombreArchivoComprobante;
	}

	public void setNombreArchivoComprobante(String nombreArchivoComprobante) {
		this.nombreArchivoComprobante = nombreArchivoComprobante;
	}

	public Integer getTipoPrepago() {
		return tipoPrepago;
	}

	public void setTipoPrepago(Integer tipoPrepago) {
		this.tipoPrepago = tipoPrepago;
	}

}
