package com.bbva.findim.dom;

import java.math.BigDecimal;
import java.util.Date;

public class ProcesoBatchLogBean {

	private BigDecimal idProceso;
    private String cdProceso;
    private String stProceso;
    private String obProceso;
    private String idTpProceso;
    private Date fhIniProceso;
    private Date fhFinProceso;

    public BigDecimal getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(BigDecimal idProceso) {
        this.idProceso = idProceso;
    }

    public String getCdProceso() {
        return cdProceso;
    }

    public void setCdProceso(String cdProceso) {
        this.cdProceso = cdProceso;
    }

    public String getStProceso() {
        return stProceso;
    }

    public void setStProceso(String stProceso) {
        this.stProceso = stProceso;
    }

    public String getObProceso() {
        return obProceso;
    }

    public void setObProceso(String obProceso) {
        this.obProceso = obProceso;
    }

    public String getIdTpProceso() {
        return idTpProceso;
    }

    public void setIdTpProceso(String idTpProceso) {
        this.idTpProceso = idTpProceso;
    }

    public Date getFhIniProceso() {
        return fhIniProceso;
    }

    public void setFhIniProceso(Date fhIniProceso) {
        this.fhIniProceso = fhIniProceso;
    }

    public Date getFhFinProceso() {
        return fhFinProceso;
    }

    public void setFhFinProceso(Date fhFinProceso) {
        this.fhFinProceso = fhFinProceso;
    }
}
