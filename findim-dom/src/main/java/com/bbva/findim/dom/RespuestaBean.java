package com.bbva.findim.dom;

public class RespuestaBean {

	private Integer indicadorProceso;
	private String mensajeFuncional;
	private String mensajeTecnico;
	private Float importeCuota;

	public RespuestaBean() {

	}

	public Integer getIndicadorProceso() {
		return indicadorProceso;
	}

	public void setIndicadorProceso(Integer indicadorProceso) {
		this.indicadorProceso = indicadorProceso;
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

	public Float getimporteCuota() {
		return importeCuota;
	}

	public void setimporteCuota(Float importeCuota) {
		this.importeCuota = importeCuota;
	}

}
