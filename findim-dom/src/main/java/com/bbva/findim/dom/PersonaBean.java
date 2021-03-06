package com.bbva.findim.dom;


import java.util.Date;
import java.util.List;

/**
 * The persistent class for the PERSONA database table.
 * 
 */
public class PersonaBean  {

	private String id;
	private String materno;
	private String paterno;
	private Boolean clienteNuevo;
	private String codigoCentral;
	private String codigoEstadoCivil;
	private String codigoSexo;
	private String estadoCivil;
	private String sexo;
	private String nacimiento;
	private Date creacion;
	private Date edicion;
	private Date alta;
	private Date expedicion;
	private Date caducidad;
	private String nombres;
	private String numeroDocumento;
	private String tipoDocumento;
	private List<DireccionBean> lstDirecciones;
	
	private String fhNacimiento;

	private String oficinaAsignada;
	private String dsDireccionAndocs;
	private String dsUbigeoComplet;
	private RespuestaService respuestaService;
	
	public PersonaBean() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaterno() {
		return this.materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getPaterno() {
		return this.paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public Boolean getClienteNuevo() {
		return this.clienteNuevo;
	}

	public void setClienteNuevo(Boolean clienteNuevo) {
		this.clienteNuevo = clienteNuevo;
	}

	public String getCodigoCentral() {
		return this.codigoCentral;
	}

	public void setCodigoCentral(String codigoCentral) {
		this.codigoCentral = codigoCentral;
	}

	public String getCodigoEstadoCivil() {
		return this.codigoEstadoCivil;
	}

	public void setCodigoEstadoCivil(String codigoEstadoCivil) {
		this.codigoEstadoCivil = codigoEstadoCivil;
	}

	public String getCodigoSexo() {
		return this.codigoSexo;
	}

	public void setCodigoSexo(String codigoSexo) {
		this.codigoSexo = codigoSexo;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String descEstadoCivil) {
		this.estadoCivil = descEstadoCivil;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String descSexo) {
		this.sexo = descSexo;
	}

	public String getNacimiento() {
		return this.nacimiento;
	}

	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getNumeroDocumento() {
		return this.numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public Date getAlta() {
		return alta;
	}

	public void setAlta(Date alta) {
		this.alta = alta;
	}

	public String getOficinaAsignada() {
		return oficinaAsignada;
	}

	public void setOficinaAsignada(String oficinaAsignada) {
		this.oficinaAsignada = oficinaAsignada;
	}

	public Date getExpedicion() {
		return expedicion;
	}

	public void setExpedicion(Date expedicion) {
		this.expedicion = expedicion;
	}

	public Date getCaducidad() {
		return caducidad;
	}

	public void setCaducidad(Date caducidad) {
		this.caducidad = caducidad;
	}

	public Date getCreacion() {
		return creacion;
	}

	public void setCreacion(Date creacion) {
		this.creacion = creacion;
	}

	public Date getEdicion() {
		return edicion;
	}

	public void setEdicion(Date edicion) {
		this.edicion = edicion;
	}

	public List<DireccionBean> getLstDirecciones() {
		return lstDirecciones;
	}

	public void setLstDirecciones(List<DireccionBean> lstDirecciones) {
		this.lstDirecciones = lstDirecciones;
	}

	public String getDsDireccionAndocs() {
		return dsDireccionAndocs;
	}

	public void setDsDireccionAndocs(String dsDireccionAndocs) {
		this.dsDireccionAndocs = dsDireccionAndocs;
	}

	public String getDsUbigeoComplet() {
		return dsUbigeoComplet;
	}

	public void setDsUbigeoComplet(String dsUbigeoComplet) {
		this.dsUbigeoComplet = dsUbigeoComplet;
	}

	public RespuestaService getRespuestaService() {
		return respuestaService;
	}

	public void setRespuestaService(RespuestaService respuestaService) {
		this.respuestaService = respuestaService;
	}

	public String getFhNacimiento() {
		return fhNacimiento;
	}

	public void setFhNacimiento(String fhNacimiento) {
		this.fhNacimiento = fhNacimiento;
	}
	
	
}