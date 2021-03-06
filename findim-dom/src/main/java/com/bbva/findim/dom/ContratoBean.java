package com.bbva.findim.dom;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ContratoBean {
	private String codigoContrato;
	private String descripcionBien;
	private String tipoMoneda;
	private String importeBien;
	private String importeInicial;
	private String importePrestamo;
	private String numeroCuotas;
	private String codigoTarifa;
	private String tasaFinanciamiento;
	private String valorTasaSeguro;
	private String descripcionTasaSeguro;
	private String tipoEnvio;
	private String fechaDesembolso;
	private String estadoContrato;
	private String codigoEstado;
	private String nombreArchivo;
	private String correo;
	private String fechaCreacion;
	private Double montoTotalFinanciado = new Double(0);
	private String tasaCosFinanciamiento;
	private List<DetalleBean> listaDetalleContrato;
	private ClienteBean clienteContrato;
	private Integer tipoRespuesta;
	private Integer tipoError;
	private String codigoAuxiliar;
	private Integer firmaContrato;
	private String boucherPagoAnticipado;
	private String fechaConsultaSaldo;
	private Integer cantidadAdeuda;
	private String motivoCancelacion;
	private Integer numeroVencido;
	
	private Double importeSaldoCapital;
	private Double importeCuota;
	private Double importeSeguroDesgravamen;
	private Double importeComisionEnvio;
	private Double importeValorCuota;
	private Double importeTotal;
	private Integer idTipoEnvio;
		
	private String rutaServicioRest;
	private Integer codigoCorreo;
	private Integer opcion;	
	private String idContrato;
	
	private Integer tipoDocumento;
	private String numeroDocumento;
	private Integer idEstadoContrato;
	//campos por tipo de datos
	
	private Date dtFechaDesembolso;
	private Double dbImporteBien;
	private Double dbImporteInicial;
	private Double dbImportePrestamo;
	private Integer intNumeroCuota;
	private Integer intCodigoEstado;
	private String nombrecompletoCliente;
	private Integer cuotasRestantes;
	
	private String ultimaFechaCancelado;
	private Double montoMinimoPrepagoParcial;
	private Double montoMaximoPrepagoParcial;
	private Integer numeroCuotasMinimaPrepagoParcial;
	
	private String fechaPago;
	private List<EventoContratoBean> listaEventoContrato;
	
	private String moneda;
	
	private BigDecimal bdImportePrestamo;
	private BigDecimal bdTCEA;
	private BigDecimal bdTEA;
	private Date fechaVencimientoMesActual;
	
	
	//campos nuevos
	private String monedaCuotaInicial;
	private String monedaCantidadInicial;
	private String idProductoFinanciadoExt;
	private Double impProductoFinanciadoExt;
	private String monedaProductoFinanciadoExt;
	
	private String nombreEnvio;
	private String idTipoOcupacion;
	
	public String getIdTipoOcupacion() {
		return idTipoOcupacion;
	}

	public void setIdTipoOcupacion(String idTipoOcupacion) {
		this.idTipoOcupacion = idTipoOcupacion;
	}

	public String getNombreEnvio() {
		return nombreEnvio;
	}

	public void setNombreEnvio(String nombreEnvio) {
		this.nombreEnvio = nombreEnvio;
	}

	public ContratoBean() {
		clienteContrato = new ClienteBean();
	}

	public ContratoBean(String codigoContrato, String descripcionBien, String tipoMoneda, String importeBien,
 			String importeInicial, String importePrestamo, String numeroCuotas, String codigoTarifa,
			String tasaFinanciamiento, String valorTasaSeguro, String descripcionTasaSeguro, String tipoEnvio,
			String fechaDesembolso, String estadoContrato, String codigoEstado, String nombreArchivo, String correo,
			String fechaCreacion, Double montoTotalFinanciado, String tasaCosFinanciamiento, 
			List<DetalleBean> listaDetalleContrato, ClienteBean clienteContrato, Integer tipoRespuesta,
			Integer tipoError, Integer firmaContrato, String fechaConsultaSaldo) {
		
		super();
		this.codigoContrato = codigoContrato;
		this.descripcionBien = descripcionBien;
		this.tipoMoneda = tipoMoneda;
		this.importeBien = importeBien;
		this.importeInicial = importeInicial;
		this.importePrestamo = importePrestamo;
		this.numeroCuotas = numeroCuotas;
		this.codigoTarifa = codigoTarifa;
		this.tasaFinanciamiento = tasaFinanciamiento;
		this.valorTasaSeguro = valorTasaSeguro;
		this.descripcionTasaSeguro = descripcionTasaSeguro;
		this.tipoEnvio = tipoEnvio;
		this.fechaDesembolso = fechaDesembolso;
		this.estadoContrato = estadoContrato;
		this.codigoEstado = codigoEstado;
		this.nombreArchivo = nombreArchivo;
		this.correo = correo;
		this.fechaCreacion = fechaCreacion;
		this.montoTotalFinanciado = montoTotalFinanciado;
		this.tasaCosFinanciamiento = tasaCosFinanciamiento;
		this.listaDetalleContrato = listaDetalleContrato;
		this.clienteContrato = clienteContrato;
		this.tipoRespuesta = tipoRespuesta;
		this.tipoError = tipoError;
		this.firmaContrato = firmaContrato;
		this.fechaConsultaSaldo = fechaConsultaSaldo;	
	}


	public String getCodigoContrato() {
		return codigoContrato;
	}


	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}

	public String getDescripcionBien() {
		return descripcionBien;
	}


	public void setDescripcionBien(String descripcionBien) {
		this.descripcionBien = descripcionBien;
	}


	public String getTipoMoneda() {
		return tipoMoneda;
	}


	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}


	public String getImporteBien() {
		return importeBien;
	}


	public void setImporteBien(String importeBien) {
		this.importeBien = importeBien;
	}


	public String getImporteInicial() {
		return importeInicial;
	}

	public void setImporteInicial(String importeInicial) {
		this.importeInicial = importeInicial;
	}

	public String getImportePrestamo() {
		return importePrestamo;
	}

	public void setImportePrestamo(String importePrestamo) {
		this.importePrestamo = importePrestamo;
	}

	public String getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(String numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public String getCodigoTarifa() {
		return codigoTarifa;
	}

	public void setCodigoTarifa(String codigoTarifa) {
		this.codigoTarifa = codigoTarifa;
	}

	public String getTasaFinanciamiento() {
		return tasaFinanciamiento;
	}


	public void setTasaFinanciamiento(String tasaFinanciamiento) {
		this.tasaFinanciamiento = tasaFinanciamiento;
	}


	public String getValorTasaSeguro() {
		return valorTasaSeguro;
	}


	public void setValorTasaSeguro(String valorTasaSeguro) {
		this.valorTasaSeguro = valorTasaSeguro;
	}


	public String getDescripcionTasaSeguro() {
		return descripcionTasaSeguro;
	}


	public void setDescripcionTasaSeguro(String descripcionTasaSeguro) {
		this.descripcionTasaSeguro = descripcionTasaSeguro;
	}


	public String getTipoEnvio() {
		return tipoEnvio;
	}


	public void setTipoEnvio(String tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}


	public String getFechaDesembolso() {
		return fechaDesembolso;
	}


	public void setFechaDesembolso(String fechaDesembolso) {
		this.fechaDesembolso = fechaDesembolso;
	}


	public String getEstadoContrato() {
		return estadoContrato;
	}


	public void setEstadoContrato(String estadoContrato) {
		this.estadoContrato = estadoContrato;
	}


	public String getCodigoEstado() {
		return codigoEstado;
	}


	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Double getMontoTotalFinanciado() {
		return montoTotalFinanciado;
	}

	public void setMontoTotalFinanciado(Double montoTotalFinanciado) {
		this.montoTotalFinanciado = montoTotalFinanciado;
	}

	public String getTasaCosFinanciamiento() {
		return tasaCosFinanciamiento;
	}

	public void setTasaCosFinanciamiento(String tasaCosFinanciamiento) {
		this.tasaCosFinanciamiento = tasaCosFinanciamiento;
	}

	public List<DetalleBean> getListaDetalleContrato() {
		return listaDetalleContrato;
	}

	public void setListaDetalleContrato(List<DetalleBean> listaDetalleContrato) {
		this.listaDetalleContrato = listaDetalleContrato;
	}

	public ClienteBean getClienteContrato() {
		return clienteContrato;
	}

	public void setClienteContrato(ClienteBean clienteContrato) {
		this.clienteContrato = clienteContrato;
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

	public String getCodigoAuxiliar() {
		return codigoAuxiliar;
	}

	public void setCodigoAuxiliar(String codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}

	public Integer getFirmaContrato() {
		return firmaContrato;
	}

	public void setFirmaContrato(Integer firmaContrato) {
		this.firmaContrato = firmaContrato;
	}

	public String getBoucherPagoAnticipado() {
		return boucherPagoAnticipado;
	}

	public void setBoucherPagoAnticipado(String boucherPagoAnticipado) {
		this.boucherPagoAnticipado = boucherPagoAnticipado;
	}
	
	public String getFechaConsultaSaldo() {
		return fechaConsultaSaldo;
	}

	public void setFechaConsultaSaldo(String fechaConsultaSaldo) {
		this.fechaConsultaSaldo = fechaConsultaSaldo;
	}

	public Integer getCantidadAdeuda() {
		return cantidadAdeuda;
	}

	public void setCantidadAdeuda(Integer cantidadAdeuda) {
		this.cantidadAdeuda = cantidadAdeuda;
	}

	public String getMotivoCancelacion() {
		return motivoCancelacion;
	}

	public void setMotivoCancelacion(String motivoCancelacion) {
		this.motivoCancelacion = motivoCancelacion;
	}

	public Integer getNumeroVencido() {
		return numeroVencido;
	}

	public void setNumeroVencido(Integer numeroVencido) {
		this.numeroVencido = numeroVencido;
	}

	public Double getImporteSaldoCapital() {
		return importeSaldoCapital;
	}

	public void setImporteSaldoCapital(Double importeSaldoCapital) {
		this.importeSaldoCapital = importeSaldoCapital;
	}

	public Double getImporteCuota() {
		return importeCuota;
	}

	public void setImporteCuota(Double importeCuota) {
		this.importeCuota = importeCuota;
	}

	public Double getImporteSeguroDesgravamen() {
		return importeSeguroDesgravamen;
	}

	public void setImporteSeguroDesgravamen(Double importeSeguroDesgravamen) {
		this.importeSeguroDesgravamen = importeSeguroDesgravamen;
	}

	public Double getImporteComisionEnvio() {
		return importeComisionEnvio;
	}

	public void setImporteComisionEnvio(Double importeComisionEnvio) {
		this.importeComisionEnvio = importeComisionEnvio;
	}

	public Double getImporteValorCuota() {
		return importeValorCuota;
	}

	public void setImporteValorCuota(Double importeValorCuota) {
		this.importeValorCuota = importeValorCuota;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Integer getIdTipoEnvio() {
		return idTipoEnvio;
	}

	public void setIdTipoEnvio(Integer idTipoEnvio) {
		this.idTipoEnvio = idTipoEnvio;
	}

	public String getRutaServicioRest() {
		return rutaServicioRest;
	}

	public void setRutaServicioRest(String rutaServicioRest) {
		this.rutaServicioRest = rutaServicioRest;
	}

	public Integer getCodigoCorreo() {
		return codigoCorreo;
	}

	public void setCodigoCorreo(Integer codigoCorreo) {
		this.codigoCorreo = codigoCorreo;
	}

	public Integer getOpcion() {
		return opcion;
	}

	public void setOpcion(Integer opcion) {
		this.opcion = opcion;
	}

	public String getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}

	public Date getDtFechaDesembolso() {
		return dtFechaDesembolso;
	}

	public void setDtFechaDesembolso(Date dtFechaDesembolso) {
		this.dtFechaDesembolso = dtFechaDesembolso;
	}

	public Double getDbImporteBien() {
		return dbImporteBien;
	}

	public void setDbImporteBien(Double dbImporteBien) {
		this.dbImporteBien = dbImporteBien;
	}

	public Double getDbImporteInicial() {
		return dbImporteInicial;
	}

	public void setDbImporteInicial(Double dbImporteInicial) {
		this.dbImporteInicial = dbImporteInicial;
	}

	public Double getDbImportePrestamo() {
		return dbImportePrestamo;
	}

	public void setDbImportePrestamo(Double dbImportePrestamo) {
		this.dbImportePrestamo = dbImportePrestamo;
	}

	public Integer getIntNumeroCuota() {
		return intNumeroCuota;
	}

	public void setIntNumeroCuota(Integer intNumeroCuota) {
		this.intNumeroCuota = intNumeroCuota;
	}

	public Integer getIntCodigoEstado() {
		return intCodigoEstado;
	}

	public void setIntCodigoEstado(Integer intCodigoEstado) {
		this.intCodigoEstado = intCodigoEstado;
	}

	public String getNombrecompletoCliente() {
		return nombrecompletoCliente;
	}

	public void setNombrecompletoCliente(String nombrecompletoCliente) {
		this.nombrecompletoCliente = nombrecompletoCliente;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Integer getIdEstadoContrato() {
		return idEstadoContrato;
	}

	public void setIdEstadoContrato(Integer idEstadoContrato) {
		this.idEstadoContrato = idEstadoContrato;
	}

	public Integer getCuotasRestantes() {
		return cuotasRestantes;
	}

	public void setCuotasRestantes(Integer cuotasRestantes) {
		this.cuotasRestantes = cuotasRestantes;
	}

	public String getUltimaFechaCancelado() {
		return ultimaFechaCancelado;
	}

	public void setUltimaFechaCancelado(String ultimaFechaCancelado) {
		this.ultimaFechaCancelado = ultimaFechaCancelado;
	}

	public Double getMontoMinimoPrepagoParcial() {
		return montoMinimoPrepagoParcial;
	}

	public void setMontoMinimoPrepagoParcial(Double montoMinimoPrepagoParcial) {
		this.montoMinimoPrepagoParcial = montoMinimoPrepagoParcial;
	}

	public Double getMontoMaximoPrepagoParcial() {
		return montoMaximoPrepagoParcial;
	}

	public void setMontoMaximoPrepagoParcial(Double montoMaximoPrepagoParcial) {
		this.montoMaximoPrepagoParcial = montoMaximoPrepagoParcial;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Integer getNumeroCuotasMinimaPrepagoParcial() {
		return numeroCuotasMinimaPrepagoParcial;
	}

	public void setNumeroCuotasMinimaPrepagoParcial(Integer numeroCuotasMinimaPrepagoParcial) {
		this.numeroCuotasMinimaPrepagoParcial = numeroCuotasMinimaPrepagoParcial;
	}

	public List<EventoContratoBean> getListaEventoContrato() {
		return listaEventoContrato;
	}

	public void setListaEventoContrato(List<EventoContratoBean> listaEventoContrato) {
		this.listaEventoContrato = listaEventoContrato;
	}

	public BigDecimal getBdImportePrestamo() {
		return bdImportePrestamo;
	}

	public void setBdImportePrestamo(BigDecimal bdImportePrestamo) {
		this.bdImportePrestamo = bdImportePrestamo;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public BigDecimal getBdTCEA() {
		return bdTCEA;
	}

	public void setBdTCEA(BigDecimal bdTCEA) {
		this.bdTCEA = bdTCEA;
	}

	public BigDecimal getBdTEA() {
		return bdTEA;
	}

	public void setBdTEA(BigDecimal bdTEA) {
		this.bdTEA = bdTEA;
	}

	public Date getFechaVencimientoMesActual() {
		return fechaVencimientoMesActual;
	}

	public void setFechaVencimientoMesActual(Date fechaVencimientoMesActual) {
		this.fechaVencimientoMesActual = fechaVencimientoMesActual;
	}

	public String getMonedaCuotaInicial() {
		return monedaCuotaInicial;
	}

	public void setMonedaCuotaInicial(String monedaCuotaInicial) {
		this.monedaCuotaInicial = monedaCuotaInicial;
	}

	public String getMonedaCantidadInicial() {
		return monedaCantidadInicial;
	}

	public void setMonedaCantidadInicial(String monedaCantidadInicial) {
		this.monedaCantidadInicial = monedaCantidadInicial;
	}

	public String getIdProductoFinanciadoExt() {
		return idProductoFinanciadoExt;
	}

	public void setIdProductoFinanciadoExt(String idProductoFinanciadoExt) {
		this.idProductoFinanciadoExt = idProductoFinanciadoExt;
	}

	public Double getImpProductoFinanciadoExt() {
		return impProductoFinanciadoExt;
	}

	public void setImpProductoFinanciadoExt(Double impProductoFinanciadoExt) {
		this.impProductoFinanciadoExt = impProductoFinanciadoExt;
	}

	public String getMonedaProductoFinanciadoExt() {
		return monedaProductoFinanciadoExt;
	}

	public void setMonedaProductoFinanciadoExt(String monedaProductoFinanciadoExt) {
		this.monedaProductoFinanciadoExt = monedaProductoFinanciadoExt;
	}	
	
	
}
