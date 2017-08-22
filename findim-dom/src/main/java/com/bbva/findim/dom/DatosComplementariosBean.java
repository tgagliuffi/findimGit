package com.bbva.findim.dom;

import java.util.List;

public class DatosComplementariosBean {
	private List<ParametroBean> listaTipoEnvio;
	private List<ParametroBean> listaOcupacion;	
	private List<ParametroBean> listaTipoModalidad;
	private String nombreCliente;
	private String correoElectronico;
	private String centroTrabajo;
	private String cargo;
	private Integer idTipoEnvio;
	private String idTipoOcupacion;	
	private Integer idTipoModalidad;
	private String idContrato;
	private Integer idCliente;
	private String ocupacion;

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
	public List<ParametroBean> getListaTipoEnvio() {
		return listaTipoEnvio;
	}
	public void setListaTipoEnvio(List<ParametroBean> listaTipoEnvio) {
		this.listaTipoEnvio = listaTipoEnvio;
	}
	public List<ParametroBean> getListaOcupacion() {
		return listaOcupacion;
	}
	public void setListaOcupacion(List<ParametroBean> listaOcupacion) {
		this.listaOcupacion = listaOcupacion;
	}
	public List<ParametroBean> getListaTipoModalidad() {
		return listaTipoModalidad;
	}
	public void setListaTipoModalidad(List<ParametroBean> listaTipoModalidad) {
		this.listaTipoModalidad = listaTipoModalidad;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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
	public String getIdTipoOcupacion() {
		return idTipoOcupacion;
	}
	public void setIdTipoOcupacion(String idTipoOcupacion) {
		this.idTipoOcupacion = idTipoOcupacion;
	}
	public Integer getIdTipoModalidad() {
		return idTipoModalidad;
	}
	public void setIdTipoModalidad(Integer idTipoModalidad) {
		this.idTipoModalidad = idTipoModalidad;
	}
	public String getOcupacion() {
		return ocupacion;
	}
	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}
}
