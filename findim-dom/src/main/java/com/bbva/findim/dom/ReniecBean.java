package com.bbva.findim.dom;

import java.util.Date;

public class ReniecBean {

	
	private String  segundoNombre;
	private String  apellidoPaterno;
	private String  apellidoMaterno;
	private Integer tamanio;
	private String  correoCliente;
	private String  nombreArchivo;
	private Integer tipoDocumento;
	private String  descTipoDocumento;
	private String  numeroDocumento;
	private String nombreCompleto;	
	private String centroTrabajo;
	private String cargo;
	private Integer idTipoEnvio;
	private Integer idTipoOcupacion;	
	private Integer idTipoModalidad;
	private Integer idContrato;
	private Integer idCliente;
	private String rutaGeneracionContrato;
	private Integer codigoResultado;
	private String detalleTecnico;
	private String detalleFuncional;
	private String pagina;	
	private Date	fechaExpedicionDocumento;
	private int		estadoCivil;
	private String	genero;
	private Date	fechaNacimiento;
	private int		idCorreoCliente;
	private String  codigoAuxiliar;
	private int		idPaisOrigen;
	private int		idPaisActual;
	private int		idNacionalidad;
	private String 		telefonoFijo;
	private String 		telefonoMovil;
	private String 		telefonoReferencial;
	private String  direccion;
	private Integer tipoRespuesta;
	private Integer tipoError;
	private String  primerNombre;
	private RespuestaWS respuestaWS = new RespuestaWS();
	
	public RespuestaWS getRespuestaWS() {
		return respuestaWS;
	}
	public void setRespuestaWS(RespuestaWS respuestaWS) {
		this.respuestaWS = respuestaWS;
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
	public Integer getTamanio() {
		return tamanio;
	}
	public void setTamanio(Integer tamanio) {
		this.tamanio = tamanio;
	}
	public String getCorreoCliente() {
		return correoCliente;
	}
	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public Integer getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDescTipoDocumento() {
		return descTipoDocumento;
	}
	public void setDescTipoDocumento(String descTipoDocumento) {
		this.descTipoDocumento = descTipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getCentroTrabajo() {
		return centroTrabajo;
	}
	public void setCentroTrabajo(String centroTrabajo) {
		this.centroTrabajo = centroTrabajo;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public Integer getIdTipoEnvio() {
		return idTipoEnvio;
	}
	public void setIdTipoEnvio(Integer idTipoEnvio) {
		this.idTipoEnvio = idTipoEnvio;
	}
	public Integer getIdTipoOcupacion() {
		return idTipoOcupacion;
	}
	public void setIdTipoOcupacion(Integer idTipoOcupacion) {
		this.idTipoOcupacion = idTipoOcupacion;
	}
	public Integer getIdTipoModalidad() {
		return idTipoModalidad;
	}
	public void setIdTipoModalidad(Integer idTipoModalidad) {
		this.idTipoModalidad = idTipoModalidad;
	}
	public Integer getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(Integer idContrato) {
		this.idContrato = idContrato;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getRutaGeneracionContrato() {
		return rutaGeneracionContrato;
	}
	public void setRutaGeneracionContrato(String rutaGeneracionContrato) {
		this.rutaGeneracionContrato = rutaGeneracionContrato;
	}
	public Integer getCodigoResultado() {
		return codigoResultado;
	}
	public void setCodigoResultado(Integer codigoResultado) {
		this.codigoResultado = codigoResultado;
	}
	public String getDetalleTecnico() {
		return detalleTecnico;
	}
	public void setDetalleTecnico(String detalleTecnico) {
		this.detalleTecnico = detalleTecnico;
	}
	public String getDetalleFuncional() {
		return detalleFuncional;
	}
	public void setDetalleFuncional(String detalleFuncional) {
		this.detalleFuncional = detalleFuncional;
	}
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	public Date getFechaExpedicionDocumento() {
		return fechaExpedicionDocumento;
	}
	public void setFechaExpedicionDocumento(Date fechaExpedicionDocumento) {
		this.fechaExpedicionDocumento = fechaExpedicionDocumento;
	}
	public int getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(int estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public int getIdCorreoCliente() {
		return idCorreoCliente;
	}
	public void setIdCorreoCliente(int idCorreoCliente) {
		this.idCorreoCliente = idCorreoCliente;
	}
	public String getCodigoAuxiliar() {
		return codigoAuxiliar;
	}
	public void setCodigoAuxiliar(String codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}
	public int getIdPaisOrigen() {
		return idPaisOrigen;
	}
	public void setIdPaisOrigen(int idPaisOrigen) {
		this.idPaisOrigen = idPaisOrigen;
	}
	public int getIdPaisActual() {
		return idPaisActual;
	}
	public void setIdPaisActual(int idPaisActual) {
		this.idPaisActual = idPaisActual;
	}
	public int getIdNacionalidad() {
		return idNacionalidad;
	}
	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	public String getTelefonoReferencial() {
		return telefonoReferencial;
	}
	public void setTelefonoReferencial(String telefonoReferencial) {
		this.telefonoReferencial = telefonoReferencial;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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


	
}
