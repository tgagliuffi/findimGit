package com.bbva.findim.dom;

public class CalificacionClienteBean {
	
	private String tpTipoDocumento;
	private String nuNumeroDocumento;
	private String idResultado;
	private String dsResultado;
	//ppazos
	private Integer codigoResultado;
	private Integer tipoRespuesta;
	private String  tituloMostrar;
	private String tipoError;
	
	private String descErrorServicio;
	
	public String getTipoError() {
		return tipoError;
	}
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
	}
	public String getTituloMostrar() {
		return tituloMostrar;
	}
	public void setTituloMostrar(String tituloMostrar) {
		this.tituloMostrar = tituloMostrar;
	}
	public Integer getTipoRespuesta() {
		return tipoRespuesta;
	}
	public void setTipoRespuesta(Integer tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
	public Integer getCodigoResultado() {
		return codigoResultado;
	}
	public void setCodigoResultado(Integer codigoResultado) {
		this.codigoResultado = codigoResultado;
//        if(this.getIdResultado()!=null && this.getIdResultado().equals("APPROVED")){
//			this.codigoResultado = 1;
//		}
//		if(this.getIdResultado()!=null && this.getIdResultado().equals("asda")){	
//			this.codigoResultado = 0;
//		}
	}
	public String getTpTipoDocumento() {
		return tpTipoDocumento;
	}
	public void setTpTipoDocumento(String tpTipoDocumento) {
		this.tpTipoDocumento = tpTipoDocumento;
	}
	public String getNuNumeroDocumento() {
		return nuNumeroDocumento;
	}
	public void setNuNumeroDocumento(String nuNumeroDocumento) {
		this.nuNumeroDocumento = nuNumeroDocumento;
	}
	public String getIdResultado() {
		return idResultado;
	}
	public void setIdResultado(String idResultado) {
		this.idResultado = idResultado;
	}
	public String getDsResultado() {
		return dsResultado;
	}
	public void setDsResultado(String dsResultado) {
		this.dsResultado = dsResultado;
	}
	public String getDescErrorServicio() {
		return descErrorServicio;
	}
	public void setDescErrorServicio(String descErrorServicio) {
		this.descErrorServicio = descErrorServicio;
	}
	
	
	
	

}
