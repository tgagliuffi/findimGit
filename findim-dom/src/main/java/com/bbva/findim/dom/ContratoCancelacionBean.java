package com.bbva.findim.dom;

public class ContratoCancelacionBean {

	private String codigoContrato;
	private Integer codigoPrepago;
	private Double montoTotalVencido;
	private Integer cantidadVencido;
	private Double montoTotalPenalidad;
	private Integer cantidadPenalidad;
	private Double importeCapital;
	private Double interesDevengado;
	private Double seguroDesgravamen;
	private Double comisionEnvio;
	private Double totalCancelado;
	private String numeroBoucher;
	private String fechaPago;
	private String fechaRegistroPago;
	private String origenFondo;
	private String medioPago;
	private String canal;
	private String rutaServicioRest;
	
	private String tipoDocumento;
	private String numeroDocumento;
	private String numeroContrato;
	private Integer idOrigenFondo;
	private Integer idMedioPago;
	private Integer idCanalOperacion;
	
	private String usuarioCreacion;
	private Integer tipoPreCancelacion;
	private Double importePrepago;
	private RutasPdfBean rutasPdfBean;
	private Integer idTipoCronograma;
	
	
	public String getCodigoContrato() {
		return codigoContrato;
	}
	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}
	
	public Double getMontoTotalVencido() {
		return montoTotalVencido;
	}
	public void setMontoTotalVencido(Double montoTotalVencido) {
		this.montoTotalVencido = montoTotalVencido;
	}
	public Integer getCantidadVencido() {
		return cantidadVencido;
	}
	public void setCantidadVencido(Integer cantidadVencido) {
		this.cantidadVencido = cantidadVencido;
	}
	public Double getMontoTotalPenalidad() {
		return montoTotalPenalidad;
	}
	public void setMontoTotalPenalidad(Double montoTotalPenalidad) {
		this.montoTotalPenalidad = montoTotalPenalidad;
	}
	public Integer getCantidadPenalidad() {
		return cantidadPenalidad;
	}
	public void setCantidadPenalidad(Integer cantidadPenalidad) {
		this.cantidadPenalidad = cantidadPenalidad;
	}
	public Double getImporteCapital() {
		return importeCapital;
	}
	public void setImporteCapital(Double importeCapital) {
		this.importeCapital = importeCapital;
	}
	public Double getInteresDevengado() {
		return interesDevengado;
	}
	public void setInteresDevengado(Double interesDevengado) {
		this.interesDevengado = interesDevengado;
	}
	
	public Double getSeguroDesgravamen() {
		return seguroDesgravamen;
	}
	public void setSeguroDesgravamen(Double seguroDesgravamen) {
		this.seguroDesgravamen = seguroDesgravamen;
	}
	public Double getComisionEnvio() {
		return comisionEnvio;
	}
	public void setComisionEnvio(Double comisionEnvio) {
		this.comisionEnvio = comisionEnvio;
	}
	
	public Double getTotalCancelado() {
		return totalCancelado;
	}
	public void setTotalCancelado(Double totalCancelado) {
		this.totalCancelado = totalCancelado;
	}
	public String getNumeroBoucher() {
		return numeroBoucher;
	}
	public void setNumeroBoucher(String numeroBoucher) {
		this.numeroBoucher = numeroBoucher;
	}
	public String getRutaServicioRest() {
		return rutaServicioRest;
	}
	public void setRutaServicioRest(String rutaServicioRest) {
		this.rutaServicioRest = rutaServicioRest;
	}
	
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getFechaRegistroPago() {
		return fechaRegistroPago;
	}
	public void setFechaRegistroPago(String fechaRegistroPago) {
		this.fechaRegistroPago = fechaRegistroPago;
	}
	public String getOrigenFondo() {
		return origenFondo;
	}
	public void setOrigenFondo(String origenFondo) {
		this.origenFondo = origenFondo;
	}
	public String getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public Integer getIdOrigenFondo() {
		return idOrigenFondo;
	}
	public void setIdOrigenFondo(Integer idOrigenFondo) {
		this.idOrigenFondo = idOrigenFondo;
	}
	public Integer getIdMedioPago() {
		return idMedioPago;
	}
	public void setIdMedioPago(Integer idMedioPago) {
		this.idMedioPago = idMedioPago;
	}
	public Integer getIdCanalOperacion() {
		return idCanalOperacion;
	}
	public void setIdCanalOperacion(Integer idCanalOperacion) {
		this.idCanalOperacion = idCanalOperacion;
	}
	
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	@Override
	public String toString() {
		return "ContratoCancelacionBean [codigoContrato=" + codigoContrato + ", montoTotalVencido=" + montoTotalVencido
				+ ", cantidadVencido=" + cantidadVencido + ", montoTotalPenalidad=" + montoTotalPenalidad
				+ ", cantidadPenalidad=" + cantidadPenalidad + ", importeCapital=" + importeCapital
				+ ", interesDevengado=" + interesDevengado + ", seguroDesgravamen=" + seguroDesgravamen
				+ ", comisionEnvio=" + comisionEnvio + ", totalCancelado=" + totalCancelado + ", numeroBoucher="
				+ numeroBoucher + ", fechaPago=" + fechaPago + ", fechaRegistroPago=" + fechaRegistroPago
				+ ", origenFondo=" + origenFondo + ", medioPago=" + medioPago + ", canal=" + canal
				+ ", rutaServicioRest=" + rutaServicioRest + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento="
				+ numeroDocumento + ", numeroContrato=" + numeroContrato + ", idOrigenFondo=" + idOrigenFondo
				+ ", idMedioPago=" + idMedioPago + ", idCanalOperacion=" + idCanalOperacion + ", usuarioCreacion="
				+ usuarioCreacion + " codigoPrepago =" + codigoPrepago+"]";
	}
	public Integer getTipoPreCancelacion() {
		return tipoPreCancelacion;
	}
	public void setTipoPreCancelacion(Integer tipoPreCancelacion) {
		this.tipoPreCancelacion = tipoPreCancelacion;
	}
	public Double getImportePrepago() {
		return importePrepago;
	}
	public void setImportePrepago(Double importePrepago) {
		this.importePrepago = importePrepago;
	}
	public RutasPdfBean getRutasPdfBean() {
		return rutasPdfBean;
	}
	public void setRutasPdfBean(RutasPdfBean rutasPdfBean) {
		this.rutasPdfBean = rutasPdfBean;
	}
	public Integer getCodigoPrepago() {
		return codigoPrepago;
	}
	public void setCodigoPrepago(Integer codigoPrepago) {
		this.codigoPrepago = codigoPrepago;
	}
	public Integer getIdTipoCronograma() {
		return idTipoCronograma;
	}
	public void setIdTipoCronograma(Integer idTipoCronograma) {
		this.idTipoCronograma = idTipoCronograma;
	}
}
