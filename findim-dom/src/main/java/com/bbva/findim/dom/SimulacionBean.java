package com.bbva.findim.dom;

import java.util.List;

import com.bbva.findim.dom.CondicionBean;


public class SimulacionBean {
	private CabeceraBean cabecera;
	private TotalesBean totales;
	private List<DetalleBean> detalle;
	
	private String fechaPrestamo;
	private String fechaVencimiento;
	private String fechaNacimientoCliente;
	private List<TarifaBean> listaTarifa;
	
	private int codigoMensaje;
	private Integer tipoRespuesta;
	private Integer tipoError;
	private List<ParametroBean> listaComprobantePago; 
	
	private String idProducto;
	private String nbProducto;
	private String cdSubProducto;
	
	private List<CondicionBean> lstCondiciones;
	
	public List<TarifaBean> getListaTarifa() {
		return listaTarifa;
	}

	public void setListaTarifa(List<TarifaBean> listaTarifa) {
		this.listaTarifa = listaTarifa;
	}

	public String getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Integer getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(Integer tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public Integer getTipoError() {
		return tipoError;
	}

	public void setTipoError(Integer tipoError) {
		this.tipoError = tipoError;
	}

	public int getCodigoMensaje() {
		return codigoMensaje;
	}

	public void setCodigoMensaje(int codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	public TotalesBean getTotales() {
		return totales;
	}

	public void setTotales(TotalesBean totales) {
		this.totales = totales;
	}

	public CabeceraBean getCabecera() {
		return cabecera;
	}

	public void setCabecera(CabeceraBean cabecera) {
		this.cabecera = cabecera;
	}

	public List<DetalleBean> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleBean> detalle) {
		this.detalle = detalle;
	}	

	public List<ParametroBean> getListaComprobantePago() {
		return listaComprobantePago;
	}

	public void setListaComprobantePago(List<ParametroBean> listaComprobantePago) {
		this.listaComprobantePago = listaComprobantePago;
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

	public String getCdSubProducto() {
		return cdSubProducto;
	}

	public void setCdSubProducto(String cdSubProducto) {
		this.cdSubProducto = cdSubProducto;
	}

	
	public List<CondicionBean> getLstCondiciones() {
		return lstCondiciones;
	}

	public void setLstCondiciones(List<CondicionBean> lstCondiciones) {
		this.lstCondiciones = lstCondiciones;
	}

	@Override
	public String toString() {
		return "SimulacionBean [cabecera=" + cabecera + ", totales=" + totales + ", detalle=" + detalle
				+ ", fechaPrestamo=" + fechaPrestamo + ", fechaVencimiento=" + fechaVencimiento + ", ListaTarifa="
				+ listaTarifa + ", codigoMensaje=" + codigoMensaje + ", tipoRespuesta=" + tipoRespuesta + ", tipoError="
				+ tipoError + "]";
	}

	public String getFechaNacimientoCliente() {
		return fechaNacimientoCliente;
	}

	public void setFechaNacimientoCliente(String fechaNacimientoCliente) {
		this.fechaNacimientoCliente = fechaNacimientoCliente;
	}

	
}
