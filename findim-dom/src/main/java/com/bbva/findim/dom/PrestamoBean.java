package com.bbva.findim.dom;

import java.math.BigDecimal;
import java.util.Date;

public class PrestamoBean {
	
	//Claves del Prestamos
	private String prestamoId;
	
	//Datos del Cliente
	private String nbCliente;
	private String apPatCliente;
	private String apMatCliente;
	private String nbCalification;
	
	//Datos del Producto 
	private String idProducto;
	private String nbProducto;
	private String idClasifProducto;
	private String nbClasifProducto;
	
	//Datos de campaña
	private String nbCampana;
	
	//Administrador de Fechas
	private Date fhSolicitudPrestamo;
	private Date fhAprobacion;
	private Date fhDesembolso;
	private Date fhCambioInteres;
	private Date fhProLiquidacion;
	private Date fhUltLiquidacion;
	private Date fhUltOperacion;
	private Date fhAplazamientoPago;
	
	private String moneda;
	private String tipoMoneda;//tp_moneda =1 (Soles)
	
	
	private BigDecimal bdImportePrestamo;
	private BigDecimal impTCEA;
	private BigDecimal impTEA;
	private BigDecimal impIntMoratorio;
	
	//private BigDecimal mtoInteres;
	
	private Integer nuDiaPagoPrest;
	
	//indicadores
	private Boolean isSeguro;
	private Boolean isRefinanciado;
	private Boolean isJugador;
	private Boolean isVariableInteres;
	
	public String getPrestamoId() {
		return prestamoId;
	}
	public void setPrestamoId(String prestamoId) {
		this.prestamoId = prestamoId;
	}
	public String getNbCliente() {
		return nbCliente;
	}
	public void setNbCliente(String nbCliente) {
		this.nbCliente = nbCliente;
	}
	public String getApPatCliente() {
		return apPatCliente;
	}
	public void setApPatCliente(String apPatCliente) {
		this.apPatCliente = apPatCliente;
	}
	public String getApMatCliente() {
		return apMatCliente;
	}
	public void setApMatCliente(String apMatCliente) {
		this.apMatCliente = apMatCliente;
	}
	public String getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}
	public String getNbProducto() {
		return nbProducto;
	}
	public void setNbProducto(String nbProducto) {
		this.nbProducto = nbProducto;
	}
	public String getIdClasifProducto() {
		return idClasifProducto;
	}
	public void setIdClasifProducto(String idClasifProducto) {
		this.idClasifProducto = idClasifProducto;
	}
	public String getNbClasifProducto() {
		return nbClasifProducto;
	}
	public void setNbClasifProducto(String nbClasifProducto) {
		this.nbClasifProducto = nbClasifProducto;
	}
	public String getNbCampana() {
		return nbCampana;
	}
	public void setNbCampana(String nbCampana) {
		this.nbCampana = nbCampana;
	}
	public Date getFhSolicitudPrestamo() {
		return fhSolicitudPrestamo;
	}
	public void setFhSolicitudPrestamo(Date fhSolicitudPrestamo) {
		this.fhSolicitudPrestamo = fhSolicitudPrestamo;
	}
	public Date getFhAprobacion() {
		return fhAprobacion;
	}
	public void setFhAprobacion(Date fhAprobacion) {
		this.fhAprobacion = fhAprobacion;
	}
	public Date getFhDesembolso() {
		return fhDesembolso;
	}
	public void setFhDesembolso(Date fhDesembolso) {
		this.fhDesembolso = fhDesembolso;
	}
	public Date getFhCambioInteres() {
		return fhCambioInteres;
	}
	public void setFhCambioInteres(Date fhCambioInteres) {
		this.fhCambioInteres = fhCambioInteres;
	}
	public Date getFhProLiquidacion() {
		return fhProLiquidacion;
	}
	public void setFhProLiquidacion(Date fhProLiquidacion) {
		this.fhProLiquidacion = fhProLiquidacion;
	}
	public Date getFhUltLiquidacion() {
		return fhUltLiquidacion;
	}
	public void setFhUltLiquidacion(Date fhUltLiquidacion) {
		this.fhUltLiquidacion = fhUltLiquidacion;
	}
	public Date getFhUltOperacion() {
		return fhUltOperacion;
	}
	public void setFhUltOperacion(Date fhUltOperacion) {
		this.fhUltOperacion = fhUltOperacion;
	}
	public Date getFhAplazamientoPago() {
		return fhAplazamientoPago;
	}
	public void setFhAplazamientoPago(Date fhAplazamientoPago) {
		this.fhAplazamientoPago = fhAplazamientoPago;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	
	public BigDecimal getBdImportePrestamo() {
		return bdImportePrestamo;
	}
	public void setBdImportePrestamo(BigDecimal bdImportePrestamo) {
		this.bdImportePrestamo = bdImportePrestamo;
	}
	
	public BigDecimal getImpTCEA() {
		return impTCEA;
	}
	public void setImpTCEA(BigDecimal impTCEA) {
		this.impTCEA = impTCEA;
	}
	public BigDecimal getImpTEA() {
		return impTEA;
	}
	public void setImpTEA(BigDecimal impTEA) {
		this.impTEA = impTEA;
	}
	public String getTipoMoneda() {
		return tipoMoneda;
	}
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
	public String getTipoMenda(){
		return tipoMoneda;
	}
	public BigDecimal getImpIntMoratorio() {
		return impIntMoratorio;
	}
	public void setImpIntMoratorio(BigDecimal impIntMoratorio) {
		this.impIntMoratorio = impIntMoratorio;
	}
	public String getNbCalification() {
		return nbCalification;
	}
	public void setNbCalification(String nbCalification) {
		this.nbCalification = nbCalification;
	}
	public Integer getNuDiaPagoPrest() {
		return nuDiaPagoPrest;
	}
	public void setNuDiaPagoPrest(Integer nuDiaPagoPrest) {
		this.nuDiaPagoPrest = nuDiaPagoPrest;
	}
	public Boolean getIsSeguro() {
		return isSeguro;
	}
	public void setIsSeguro(Boolean isSeguro) {
		this.isSeguro = isSeguro;
	}
	public Boolean getIsRefinanciado() {
		return isRefinanciado;
	}
	public void setIsRefinanciado(Boolean isRefinanciado) {
		this.isRefinanciado = isRefinanciado;
	}
	public Boolean getIsJugador() {
		return isJugador;
	}
	public void setIsJugador(Boolean isJugador) {
		this.isJugador = isJugador;
	}
	public Boolean getIsVariableInteres() {
		return isVariableInteres;
	}
	public void setIsVariableInteres(Boolean isVariableInteres) {
		this.isVariableInteres = isVariableInteres;
	}
	
}
