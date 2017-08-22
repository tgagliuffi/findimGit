package com.bbva.findim.dom;

public class LogServicioBean {

	private String stObervacion;
	private String stInput;
	private String stOutput;
	private Integer inIdRespuesta;
	private Integer inTipoServicio;
	private String stIpHost;

	public Integer getInIdRespuesta() {
		return inIdRespuesta;
	}

	public void setInIdRespuesta(Integer inIdRespuesta) {
		this.inIdRespuesta = inIdRespuesta;
	}

	public String getStIpHost() {
		return stIpHost;
	}

	public void setStIpHost(String stIpHost) {
		this.stIpHost = stIpHost;
	}

	public String getStObervacion() {
		return stObervacion;
	}

	public void setStObervacion(String stObervacion) {
		this.stObervacion = stObervacion;
	}

	public String getStInput() {
		return stInput;
	}

	public void setStInput(String stInput) {
		this.stInput = stInput;
	}

	public String getStOutput() {
		return stOutput;
	}

	public void setStOutput(String stOutput) {
		this.stOutput = stOutput;
	}

	public Integer getInTipoServicio() {
		return inTipoServicio;
	}

	public void setInTipoServicio(Integer inTipoServicio) {
		this.inTipoServicio = inTipoServicio;
	}

}
