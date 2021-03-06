package com.bbva.findim.dom;

public class RespuestaWS {

	private String idContrato;
	private Integer idCliente;
	private Integer indicadorProceso;
	private String mensajeFuncional;
	private String mensajeTecnico;
	private Float numeroCuotas;
	private Integer registrosProcesados=0;
	private Integer tipoRespuesta;
	private Integer tipoError;
	private byte[] archivoPdfByte;
	private Integer idPrepago;
	private String nombreArchivoPrepago;
	private float importeCuota;
	
	public RespuestaWS() {
		this.indicadorProceso=0;
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

	public Float getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(Float numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String toString() {
		return "Mensaje Funcional:" + this.getMensajeFuncional();
	}

	public Integer getRegistrosProcesados() {
		return registrosProcesados;
	}

	public void setRegistrosProcesados(Integer registrosProcesados) {
		this.registrosProcesados = registrosProcesados;
	}

	public Integer getTipoRespuesta() {
		return tipoRespuesta;
	}

	public void setTipoRespuesta(Integer tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	public Integer getTipoError() {
		return tipoError;
	}

	public void setTipoError(Integer tipoError) {
		this.tipoError = tipoError;
	}

	public byte[] getArchivoPdfByte() {
		return archivoPdfByte;
	}

	public void setArchivoPdfByte(byte[] archivoPdfByte) {
		this.archivoPdfByte = archivoPdfByte;
	}

	public Integer getIdPrepago() {
		return idPrepago;
	}

	public void setIdPrepago(Integer idPrepago) {
		this.idPrepago = idPrepago;
	}

	public String getNombreArchivoPrepago() {
		return nombreArchivoPrepago;
	}

	public void setNombreArchivoPrepago(String nombreArchivoPrepago) {
		this.nombreArchivoPrepago = nombreArchivoPrepago;
	}

	public float getImporteCuota() {
		return importeCuota;
	}

	public void setImporteCuota(float importeCuota) {
		this.importeCuota = importeCuota;
	}

	

}
