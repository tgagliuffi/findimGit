package com.bbva.findim.dom;

public class EventoContratoBean {
		
	private Integer idEventoContrato;
	private String	idContrato;
	private Integer	tipoEvento;
	private String  nombreEvento;
	private String	usuarioCreacion;
	private  String	fechaEvento;
	private String  descripcionEvento;
	private String  tipoUsuario;	
	private Integer resultado;
	private String descripcionTecnica;
	private String descripcionFuncional;
	private String cliente;
	
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public  String getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}
	public Integer getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(Integer tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}
	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}
	public String getFechaEvento() {
		return fechaEvento;
	}
	public void setFechaEvento(String fechaEvento) {
		this.fechaEvento = fechaEvento;
	}
	public Integer getIdEventoContrato() {
		return idEventoContrato;
	}
	public void setIdEventoContrato(Integer idEventoContrato) {
		this.idEventoContrato = idEventoContrato;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public String getDescripcionEvento() {
		return descripcionEvento;
	}
	public void setDescripcionEvento(String descripcionEvento) {
		this.descripcionEvento = descripcionEvento;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Integer getResultado() {
		return resultado;
	}
	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}
	public String getDescripcionTecnica() {
		return descripcionTecnica;
	}
	public void setDescripcionTecnica(String descripcionTecnica) {
		this.descripcionTecnica = descripcionTecnica;
	}
	public String getDescripcionFuncional() {
		return descripcionFuncional;
	}
	public void setDescripcionFuncional(String descripcionFuncional) {
		this.descripcionFuncional = descripcionFuncional;
	}	
}
