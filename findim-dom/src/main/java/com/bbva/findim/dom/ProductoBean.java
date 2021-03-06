package com.bbva.findim.dom;

import java.util.List;

public class ProductoBean {
	
	private String productoId;
	private String productoNombre;
	private List<ClasificacionComercialBean> clasificacioncComercial;
	
	public String getProductoId() {
		return productoId;
	}
	public void setProductoId(String productoId) {
		this.productoId = productoId;
	}
	public String getProductoNombre() {
		return productoNombre;
	}
	public void setProductoNombre(String productoNombre) {
		this.productoNombre = productoNombre;
	}
	public List<ClasificacionComercialBean> getClasificacioncComercial() {
		return clasificacioncComercial;
	}
	public void setClasificacioncComercial(List<ClasificacionComercialBean> clasificacioncComercial) {
		this.clasificacioncComercial = clasificacioncComercial;
	}
}
