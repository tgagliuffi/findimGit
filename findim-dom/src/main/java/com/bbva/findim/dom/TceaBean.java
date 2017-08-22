package com.bbva.findim.dom;

import java.util.Date;
import java.util.List;

public class TceaBean {

	private Double montoTotal;
	private Date fechaCurse;
	private List<String> fechaCuotas;
	private List<Double> montoCuotas;
	private String wsdlLocation;
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Date getFechaCurse() {
		return fechaCurse;
	}
	public void setFechaCurse(Date fechaCurse) {
		this.fechaCurse = fechaCurse;
	}
	public List<String> getFechaCuotas() {
		return fechaCuotas;
	}
	public void setFechaCuotas(List<String> fechaCuotas) {
		this.fechaCuotas = fechaCuotas;
	}
	public List<Double> getMontoCuotas() {
		return montoCuotas;
	}
	public void setMontoCuotas(List<Double> montoCuotas) {
		this.montoCuotas = montoCuotas;
	}
	public String getWsdlLocation() {
		return wsdlLocation;
	}
	public void setWsdlLocation(String wsdlLocation) {
		this.wsdlLocation = wsdlLocation;
	}
	
	
	

}
