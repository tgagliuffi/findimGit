package com.bbva.findim.dom;

import java.util.Date;

public class DatosReniecBean {
	
	private String numeroDni;
	private String direccionAmdocs;
	private Date fechaCaducidad;
	public String getNumeroDni() {
		return numeroDni;
	}
	public void setNumeroDni(String numeroDni) {
		this.numeroDni = numeroDni;
	}
	public String getDireccionAmdocs() {
		return direccionAmdocs;
	}
	public void setDireccionAmdocs(String direccionAmdocs) {
		this.direccionAmdocs = direccionAmdocs;
	}
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	
}
