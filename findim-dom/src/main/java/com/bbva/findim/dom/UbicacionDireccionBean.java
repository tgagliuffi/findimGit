package com.bbva.findim.dom;

import java.util.List;

public class UbicacionDireccionBean {
	
	private String nbPais;
	private List<DireccionDetalleclienteBean> lstDetalleDireccion;
	private String dsReferencia;
	private String dsUbigeo;
	private String dsDireccionCompleta;
	
	public String getNbPais() {
		return nbPais;
	}
	public void setNbPais(String nbPais) {
		this.nbPais = nbPais;
	}
	public List<DireccionDetalleclienteBean> getLstDetalleDireccion() {
		return lstDetalleDireccion;
	}
	public void setLstDetalleDireccion(List<DireccionDetalleclienteBean> lstDetalleDireccion) {
		this.lstDetalleDireccion = lstDetalleDireccion;
	}
	public String getDsReferencia() {
		return dsReferencia;
	}
	public void setDsReferencia(String dsReferencia) {
		this.dsReferencia = dsReferencia;
	}
	public String getDsDireccionCompleta() {
		return dsDireccionCompleta;
	}
	public void setDsDireccionCompleta(String dsDireccionCompleta) {
		this.dsDireccionCompleta = dsDireccionCompleta;
	}
	public String getDsUbigeo() {
		return dsUbigeo;
	}
	public void setDsUbigeo(String dsUbigeo) {
		this.dsUbigeo = dsUbigeo;
	}
	
	
	
}
