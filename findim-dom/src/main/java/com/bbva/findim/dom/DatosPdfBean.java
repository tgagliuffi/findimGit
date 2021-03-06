package com.bbva.findim.dom;

import java.util.Date;
import java.util.List;

public class DatosPdfBean {
	private String nombreCompleto;
	private String codigoContrato;
	private Integer codigoCliente;
	private String correoCliente;
	private Integer codigoTipoEnvio;
	private Integer codigoTipoContrato;
	private String centroLaboral;
	private String cargo;
	private String profesionPdf;
	private String tipoMonedaPdf;
	private String simboloMonedaPdf;	
	private Double importePrestamo;
	private String tipoPrestamoPdf;
	private String tipoSeguroPdf;
	private String fechaDesembolsoPdf;
	private Double tea;
	private Double tcea;
	private String numeroDocumentoPdf;
	private String tipoDocumento;
	private String auxCodigoContratoPdf;
	private Integer numeroCuotas;
	private List<DetalleBean> listaCuota;
	private Date fechaFormalizacion;
	private String fechaPrimerVencimiento;
	private String fechaFinalContrato;
	private Double totalInteresCompensatorio;
	private Double importeSeguro;
	private Double montoTotalCuota;
	private Double montoTotalPagar;
	private String rutaGeneracionContrato;
	private String rutaImagen;
	private String rutacontratos;
	private String rutatemp;
	private String rutaPdf;
	private String rutaFija;

	private String RUC;
	private String domicilio;
	private String nombreRepresentante1;
	private String dniRepresentante1;
	private String poderRepresentante1;
	private String nombreRepresentante2;
	private String dniRepresentante2;
	private String poderRepresentante2;
	private String nombreCompania;
	private String numeroPoliza;

	private Double precioEquipo;
	private Double cuotaInicial;
	private Integer diasPago;
	private String fechaNacimiento;
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;

	private String estadoCivil;
	private String direccion;
	private Double primaMensual;
	private Integer tipoEnvio;
	private String distrito;
	private String provincia;
	private String departamento;
	private String fechaActual;
	private String dia;
	private String mes;
	private String anio;
	private RutasPdfBean RutasPdfBean;

	private Double penalidad;
	private Double costoEnvio;
	private String codigoSexo;
	private String codigoEstadoCivil;
	private Integer idProfesion;

	private DatosComplementariosBean datosComplementariosBean;
	private String fechaVenta;
	private String fechaReDesembolso;
	private Double totalMontoAmortizacion;
	private Double totalMontoInteresDevengado;
	private Double totalMontoSeguroDesgravamen;
	private Double totalMontoGastosComisiones;
	private Double totalMontoCuotas;

	public Double getPenalidad() {
		return penalidad;
	}

	public void setPenalidad(Double penalidad) {
		this.penalidad = penalidad;
	}

	public Double getCostoEnvio() {
		return costoEnvio;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public void setCostoEnvio(Double costoEnvio) {
		this.costoEnvio = costoEnvio;
	}

	public String getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(String numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public RutasPdfBean getRutasPdfBean() {
		return RutasPdfBean;
	}

	public void setRutasPdfBean(RutasPdfBean rutasPdfBean) {
		RutasPdfBean = rutasPdfBean;
	}

	public Double getCuotaInicial() {
		return cuotaInicial;
	}

	public void setCuotaInicial(Double cuotaInicial) {
		this.cuotaInicial = cuotaInicial;
	}

	public Double getPrecioEquipo() {
		return precioEquipo;
	}

	public void setPrecioEquipo(Double precioEquipo) {
		this.precioEquipo = precioEquipo;
	}

	public Integer getDiasPago() {
		return diasPago;
	}

	public void setDiasPago(Integer diasPago) {
		this.diasPago = diasPago;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getPrimaMensual() {
		return primaMensual;
	}

	public void setPrimaMensual(Double primaMensual) {
		this.primaMensual = primaMensual;
	}

	public Integer getTipoEnvio() {
		return tipoEnvio;
	}

	public void setTipoEnvio(Integer tipoEnvio) {
		this.tipoEnvio = tipoEnvio;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCodigoContrato() {
		return codigoContrato;
	}

	public void setCodigoContrato(String codigoContrato) {
		this.codigoContrato = codigoContrato;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	public Integer getCodigoTipoEnvio() {
		return codigoTipoEnvio;
	}

	public void setCodigoTipoEnvio(Integer codigoTipoEnvio) {
		this.codigoTipoEnvio = codigoTipoEnvio;
	}

	public Integer getCodigoTipoContrato() {
		return codigoTipoContrato;
	}

	public void setCodigoTipoContrato(Integer codigoTipoContrato) {
		this.codigoTipoContrato = codigoTipoContrato;
	}

	public String getCentroLaboral() {
		return centroLaboral;
	}

	public void setCentroLaboral(String centroLaboral) {
		this.centroLaboral = centroLaboral;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getProfesionPdf() {
		return profesionPdf;
	}

	public void setProfesionPdf(String profesionPdf) {
		this.profesionPdf = profesionPdf;
	}

	public String getTipoMonedaPdf() {
		return tipoMonedaPdf;
	}

	public void setTipoMonedaPdf(String tipoMonedaPdf) {
		this.tipoMonedaPdf = tipoMonedaPdf;
	}

	public Double getImportePrestamo() {
		return importePrestamo;
	}

	public void setImportePrestamo(Double importePrestamo) {
		this.importePrestamo = importePrestamo;
	}

	public String getTipoPrestamoPdf() {
		return tipoPrestamoPdf;
	}

	public void setTipoPrestamoPdf(String tipoPrestamoPdf) {
		this.tipoPrestamoPdf = tipoPrestamoPdf;
	}

	public String getTipoSeguroPdf() {
		return tipoSeguroPdf;
	}

	public void setTipoSeguroPdf(String tipoSeguroPdf) {
		this.tipoSeguroPdf = tipoSeguroPdf;
	}

	public String getFechaDesembolsoPdf() {
		return fechaDesembolsoPdf;
	}

	public void setFechaDesembolsoPdf(String fechaDesembolsoPdf) {
		this.fechaDesembolsoPdf = fechaDesembolsoPdf;
	}

	public String getNumeroDocumentoPdf() {
		return numeroDocumentoPdf;
	}

	public void setNumeroDocumentoPdf(String numeroDocumentoPdf) {
		this.numeroDocumentoPdf = numeroDocumentoPdf;
	}

	public String getAuxCodigoContratoPdf() {
		return auxCodigoContratoPdf;
	}

	public void setAuxCodigoContratoPdf(String auxCodigoContratoPdf) {
		this.auxCodigoContratoPdf = auxCodigoContratoPdf;
	}

	public Double getTea() {
		return tea;
	}

	public void setTea(Double tea) {
		this.tea = tea;
	}

	public Double getTcea() {
		return tcea;
	}

	public void setTcea(Double tcea) {
		this.tcea = tcea;
	}

	public Integer getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(Integer numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public List<DetalleBean> getListaCuota() {
		return listaCuota;
	}

	public void setListaCuota(List<DetalleBean> listaCuota) {
		this.listaCuota = listaCuota;
	}

	public Date getFechaFormalizacion() {
		return fechaFormalizacion;
	}

	public void setFechaFormalizacion(Date fechaFormalizacion) {
		this.fechaFormalizacion = fechaFormalizacion;
	}

	public String getFechaPrimerVencimiento() {
		return fechaPrimerVencimiento;
	}

	public void setFechaPrimerVencimiento(String fechaPrimerVencimiento) {
		this.fechaPrimerVencimiento = fechaPrimerVencimiento;
	}

	public String getFechaFinalContrato() {
		return fechaFinalContrato;
	}

	public void setFechaFinalContrato(String fechaFinalContrato) {
		this.fechaFinalContrato = fechaFinalContrato;
	}

	public Double getTotalInteresCompensatorio() {
		return totalInteresCompensatorio;
	}

	public void setTotalInteresCompensatorio(Double totalInteresCompensatorio) {
		this.totalInteresCompensatorio = totalInteresCompensatorio;
	}

	public Double getImporteSeguro() {
		return importeSeguro;
	}

	public void setImporteSeguro(Double importeSeguro) {
		this.importeSeguro = importeSeguro;
	}

	public Double getMontoTotalCuota() {
		return montoTotalCuota;
	}

	public void setMontoTotalCuota(Double montoTotalCuota) {
		this.montoTotalCuota = montoTotalCuota;
	}

	public Double getMontoTotalPagar() {
		return montoTotalPagar;
	}

	public void setMontoTotalPagar(Double montoTotalPagar) {
		this.montoTotalPagar = montoTotalPagar;
	}

	public String getRutaGeneracionContrato() {
		return rutaGeneracionContrato;
	}

	public void setRutaGeneracionContrato(String rutaGeneracionContrato) {
		this.rutaGeneracionContrato = rutaGeneracionContrato;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public String getRutacontratos() {
		return rutacontratos;
	}

	public void setRutacontratos(String rutacontratos) {
		this.rutacontratos = rutacontratos;
	}

	public String getRutatemp() {
		return rutatemp;
	}

	public void setRutatemp(String rutatemp) {
		this.rutatemp = rutatemp;
	}

	public String getRutaPdf() {
		return rutaPdf;
	}

	public void setRutaPdf(String rutaPdf) {
		this.rutaPdf = rutaPdf;
	}

	public String getRutaFija() {
		return rutaFija;
	}

	public void setRutaFija(String rutaFija) {
		this.rutaFija = rutaFija;
	}

	public String getRUC() {
		return RUC;
	}

	public void setRUC(String rUC) {
		RUC = rUC;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getNombreRepresentante1() {
		return nombreRepresentante1;
	}

	public void setNombreRepresentante1(String nombreRepresentante1) {
		this.nombreRepresentante1 = nombreRepresentante1;
	}

	public String getDniRepresentante1() {
		return dniRepresentante1;
	}

	public void setDniRepresentante1(String dniRepresentante1) {
		this.dniRepresentante1 = dniRepresentante1;
	}

	public String getPoderRepresentante1() {
		return poderRepresentante1;
	}

	public void setPoderRepresentante1(String poderRepresentante1) {
		this.poderRepresentante1 = poderRepresentante1;
	}

	public String getNombreRepresentante2() {
		return nombreRepresentante2;
	}

	public void setNombreRepresentante2(String nombreRepresentante2) {
		this.nombreRepresentante2 = nombreRepresentante2;
	}

	public String getDniRepresentante2() {
		return dniRepresentante2;
	}

	public void setDniRepresentante2(String dniRepresentante2) {
		this.dniRepresentante2 = dniRepresentante2;
	}

	public String getPoderRepresentante2() {
		return poderRepresentante2;
	}

	public void setPoderRepresentante2(String poderRepresentante2) {
		this.poderRepresentante2 = poderRepresentante2;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}

	public DatosComplementariosBean getDatosComplementariosBean() {
		return datosComplementariosBean;
	}

	public void setDatosComplementariosBean(DatosComplementariosBean datosComplementariosBean) {
		this.datosComplementariosBean = datosComplementariosBean;
	}

	public String getCodigoSexo() {
		return codigoSexo;
	}

	public void setCodigoSexo(String codigoSexo) {
		this.codigoSexo = codigoSexo;
	}

	public String getCodigoEstadoCivil() {
		return codigoEstadoCivil;
	}

	public void setCodigoEstadoCivil(String codigoEstadoCivil) {
		this.codigoEstadoCivil = codigoEstadoCivil;
	}

	public Integer getIdProfesion() {
		return idProfesion;
	}

	public void setIdProfesion(Integer idProfesion) {
		this.idProfesion = idProfesion;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getFechaReDesembolso() {
		return fechaReDesembolso;
	}

	public void setFechaReDesembolso(String fechaReDesembolso) {
		this.fechaReDesembolso = fechaReDesembolso;
	}

	public String getSimboloMonedaPdf() {
		return simboloMonedaPdf;
	}

	public void setSimboloMonedaPdf(String simboloMonedaPdf) {
		this.simboloMonedaPdf = simboloMonedaPdf;
	}

	public Double getTotalMontoAmortizacion() {
		return totalMontoAmortizacion;
	}

	public void setTotalMontoAmortizacion(Double totalMontoAmortizacion) {
		this.totalMontoAmortizacion = totalMontoAmortizacion;
	}

	public Double getTotalMontoInteresDevengado() {
		return totalMontoInteresDevengado;
	}

	public void setTotalMontoInteresDevengado(Double totalMontoInteresDevengado) {
		this.totalMontoInteresDevengado = totalMontoInteresDevengado;
	}

	public Double getTotalMontoSeguroDesgravamen() {
		return totalMontoSeguroDesgravamen;
	}

	public void setTotalMontoSeguroDesgravamen(Double totalMontoSeguroDesgravamen) {
		this.totalMontoSeguroDesgravamen = totalMontoSeguroDesgravamen;
	}

	public Double getTotalMontoGastosComisiones() {
		return totalMontoGastosComisiones;
	}

	public void setTotalMontoGastosComisiones(Double totalMontoGastosComisiones) {
		this.totalMontoGastosComisiones = totalMontoGastosComisiones;
	}

	public Double getTotalMontoCuotas() {
		return totalMontoCuotas;
	}

	public void setTotalMontoCuotas(Double totalMontoCuotas) {
		this.totalMontoCuotas = totalMontoCuotas;
	}
}
