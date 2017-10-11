package com.bbva.findim.dom;

import java.math.BigDecimal;
import java.util.Date;

public class ContratoAltaBean {

	/**
	 * @author P023650 tgagliuffi
	 */
	private  Integer	idContratoAlta;
	private	 int		idTipoProducto;
	private  String 	atributos;
	private  Integer 	idAsesor;
	private  Integer 	idPuntoVenta;
	private  String		descripcionPuntoVenta;
	private  Integer 	idBien;
	private  Double 	valorEquipo ;
	private  Double		importeInicial;
	private  Double		importePrestamo;
	private  Double		importeEnvio;
	private  Date  		fechaFacturacion;
	private  Date 		fechaFormalizacion;
	private  Integer 	idTarifa;
	private  int		estado;
	private  Double		tasaSeguro;
	private  Double		tasaEfectivaAnual;
	private  Double		tasaCostoEfectivaAnual;
	private  Date 		fechaPrimerVencimiento;
	private	 int 		numeroCuotas;
	private  Double		montoCuota;
	private  Integer	indicadorCuotaDoble;
	private  String 	keyUnica;
	private  int 		tipoTransaccion;
	private  int		idEntidad;
	private  String	 	descripcionEntidad;
	private  int		idCanal ;
	private	 String		descripcionCanal;
	private  int 		versionContrato;
	private  int 		tipoVersion;
	private  Date 		fechaVenta;
	private  int 		situacionContrato;
	private  int 		diaPago;
	private  ClienteBean clienteBean = new ClienteBean();
	private DomicilioBean  domicilio = new DomicilioBean();
	private String 		telefonoFinanciado;
	private int			idTipoEnvio;
	private int			monedaCredito;
	private String		numeroContrato;
	private int			idOrganizacion;
	private String		nombreArchivo;

	private String		AddDepartamentoFirma;
	private String		AddNombreTarifa;
	private int			AdddiaFacturacion;
	private String		Addtransacporta;
	private String		AddnombreCanal;
	
	private RespuestaWS respuestaWS = new RespuestaWS();
	private Integer     posicionContrato= new Integer(1);
	private Date 		fechaFinalContrato;

	private Date		fechaDesembolso;
	private Double		totalInteresCompensatorio;
	private Double		importeSeguro;
	private BigDecimal	factorAcumulado = new BigDecimal(0);
	private Integer		idUsuarioCreacion = new Integer(1);
	private Double		montoCuotaTotal;
	private	Integer    numeroCuotasInicial;
	private Date 		cambioFechaPagoContrato;
	private Boolean 	primeraCuotaCambioFechaPago;
	
	private String 	proveedorTercero;
	private String 	canal;
	private String 	productoExterno;
	private String	codTipoEnvio;
	
	private String cdOficina;
	
	private RespuestaService repuestaService;
	
	public ContratoAltaBean() {
	}

	public Integer getIdContratoAlta() {
		return idContratoAlta;
	}

	public void setIdContratoAlta(Integer idContratoAlta) {
		this.idContratoAlta = idContratoAlta;
	}

	public int getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(int idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public String getAtributos() {
		return atributos;
	}

	public void setAtributos(String atributos) {
		this.atributos = atributos;
	}

	public Integer getIdAsesor() {
		return idAsesor;
	}

	public void setIdAsesor(Integer idAsesor) {
		this.idAsesor = idAsesor;
	}

	public Integer getIdPuntoVenta() {
		return idPuntoVenta;
	}

	public void setIdPuntoVenta(Integer idPuntoVenta) {
		this.idPuntoVenta = idPuntoVenta;
	}

	public Integer getIdBien() {
		return idBien;
	}

	public void setIdBien(Integer idBien) {
		this.idBien = idBien;
	}

	public Double getValorEquipo() {
		return valorEquipo;
	}

	public void setValorEquipo(Double valorEquipo) {
		this.valorEquipo = valorEquipo;
	}

	public Double getImporteInicial() {
		return importeInicial;
	}

	public void setImporteInicial(Double importeInicial) {
		this.importeInicial = importeInicial;
	}

	public Double getImportePrestamo() {
		return importePrestamo;
	}

	public void setImportePrestamo(Double importePrestamo) {
		this.importePrestamo = importePrestamo;
	}

	public Date getFechaFacturacion() {
		return fechaFacturacion;
	}

	public void setFechaFacturacion(Date fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}

	public Date getFechaFormalizacion() {
		return fechaFormalizacion;
	}

	public void setFechaFormalizacion(Date fechaFormalizacion) {
		this.fechaFormalizacion = fechaFormalizacion;
	}

	public Integer getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(Integer idTarifa) {
		this.idTarifa = idTarifa;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Double getTasaSeguro() {
		return tasaSeguro;
	}

	public void setTasaSeguro(Double tasaSeguro) {
		this.tasaSeguro = tasaSeguro;
	}

	public Double getTasaEfectivaAnual() {
		return tasaEfectivaAnual;
	}

	public void setTasaEfectivaAnual(Double tasaEfectivaAnual) {
		this.tasaEfectivaAnual = tasaEfectivaAnual;
	}

	public Double getTasaCostoEfectivaAnual() {
		return tasaCostoEfectivaAnual;
	}

	public void setTasaCostoEfectivaAnual(Double tasaCostoEfectivaAnual) {
		this.tasaCostoEfectivaAnual = tasaCostoEfectivaAnual;
	}

	public Date getFechaPrimerVencimiento() {
		return fechaPrimerVencimiento;
	}

	public void setFechaPrimerVencimiento(Date fechaPrimerVencimiento) {
		this.fechaPrimerVencimiento = fechaPrimerVencimiento;
	}

	public int getNumeroCuotas() {
		return numeroCuotas;
	}

	public void setNumeroCuotas(int numeroCuotas) {
		this.numeroCuotas = numeroCuotas;
	}

	public Double getMontoCuota() {
		return montoCuota;
	}

	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}

	public Integer getIndicadorCuotaDoble() {
		return indicadorCuotaDoble;
	}

	public void setIndicadorCuotaDoble(Integer indicadorCuotaDoble) {
		this.indicadorCuotaDoble = indicadorCuotaDoble;
	}

	public String getKeyUnica() {
		return keyUnica;
	}

	public void setKeyUnica(String keyUnica) {
		this.keyUnica = keyUnica;
	}

	public int getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(int tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	public int getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(int idEntidad) {
		this.idEntidad = idEntidad;
	}

	public int getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(int idCanal) {
		this.idCanal = idCanal;
	}

	public int getVersionContrato() {
		return versionContrato;
	}

	public void setVersionContrato(int versionContrato) {
		this.versionContrato = versionContrato;
	}

	public int getTipoVersion() {
		return tipoVersion;
	}

	public void setTipoVersion(int tipoVersion) {
		this.tipoVersion = tipoVersion;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public int getSituacionContrato() {
		return situacionContrato;
	}

	public void setSituacionContrato(int situacionContrato) {
		this.situacionContrato = situacionContrato;
	}

	public int getDiaPago() {
		return diaPago;
	}

	public void setDiaPago(int diaPago) {
		this.diaPago = diaPago;
	}

	public ClienteBean getClienteBean() {
		return clienteBean;
	}

	public void setClienteBean(ClienteBean clienteBean) {
		this.clienteBean = clienteBean;
	}

	public DomicilioBean getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(DomicilioBean domicilio) {
		this.domicilio = domicilio;
	}

	
	public String getTelefonoFinanciado() {
		return telefonoFinanciado;
	}

	public void setTelefonoFinanciado(String telefonoFinanciado) {
		this.telefonoFinanciado = telefonoFinanciado;
	}

	
	public int getIdTipoEnvio() {
		return idTipoEnvio;
	}

	public void setIdTipoEnvio(int idTipoEnvio) {
		this.idTipoEnvio = idTipoEnvio;
	}

	public int getMonedaCredito() {
		return monedaCredito;
	}

	public void setMonedaCredito(int monedaCredito) {
		this.monedaCredito = monedaCredito;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public int getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(int idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public RespuestaWS getRespuestaWS() {
		return respuestaWS;
	}

	public void setRespuestaWS(RespuestaWS respuestaWS) {
		this.respuestaWS = respuestaWS;
	}

	public String getDescripcionPuntoVenta() {
		return descripcionPuntoVenta;
	}

	public void setDescripcionPuntoVenta(String descripcionPuntoVenta) {
		this.descripcionPuntoVenta = descripcionPuntoVenta;
	}

	public String getDescripcionEntidad() {
		return descripcionEntidad;
	}

	public void setDescripcionEntidad(String descripcionEntidad) {
		this.descripcionEntidad = descripcionEntidad;
	}

	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}
	
	public String getAddDepartamentoFirma() {
		return AddDepartamentoFirma;
	}

	public void setAddDepartamentoFirma(String addDepartamentoFirma) {
		AddDepartamentoFirma = addDepartamentoFirma;
	}

	public String getAddNombreTarifa() {
		return AddNombreTarifa;
	}

	public void setAddNombreTarifa(String addNombreTarifa) {
		AddNombreTarifa = addNombreTarifa;
	}

	public int getAdddiaFacturacion() {
		return AdddiaFacturacion;
	}

	public void setAdddiaFacturacion(int adddiaFacturacion) {
		AdddiaFacturacion = adddiaFacturacion;
	}

	public String getAddtransacporta() {
		return Addtransacporta;
	}

	public void setAddtransacporta(String addtransacporta) {
		Addtransacporta = addtransacporta;
	}

	public String getAddnombreCanal() {
		return AddnombreCanal;
	}

	public void setAddnombreCanal(String addnombreCanal) {
		AddnombreCanal = addnombreCanal;
	}

	public Date getFechaFinalContrato() {
		return fechaFinalContrato;
	}

	public void setFechaFinalContrato(Date fechaFinalContrato) {
		this.fechaFinalContrato = fechaFinalContrato;
	}
	public Double getImporteEnvio() {
		return importeEnvio;
	}

	public void setImporteEnvio(Double importeEnvio) {
		this.importeEnvio = importeEnvio;
	}

	public Date getFechaDesembolso() {
		return fechaDesembolso;
	}

	public void setFechaDesembolso(Date fechaDesembolso) {
		this.fechaDesembolso = fechaDesembolso;
	}

	public Integer getPosicionContrato() {
		return posicionContrato;
	}

	public void setPosicionContrato(Integer posicionContrato) {
		this.posicionContrato = posicionContrato;
	}

	public BigDecimal getFactorAcumulado() {
		return factorAcumulado;
	}

	public void setFactorAcumulado(BigDecimal factorAcumulado) {
		this.factorAcumulado = factorAcumulado;
	}

	public Integer getIdUsuarioCreacion() {
		return idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(Integer idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
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

	public Double getMontoCuotaTotal() {
		return montoCuotaTotal;
	}

	public void setMontoCuotaTotal(Double montoCuotaTotal) {
		this.montoCuotaTotal = montoCuotaTotal;
	}

	public Integer getNumeroCuotasInicial() {
		return numeroCuotasInicial;
	}

	public void setNumeroCuotasInicial(Integer numeroCuotasInicial) {
		this.numeroCuotasInicial = numeroCuotasInicial;
	}

	public Boolean getPrimeraCuotaCambioFechaPago() {
		return primeraCuotaCambioFechaPago;
	}

	public void setPrimeraCuotaCambioFechaPago(Boolean primeraCuotaCambioFechaPago) {
		this.primeraCuotaCambioFechaPago = primeraCuotaCambioFechaPago;
	}

	public Date getCambioFechaPagoContrato() {
		return cambioFechaPagoContrato;
	}

	public void setCambioFechaPagoContrato(Date cambioFechaPagoContrato) {
		this.cambioFechaPagoContrato = cambioFechaPagoContrato;
	}

	public String getProveedorTercero() {
		return proveedorTercero;
	}

	public void setProveedorTercero(String proveedorTercero) {
		this.proveedorTercero = proveedorTercero;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getProductoExterno() {
		return productoExterno;
	}

	public void setProductoExterno(String productoExterno) {
		this.productoExterno = productoExterno;
	}

	public String getCodTipoEnvio() {
		return codTipoEnvio;
	}

	public void setCodTipoEnvio(String codTipoEnvio) {
		this.codTipoEnvio = codTipoEnvio;
	}

	public RespuestaService getRepuestaService() {
		return repuestaService;
	}

	public void setRepuestaService(RespuestaService repuestaService) {
		this.repuestaService = repuestaService;
	}

	public String getCdOficina() {
		return cdOficina;
	}

	public void setCdOficina(String cdOficina) {
		this.cdOficina = cdOficina;
	}

	
}
