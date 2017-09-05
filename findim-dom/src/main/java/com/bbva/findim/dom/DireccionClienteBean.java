package com.bbva.findim.dom;

public class DireccionClienteBean {
	
	private String idDireccion;
	private String idTipoDireccion;
	private String nbTipoDireccion;
	
	private String idTipoPropiedad;
	private String nbTipoPropiedad;
	
	private String strDireccionInputTlf;
	
	private UbicacionDireccionBean ubicacion;

	public String getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(String idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getIdTipoDireccion() {
		return idTipoDireccion;
	}

	public void setIdTipoDireccion(String idTipoDireccion) {
		this.idTipoDireccion = idTipoDireccion;
	}

	public String getNbTipoDireccion() {
		return nbTipoDireccion;
	}

	public void setNbTipoDireccion(String nbTipoDireccion) {
		this.nbTipoDireccion = nbTipoDireccion;
	}

	public String getIdTipoPropiedad() {
		return idTipoPropiedad;
	}

	public void setIdTipoPropiedad(String idTipoPropiedad) {
		this.idTipoPropiedad = idTipoPropiedad;
	}

	public String getNbTipoPropiedad() {
		return nbTipoPropiedad;
	}

	public void setNbTipoPropiedad(String nbTipoPropiedad) {
		this.nbTipoPropiedad = nbTipoPropiedad;
	}

	public UbicacionDireccionBean getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(UbicacionDireccionBean ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getStrDireccionInputTlf() {
		return strDireccionInputTlf;
	}

	public void setStrDireccionInputTlf(String strDireccionInputTlf) {
		this.strDireccionInputTlf = strDireccionInputTlf;
	} 
	
	
	
}
