package com.bbva.findim.dom;

import java.util.List;

public class DatosLaboralesBean {

	private String idOcupacion;
	private List<IngresosBean> lstIngresos;
	private String centroLaboral;
	
	public String getIdOcupacion() {
		return idOcupacion;
	}
	public void setIdOcupacion(String idOcupacion) {
		this.idOcupacion = idOcupacion;
	}
	public List<IngresosBean> getLstIngresos() {
		return lstIngresos;
	}
	public void setLstIngresos(List<IngresosBean> lstIngresos) {
		this.lstIngresos = lstIngresos;
	}
	public String getCentroLaboral() {
		return centroLaboral;
	}
	public void setCentroLaboral(String centroLaboral) {
		this.centroLaboral = centroLaboral;
	}
  
  
}
