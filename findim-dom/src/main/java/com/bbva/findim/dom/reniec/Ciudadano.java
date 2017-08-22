package com.bbva.findim.dom.reniec;

public class Ciudadano {
	
	private String numeroDNI;
	public String getNumeroDNI() { return numeroDNI; }
	public void setNumeroDNI(String numeroDNI) { this.numeroDNI = numeroDNI; }
	
	private String nombres;
	public String getNombres() { return nombres; }
	public void setNombres(String nombres) { this.nombres = nombres; }
	
	private String apellidoPaterno;
	public String getApellidoPaterno() { return apellidoPaterno; }
	public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }
	
	private String apellidoMaterno;
	public String getApellidoMaterno() { return apellidoMaterno; }
	public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }
	
	private String apellidoCasada;
	public String getApellidoCasada() { return apellidoCasada; }
	public void setApellidoCasada(String apellidoCasada) { this.apellidoCasada = apellidoCasada; }
	
	private String sexo;
	public String getSexo() { return sexo; }
	public void setSexo(String sexo) { this.sexo = sexo; }
	
	private String codigoEstadoCivil;
	public String getCodigoEstadoCivil() { return codigoEstadoCivil; }
	public void setCodigoEstadoCivil(String codigoEstadoCivil) { this.codigoEstadoCivil = codigoEstadoCivil; }
	
	private String descripcionEstadoCivil;
	public String getDescripcionEstadoCivil() { return descripcionEstadoCivil; }
	public void setDescripcionEstadoCivil(String descripcionEstadoCivil) { this.descripcionEstadoCivil = descripcionEstadoCivil; }
	
	private String fechaNacimiento;
	public String getFechaNacimiento() { return fechaNacimiento; }
	public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
	
	private Domicilio domicilio;
	public Domicilio getDomicilio() { return domicilio; }
	public void setDomicilio(Domicilio domicilio) { this.domicilio = domicilio;	}
	
	private String codigoRespuesta;
	public String getCodigoRespuesta() { return codigoRespuesta; }
	public void setCodigoRespuesta(String codigoRespuesta) { this.codigoRespuesta = codigoRespuesta; }

	private String mensajeRespuesta;
	public String getMensajeRespuesta() { return mensajeRespuesta; }
	public void setMensajeRespuesta(String mensajeRespuesta) { this.mensajeRespuesta = mensajeRespuesta; }
	
	private String fechaCaducidad;
	public String getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
}