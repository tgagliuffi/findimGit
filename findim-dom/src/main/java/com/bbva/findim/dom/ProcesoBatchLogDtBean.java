package com.bbva.findim.dom;

import java.math.BigDecimal;

public class ProcesoBatchLogDtBean {

	private BigDecimal idProcesoDt;
    private String tarea;
    private String paso;
    private String idProceso;
    private String nombreArchivo;
    private String obsEstado;

    public BigDecimal getIdProcesoDt() {
        return idProcesoDt;
    }

    public void setIdProcesoDt(BigDecimal idProcesoDt) {
        this.idProcesoDt = idProcesoDt;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getPaso() {
        return paso;
    }

    public void setPaso(String paso) {
        this.paso = paso;
    }

    public String getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(String idProceso) {
        this.idProceso = idProceso;
    }
    

    public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getObsEstado() {
		return obsEstado;
	}

	public void setObsEstado(String obsEstado) {
		this.obsEstado = obsEstado;
	}

}
