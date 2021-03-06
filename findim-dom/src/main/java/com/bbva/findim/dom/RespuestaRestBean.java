package com.bbva.findim.dom;

public class RespuestaRestBean<T> {

	// -1 error generico
	// 0 error de validaciones
	// 1 exitoso
	private Integer tipoRespuesta;
	private String mensajeFuncional;
	private String mensajeTecnico;

	private T objetoRespuesta;

	public RespuestaRestBean() {
	}

	public RespuestaRestBean(T objetoRespuesta) {
		super();
		this.objetoRespuesta = objetoRespuesta;
	}

	public Integer getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(Integer tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public String getMensajeFuncional() {
		return mensajeFuncional;
	}

	public void setMensajeFuncional(String mensajeFuncional) {
		this.mensajeFuncional = mensajeFuncional;
	}

	public String getMensajeTecnico() {
		return mensajeTecnico;
	}

	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}

	public String classType() {
		return "El tipo de T es " + objetoRespuesta.getClass().getName();
	}

	public T getObjetoRespuesta() {
		return objetoRespuesta;
	}

	public void setObjetoRespuesta(T objetoRespuesta) {
		this.objetoRespuesta = objetoRespuesta;
	}

}
