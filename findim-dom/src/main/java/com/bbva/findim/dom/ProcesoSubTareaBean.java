package com.bbva.findim.dom;

import java.math.BigDecimal;

public class ProcesoSubTareaBean {

	private BigDecimal idSubTarea;
    private String idTarea;
    private String nbSubTarea;
    private String stTarea;
    private String dtSubtarea;
    private String idPredecesora;
	
    public BigDecimal getIdSubTarea() {
		return idSubTarea;
	}
	public void setIdSubTarea(BigDecimal idSubTarea) {
		this.idSubTarea = idSubTarea;
	}
	public String getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(String idTarea) {
		this.idTarea = idTarea;
	}
	public String getNbSubTarea() {
		return nbSubTarea;
	}
	public void setNbSubTarea(String nbSubTarea) {
		this.nbSubTarea = nbSubTarea;
	}
	public String getStTarea() {
		return stTarea;
	}
	public void setStTarea(String stTarea) {
		this.stTarea = stTarea;
	}
	public String getDtSubtarea() {
		return dtSubtarea;
	}
	public void setDtSubtarea(String dtSubtarea) {
		this.dtSubtarea = dtSubtarea;
	}
	public String getIdPredecesora() {
		return idPredecesora;
	}
	public void setIdPredecesora(String idPredecesora) {
		this.idPredecesora = idPredecesora;
	}
    
}
