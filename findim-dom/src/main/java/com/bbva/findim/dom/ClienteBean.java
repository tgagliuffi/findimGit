package com.bbva.findim.dom;

import java.util.Date;
import java.util.List;

import com.bbva.findim.dom.CatalogoBean;
import com.bbva.findim.dom.ContactoBean;
import com.bbva.findim.dom.DatosLaboralesBean;
import com.bbva.findim.dom.DireccionBean;
import com.bbva.findim.dom.DireccionClienteBean;
import com.bbva.findim.dom.DocumentoIdentidadBean;
import com.bbva.findim.dom.GrupoGeografico;

public class ClienteBean {
	private String primerNombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Integer tamanio;
	private String correoCliente;
	private String nombreArchivo;
	private String tipoDocumento;
	private String descTipoDocumento;
	private String numeroDocumento;
	private String nombreCompleto;
	private List<ContratoBean> listaContrato;
	private List<ParametroBean> listaTipoEnvio;
	private List<ParametroBean> listaOcupacion;
	private List<ParametroBean> listaTipoModalidad;
	private List<ParametroBean> listaTipoEnvios;
	private String centroTrabajo;
	private String cargo;
	private Integer idTipoEnvio;
	private String idTipoOcupacion;
	private Integer idTipoModalidad;
	private String idContrato;
	private Integer idCliente;
	private String rutaGeneracionContrato;
	private Integer codigoResultado;
	private String detalleTecnico;
	private String detalleFuncional;
	private String pagina;
	private Date fechaExpedicionDocumento;
	private Date fechaExpiracionDocumento;
	private int estadoCivil;
	private String genero;
	private String fechaNacimiento;
	private int idCorreoCliente;
	private String codigoAuxiliar;
	private int idPaisOrigen;
	private int idPaisActual;
	private int idNacionalidad;
	private String telefonoFijo;
	private String telefonoMovil;
	private String telefonoReferencial;
	private String direccion;
	private Integer tipoRespuesta;
	private Integer tipoError;
	private String rutaServicioRest;
	private DatosPdfBean datosPdf;
	private String AddtipoDocIdentidad;
	private String rutaImagen;
	private String rutacontratos;
	private String rutatemp;
	private String rutaPdf;
	private String rutaFija;

	private Integer resultadoFirma;

	private String seguridad_data;
	private String seguridad_user;
	private String seguridad_usertype;
	private String seguridad_channel;
	private String seguridad_message;
	private String seguridad_status;
	private ContratoBean contratoBean;
	
	private Integer idTipoEnvioSeleccionado;
	
	private List<MantenimientoCorreoBean> listaCorreo;
	 
	
	private String estadoContrato;
	private String nombreEnvio;
	private Boolean esCliente;
	private List<GrupoGeografico> lstGrupoGeografico;
	private List<DireccionBean> lstDireccionBean;
	private List<DocumentoIdentidadBean> lstDocumentoIdentidadBean;
	private List<ContactoBean> lstContacto;
	private List<String> nacionalidades;
	private DatosLaboralesBean datosLaboralesBean;
	private List<String> lstCondiciones;
	private List<CatalogoBean> listaCatalogo;
	private List<CatalogoBean> listaCatalogoDireccion;
	private String codNacionalidad;
	private String codPaisOrigen;
	private List<ParametroBean> listaComprobantePago; 
	
	private String tituloCliente;
	private int idNaturaleza;//3=cliente ; 2 = persona ; 1 = ciudadano

	private SimulacionBean simulacionBean;
	private RespuestaService respuestaService;
	private DireccionClienteBean direccionCliente;
	
	public SimulacionBean getSimulacionBean() {
		return simulacionBean;
	}

	public void setSimulacionBean(SimulacionBean simulacionBean) {
		this.simulacionBean = simulacionBean;
	}

	public Boolean isEsCliente() {
		return esCliente;
	}

	public void setEsCliente(Boolean esCliente) {
		this.esCliente = esCliente;
	}

	public List<GrupoGeografico> getLstGrupoGeografico() {
		return lstGrupoGeografico;
	}

	public void setLstGrupoGeografico(List<GrupoGeografico> lstGrupoGeografico) {
		this.lstGrupoGeografico = lstGrupoGeografico;
	}

	public String getNombreEnvio() {
		return nombreEnvio;
	}

	public void setNombreEnvio(String nombreEnvio) {
		this.nombreEnvio = nombreEnvio;
	}

	public String getEstadoContrato() {
		return estadoContrato;
	}

	public void setEstadoContrato(String estadoContrato) {
		this.estadoContrato = estadoContrato;
	}

	public List<CatalogoBean> getListaCatalogoDireccion() {
		return listaCatalogoDireccion;
	}

	public void setListaCatalogoDireccion(List<CatalogoBean> listaCatalogoDireccion) {
		this.listaCatalogoDireccion = listaCatalogoDireccion;
	}

	public List<ParametroBean> getListaComprobantePago() {
		return listaComprobantePago;
	}

	public void setListaComprobantePago(List<ParametroBean> listaComprobantePago) {
		this.listaComprobantePago = listaComprobantePago;
	}

	public List<CatalogoBean> getListaCatalooDireccion() {
		return listaCatalogoDireccion;
	}

	public void setListaCatalooDireccion(List<CatalogoBean> listaCatalogoDireccion) {
		this.listaCatalogoDireccion = listaCatalogoDireccion;
	}

	public String getRutaServicioRest() {
		return rutaServicioRest;
	}

	public List<CatalogoBean> getListaCatalogo() {
		return listaCatalogo;
	}

	public void setListaCatalogo(List<CatalogoBean> listaCatalogo) {
		this.listaCatalogo = listaCatalogo;
	}

	public void setRutaServicioRest(String rutaServicioRest) {
		this.rutaServicioRest = rutaServicioRest;
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

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
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

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
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

	public List<ContratoBean> getListaContrato() {
		return listaContrato;
	}

	public void setListaContrato(List<ContratoBean> listaContrato) {
		this.listaContrato = listaContrato;
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

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public int getIdCorreoCliente() {
		return idCorreoCliente;
	}

	public void setIdCorreoCliente(int idCorreoCliente) {
		this.idCorreoCliente = idCorreoCliente;
	}

	public String getAddtipoDocIdentidad() {
		return AddtipoDocIdentidad;
	}

	public void setAddtipoDocIdentidad(String addtipoDocIdentidad) {
		AddtipoDocIdentidad = addtipoDocIdentidad;
	}

	public DatosPdfBean getDatosPdf() {
		return datosPdf;
	}

	public void setDatosPdf(DatosPdfBean datosPdf) {
		this.datosPdf = datosPdf;
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


	public Integer getResultadoFirma() {
		return resultadoFirma;
	}

	public void setResultadoFirma(Integer resultadoFirma) {
		this.resultadoFirma = resultadoFirma;
	}

	public String getSeguridad_data() {
		return seguridad_data;
	}

	public void setSeguridad_data(String seguridad_data) {
		this.seguridad_data = seguridad_data;
	}

	public String getSeguridad_user() {
		return seguridad_user;
	}

	public void setSeguridad_user(String seguridad_user) {
		this.seguridad_user = seguridad_user;
	}

	public String getSeguridad_usertype() {
		return seguridad_usertype;
	}

	public void setSeguridad_usertype(String seguridad_usertype) {
		this.seguridad_usertype = seguridad_usertype;
	}

	public String getSeguridad_channel() {
		return seguridad_channel;
	}

	public void setSeguridad_channel(String seguridad_channel) {
		this.seguridad_channel = seguridad_channel;
	}

	public String getSeguridad_message() {
		return seguridad_message;
	}

	public void setSeguridad_message(String seguridad_message) {
		this.seguridad_message = seguridad_message;
	}

	public String getSeguridad_status() {
		return seguridad_status;
	}

	public void setSeguridad_status(String seguridad_status) {
		this.seguridad_status = seguridad_status;
	}

	@Deprecated // Deprecado en Sprint 9, usar listaTipoEnvio
	public List<ParametroBean> getListaTipoEnvios() {
		return listaTipoEnvios;
	}

	@Deprecated // Deprecado en Sprint 9, usar listaTipoEnvio
	public void setListaTipoEnvios(List<ParametroBean> listaTipoEnvios) {
		this.listaTipoEnvios = listaTipoEnvios;
	}

	public Integer getIdTipoEnvioSeleccionado() {
		return idTipoEnvioSeleccionado;
	}

	public void setIdTipoEnvioSeleccionado(Integer idTipoEnvioSeleccionado) {
		this.idTipoEnvioSeleccionado = idTipoEnvioSeleccionado;
	}

	public ContratoBean getContratoBean() {
		return contratoBean;
	}

	public void setContratoBean(ContratoBean contratoBean) {
		this.contratoBean = contratoBean;
	}

	public List<MantenimientoCorreoBean> getListaCorreo() {
		return listaCorreo;
	}

	public void setListaCorreo(List<MantenimientoCorreoBean> listaCorreo) {
		this.listaCorreo = listaCorreo;
	}

	public Date getFechaExpiracionDocumento() {
		return fechaExpiracionDocumento;
	}

	public void setFechaExpiracionDocumento(Date fechaExpiracionDocumento) {
		this.fechaExpiracionDocumento = fechaExpiracionDocumento;
	}

	public List<String> getNacionalidades() {
		return nacionalidades;
	}

	public void setNacionalidades(List<String> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}

	public List<DireccionBean> getLstDireccionBean() {
		return lstDireccionBean;
	}

	public void setLstDireccionBean(List<DireccionBean> lstDireccionBean) {
		this.lstDireccionBean = lstDireccionBean;
	}

	public List<DocumentoIdentidadBean> getLstDocumentoIdentidadBean() {
		return lstDocumentoIdentidadBean;
	}

	public void setLstDocumentoIdentidadBean(List<DocumentoIdentidadBean> lstDocumentoIdentidadBean) {
		this.lstDocumentoIdentidadBean = lstDocumentoIdentidadBean;
	}

	public List<ContactoBean> getLstContacto() {
		return lstContacto;
	}

	public void setLstContacto(List<ContactoBean> lstContacto) {
		this.lstContacto = lstContacto;
	}

	public DatosLaboralesBean getDatosLaboralesBean() {
		return datosLaboralesBean;
	}

	public void setDatosLaboralesBean(DatosLaboralesBean datosLaboralesBean) {
		this.datosLaboralesBean = datosLaboralesBean;
	}

	public List<String> getLstCondiciones() {
		return lstCondiciones;
	}

	public void setLstCondiciones(List<String> lstCondiciones) {
		this.lstCondiciones = lstCondiciones;
	}

	public String getTituloCliente() {
		return tituloCliente;
	}

	public void setTituloCliente(String tituloCliente) {
		this.tituloCliente = tituloCliente;
	}

	public String getCodNacionalidad() {
		return codNacionalidad;
	}

	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}

	public String getCodPaisOrigen() {
		return codPaisOrigen;
	}

	public void setCodPaisOrigen(String codPaisOrigen) {
		this.codPaisOrigen = codPaisOrigen;
	}

	public RespuestaService getRespuestaService() {
		return respuestaService;
	}

	public void setRespuestaService(RespuestaService respuestaService) {
		this.respuestaService = respuestaService;
	}

	public DireccionClienteBean getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(DireccionClienteBean direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public int getIdNaturaleza() {
		return idNaturaleza;
	}

	public void setIdNaturaleza(int idNaturaleza) {
		this.idNaturaleza = idNaturaleza;
	}

	public Boolean getEsCliente() {
		return esCliente;
	}
	
	
	
}
