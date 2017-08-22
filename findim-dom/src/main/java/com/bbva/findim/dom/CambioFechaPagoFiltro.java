package com.bbva.findim.dom;

public class CambioFechaPagoFiltro {
	private String fechaPago;
    private Double importePago =new Double(0.0);
    private String codigoContrato;
    private Integer tipoCronograma;
    private RutasPdfBean rutasPdfBean;
    private String usuarioCreacion; 
    private Integer diaCambioFechaPago;
    
	public Double getImportePago() {
		return importePago;
	}
	public void setImportePago(Double importePago) {
		this.importePago = importePago;
	}
	public String getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	public Integer getTipoCronograma() {
		return tipoCronograma;
	}
	public void setTipoCronograma(Integer tipoCronograma) {
		this.tipoCronograma = tipoCronograma;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public RutasPdfBean getRutasPdfBean() {
		return rutasPdfBean;
	}
	public void setRutasPdfBean(RutasPdfBean rutasPdfBean) {
		this.rutasPdfBean = rutasPdfBean;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public Integer getDiaCambioFechaPago() {
		return diaCambioFechaPago;
	}
	public void setDiaCambioFechaPago(Integer diaCambioFechaPago) {
		this.diaCambioFechaPago = diaCambioFechaPago;
	}	
}
