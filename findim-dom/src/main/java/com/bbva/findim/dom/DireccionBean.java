package com.bbva.findim.dom;

import java.util.List;

public class DireccionBean {
	
	private String dsDireccion;
	private String idTipoDireccion;
	private String idTipoPropiedad;
	private String idCountry;
	private String rfAdicionalInfo;
	private String idTipoGrupoGeo;
	private String nbTipoGrupoGeo;
	
	private List<GrupoGeografico> lstGrupoGeografico;
	
	
	//ppazos(datos mostrar en web)
	private String numeroExterno;
	private String indicador;
	private String nombreCalle;

	
	public String getNumeroExterno() {
		return numeroExterno;
	}
	public void setNumeroExterno(String numeroExterno) {
		this.numeroExterno = numeroExterno;
	}
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public String getNombreCalle() {
		return nombreCalle;
	}
	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}
	public List<GrupoGeografico> getLstGrupoGeografico() {
	return lstGrupoGeografico;
	}
	public void setLstGrupoGeografico(List<GrupoGeografico> lstGrupoGeografico) {
	this.lstGrupoGeografico = lstGrupoGeografico;
	}
	
	public String getDsDireccion() {
		return dsDireccion;
	}
	public void setDsDireccion(String dsDireccion) {
		this.dsDireccion = dsDireccion;
	}
	public String getIdTipoDireccion() {
		return idTipoDireccion;
	}
	public void setIdTipoDireccion(String idTipoDireccion) {
		this.idTipoDireccion = idTipoDireccion;
	}
	public String getIdTipoPropiedad() {
		return idTipoPropiedad;
	}
	public void setIdTipoPropiedad(String idTipoPropiedad) {
		this.idTipoPropiedad = idTipoPropiedad;
	}
	public String getIdCountry() {
		return idCountry;
	}
	public void setIdCountry(String idCountry) {
		this.idCountry = idCountry;
	}
	public String getIdTipoGrupoGeo() {
		return idTipoGrupoGeo;
	}
	public void setIdTipoGrupoGeo(String idTipoGrupoGeo) {
		this.idTipoGrupoGeo = idTipoGrupoGeo;
	}
	public String getNbTipoGrupoGeo() {
		return nbTipoGrupoGeo;
	}
	public void setNbTipoGrupoGeo(String nbTipoGrupoGeo) {
		this.nbTipoGrupoGeo = nbTipoGrupoGeo;
	}
	public String getRfAdicionalInfo() {
		return rfAdicionalInfo;
	}
	public void setRfAdicionalInfo(String rfAdicionalInfo) {
		this.rfAdicionalInfo = rfAdicionalInfo;
	}
	
}
