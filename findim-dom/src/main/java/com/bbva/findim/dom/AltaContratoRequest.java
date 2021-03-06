package com.bbva.findim.dom;

import java.io.Serializable;

public class AltaContratoRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String llaveUnica;
	private String atributo;
	private String tarifa;
	private Double importeBien;
	private Double importePrestamo;
	private Integer diaFacturacion;
	private Integer diaPagoCredito;
	private String tipoDocIdentidad;
	private String numeroDocIdentidad;
	private String direccion;
	private String departamento;
	private String provincia;
	private String distrito;
	private String telefonoFijo;
	private String telefonoReferencia;
	private String telefonoFinanciado;
	private String correo;
	private String tipoTransaccion;
	private String codigoCanal;
	private String nombreCanal;
	private Integer codigoEntidad;
	private String nombreEntidad;
	private Integer codigoPuntoVenta;
	private String nombrePuntoVenta;
	private Integer tipoEnvio;
	private String departamentoFirma;
	private String rtFiltroCliente;
	private String nbMotivoRechazo;
	private String tpIndicadorProceso;
	private String txCodigoError;
	private String txMensajeFuncional;
	private String txMensajeTecnico;

	public String getLlaveUnica() {
		return llaveUnica;
	}

	public void setLlaveUnica(String llaveUnica) {
		this.llaveUnica = llaveUnica;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getTarifa() {
		return tarifa;
	}

	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	public Double getImporteBien() {
		return importeBien;
	}

	public void setImporteBien(Double importeBien) {
		this.importeBien = importeBien;
	}

	public Double getImportePrestamo() {
		return importePrestamo;
	}

	public void setImportePrestamo(Double importePrestamo) {
		this.importePrestamo = importePrestamo;
	}

	public Integer getDiaFacturacion() {
		return diaFacturacion;
	}

	public void setDiaFacturacion(Integer diaFacturacion) {
		this.diaFacturacion = diaFacturacion;
	}

	public Integer getDiaPagoCredito() {
		return diaPagoCredito;
	}

	public void setDiaPagoCredito(Integer diaPagoCredito) {
		this.diaPagoCredito = diaPagoCredito;
	}

	public String getTipoDocIdentidad() {
		return tipoDocIdentidad;
	}

	public void setTipoDocIdentidad(String tipoDocIdentidad) {
		this.tipoDocIdentidad = tipoDocIdentidad;
	}

	public String getNumeroDocIdentidad() {
		return numeroDocIdentidad;
	}

	public void setNumeroDocIdentidad(String numeroDocIdentidad) {
		this.numeroDocIdentidad = numeroDocIdentidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoReferencia() {
		return telefonoReferencia;
	}

	public void setTelefonoReferencia(String telefonoReferencia) {
		this.telefonoReferencia = telefonoReferencia;
	}

	public String getTelefonoFinanciado() {
		return telefonoFinanciado;
	}

	public void setTelefonoFinanciado(String telefonoFinanciado) {
		this.telefonoFinanciado = telefonoFinanciado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getNombreCanal() {
		return nombreCanal;
	}

	public void setNombreCanal(String nombreCanal) {
		this.nombreCanal = nombreCanal;
	}

	public Integer getCodigoEntidad() {
		return codigoEntidad;
	}

	public void setCodigoEntidad(Integer codigoEntidad) {
		this.codigoEntidad = codigoEntidad;
	}

	public String getNombreEntidad() {
		return nombreEntidad;
	}

	public void setNombreEntidad(String nombreEntidad) {
		this.nombreEntidad = nombreEntidad;
	}

	public Integer getCodigoPuntoVenta() {
		return codigoPuntoVenta;
	}

	public void setCodigoPuntoVenta(Integer codigoPuntoVenta) {
		this.codigoPuntoVenta = codigoPuntoVenta;
	}

	public String getNombrePuntoVenta() {
		return nombrePuntoVenta;
	}

	public void setNombrePuntoVenta(String nombrePuntoVenta) {
		this.nombrePuntoVenta = nombrePuntoVenta;
	}

	public Integer getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(Integer tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	public String getDepartamentoFirma() {
		return departamentoFirma;
	}

	public void setDepartamentoFirma(String departamentoFirma) {
		this.departamentoFirma = departamentoFirma;
	}

	public String getTpIndicadorProceso() {
		return tpIndicadorProceso;
	}

	public void setTpIndicadorProceso(String tpIndicadorProceso) {
		this.tpIndicadorProceso = tpIndicadorProceso;
	}

	public String getTxMensajeFuncional() {
		return txMensajeFuncional;
	}

	public void setTxMensajeFuncional(String txMensajeFuncional) {
		this.txMensajeFuncional = txMensajeFuncional;
	}

	public String getTxMensajeTecnico() {
		return txMensajeTecnico;
	}

	public void setTxMensajeTecnico(String txMensajeTecnico) {
		this.txMensajeTecnico = txMensajeTecnico;
	}

	public String getRtFiltroCliente() {
		return rtFiltroCliente;
	}

	public void setRtFiltroCliente(String rtFiltroCliente) {
		this.rtFiltroCliente = rtFiltroCliente;
	}

	public String getNbMotivoRechazo() {
		return nbMotivoRechazo;
	}

	public void setNbMotivoRechazo(String nbMotivoRechazo) {
		this.nbMotivoRechazo = nbMotivoRechazo;
	}

	public String getTxCodigoError() {
		return txCodigoError;
	}

	public void setTxCodigoError(String txCodigoError) {
		this.txCodigoError = txCodigoError;
	}

	@Override
	public String toString() {
		return "AltaContratoRequest [llaveUnica=" + llaveUnica + ", atributo=" + atributo + ", tarifa=" + tarifa
				+ ", importeBien=" + importeBien + ", importePrestamo=" + importePrestamo + ", diaFacturacion="
				+ diaFacturacion + ", diaPagoCredito=" + diaPagoCredito + ", tipoDocIdentidad=" + tipoDocIdentidad
				+ ", numeroDocIdentidad=" + numeroDocIdentidad + ", direccion=" + direccion + ", departamento="
				+ departamento + ", provincia=" + provincia + ", distrito=" + distrito + ", telefonoFijo="
				+ telefonoFijo + ", telefonoReferencia=" + telefonoReferencia + ", telefonoFinanciado="
				+ telefonoFinanciado + ", correo=" + correo + ", tipoTransaccion=" + tipoTransaccion + ", codigoCanal="
				+ codigoCanal + ", nombreCanal=" + nombreCanal + ", codigoEntidad=" + codigoEntidad + ", nombreEntidad="
				+ nombreEntidad + ", codigoPuntoVenta=" + codigoPuntoVenta + ", nombrePuntoVenta=" + nombrePuntoVenta
				+ ", tipoEnvio=" + tipoEnvio + ", departamentoFirma=" + departamentoFirma + "]";
	}

}
