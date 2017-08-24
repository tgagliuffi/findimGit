package com.bbva.findim.wstdp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bbva.findim.bck.service.ApprovalsService;
import com.bbva.findim.bck.service.CustomersService;
import com.bbva.findim.bck.service.PersonService;
import com.bbva.findim.bck.service.ProposalService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.dom.AltaContratoRequest;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.DatosReniecBean;
import com.bbva.findim.dom.RespuestaBean;
import com.bbva.findim.dom.reniec.Ciudadano;
import com.bbva.findim.dom.CalificacionClienteBean;
import com.bbva.findim.dom.DireccionBean;
import com.bbva.findim.dom.GrupoGeografico;
import com.bbva.findim.dom.PersonaBean;
import com.bbva.findim.sql.service.DatoReniecService;
import com.bbva.findim.sql.service.LogAltaContratoService;
import com.bbva.findim.wstdp.service.ContratoWS;
import com.bbva.server.reniec.WS_PersonaReniec;
import com.grupobbva.pe.sir.ents.body.consultapordni.DatosNacimiento;
import com.grupobbva.pe.sir.ents.body.consultapordni.DatosPersona;
import com.grupobbva.pe.sir.ents.body.consultapordni.RespuestaDNI;
import com.grupobbva.pe.sir.ents.body.consultapordni.RespuestaDatos;
import com.grupobbva.pe.sir.ents.header.RequestHeader;
import com.grupobbva.pe.sir.ents.header.ResponseHeader;
import com.grupobbva.pe.sir.service.message.ConsultaPorDNIRequest;
import com.grupobbva.pe.sir.service.message.ConsultaPorDNIResponse;

public class ContratoWSImpl implements ContratoWS {

	private static final Logger LOGGER = LogManager.getLogger(ContratoWSImpl.class);

	@Autowired
	private LogAltaContratoService logAltaContratoService;

	@Autowired
	private DatoReniecService datoReniecService;

	@Autowired
	SeguridadBbvaService seguridad;

	@Autowired
	CustomersService customerService;

	@Autowired
	PersonService personService;

	@Autowired
	ProposalService proposalService;

	@Autowired
	ApprovalsService approvalsService;

	@Autowired
	WS_PersonaReniec wS_PersonaReniec_Service;

	private Properties prop = new Properties();

	String ErrorAtla;

	@PostConstruct
	public void postConstruct() {
		String configPath = System.getProperty("findim-ws-tdp.config.path");
		try (FileInputStream file = new FileInputStream(
				configPath + File.separator + "findim-ws-tdp.properties")) {
			prop.load(file);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public ClienteBean cargarDatosClienteAlta(ClienteBean customer, PersonaBean noCliente, String correo) {
		ClienteBean cliente = null;

		if (customer != null) {
			cliente = new ClienteBean();
			cliente.setNumeroDocumento(customer.getLstDocumentoIdentidadBean().get(0).getNroDocumento());
			cliente.setTipoDocumento(customer.getLstDocumentoIdentidadBean().get(0).getTipoDocumentoIdentidad());//DNI
			cliente.setCorreoCliente(correo);
			cliente.setDescTipoDocumento(customer.getLstDocumentoIdentidadBean().get(0).getTipoDocumentoIdentidad());
			
		} else if (noCliente != null) {
			cliente = new ClienteBean();
			cliente.setNumeroDocumento(noCliente.getNumeroDocumento());
			cliente.setTipoDocumento("DNI");// DNI
			cliente.setCorreoCliente(correo);
			cliente.setDescTipoDocumento("DNI");
		}

		return cliente;
	}

	@Override
	@WebMethod(operationName = "altaContrato")
	public RespuestaBean altaContrato(
			@WebParam(name ="llaveUnica") String llaveunica,
			@WebParam(name ="atributo") String atributo,
			@WebParam(name ="tarifa") String tarifa,
			@WebParam(name ="importeBien") Double importeBien,
			@WebParam(name ="importePrestamo") Double importePrestamo,
			@WebParam(name ="diaFacturacion") Integer diaFacturacion,
			@WebParam(name ="diaPagoCred") Integer diaPagocred,
			@WebParam(name ="tipoDocIdentidad") String tipoDocIdentidad,
			@WebParam(name ="numeroDocIdentidad") String numeroDocIdentidad,
			@WebParam(name ="direccion") String direccion,
			@WebParam(name ="departamento") String departamento,
			@WebParam(name ="provincia") String provincia,
			@WebParam(name ="distrito") String distrito,
			@WebParam(name ="telefonoFijo") String telefonoFijo,
			@WebParam(name ="telefonoReferencia") String telefonoReferencia,
			@WebParam(name ="telefonoFinanciado") String telefonoFinanciado,
			@WebParam(name ="correo") String correo,
			@WebParam(name ="tipoTransaccion") String transacporta,
			@WebParam(name ="codigoCanal") String codigoCanal,
			@WebParam(name ="nombreCanal") String nombreCanal,
			@WebParam(name ="codigoEntidad") Integer codigoEntidad,
			@WebParam(name ="nombreEntidad") String nombreEntidad,
			@WebParam(name ="codigoPuntoVenta") Integer codigoPuntoVenta,
			@WebParam(name ="nombrePuntoVenta") String nombrePuntoVenta,
			@WebParam(name ="tipoEnvio") Integer tipoEnvio,
			@WebParam(name ="departamentoFirma") String departamentoFirma
			) {
		long begin = System.nanoTime();
		LOGGER.info("SOAP::>Inicio -[altaContrato] {}", numeroDocIdentidad);

		AltaContratoRequest request = new AltaContratoRequest();
		request.setLlaveUnica(llaveunica);
		request.setAtributo(atributo);
		request.setTarifa(tarifa);
		request.setImporteBien(importeBien);
		request.setImportePrestamo(importePrestamo);
		request.setDiaFacturacion(diaFacturacion);
		request.setDiaPagoCredito(diaPagocred);
		request.setTipoDocIdentidad(tipoDocIdentidad);
		request.setNumeroDocIdentidad(numeroDocIdentidad);
		request.setDireccion(direccion);
		request.setDepartamento(departamento);
		request.setProvincia(provincia);
		request.setDistrito(distrito);
		request.setTelefonoFijo(telefonoFijo);
		request.setTelefonoReferencia(telefonoReferencia);
		request.setTelefonoFinanciado(telefonoFinanciado);
		request.setCorreo(correo);
		request.setTipoTransaccion(transacporta);
		request.setCodigoCanal(codigoCanal);
		request.setNombreCanal(nombreCanal);
		request.setCodigoEntidad(codigoEntidad);
		request.setNombreEntidad(nombreEntidad);
		request.setCodigoPuntoVenta(codigoPuntoVenta);
		request.setNombrePuntoVenta(nombrePuntoVenta);
		request.setTipoEnvio(tipoEnvio);
		request.setDepartamentoFirma(departamentoFirma);
		
		LOGGER.debug("Input: {}", request);
		RespuestaBean respuestaTDP = new RespuestaBean();
		try {
			
			ContratoAltaBean contratoAltaBean = obtenerValoresBean(
					llaveunica            ,			//	   1	cd_llaveunica       		string(32)   
					atributo              ,         //     2	nb_atributo         		string(50
					tarifa                ,         //     3	cd_tarifa           		string(10
					importeBien           ,         //     4	im_bien             		numeric(1
					importePrestamo       ,         //     5	im_prestamo         		numeric(1
					diaFacturacion        ,         //     6	nu_diafacturacion     		numeric
					diaPagocred           ,         //     7	nu_diapagocred      		numeric(2
					tipoDocIdentidad      ,         //     8	tp_docidentidad     		string(5)
					numeroDocIdentidad    ,         //     9	cd_docidentidad     		string(20
					direccion             ,         //     10	nb_direccion        	string(100)
					departamento          ,         //     11	cd_departamento     	string(3)  
					provincia             ,         //     12	cd_provincia        	string(3)  
					distrito              ,         //     13	cd_distrito         	string(3)  
					telefonoFijo          ,         //     14	nb_numerotelefono_FJ	string(15) 
					telefonoReferencia    ,         //     15	nb_numerotelefono_RF	string(15) 
					telefonoFinanciado    ,         //     16	nb_numerotelefono_FN	string(15) 
					correo                ,         //     17	nb_mail             	string(50) 
					transacporta          ,         //     18	tp_transacporta     	string(5)  
					codigoCanal           ,         //     19	cd_canal            	string(5)
					codigoEntidad         ,         //     21	cd_entidad          	numeric(5) 
					nombreEntidad         ,         //     22	nb_entidad						string(100)
					codigoPuntoVenta      ,         //     23	cd_puntoventa       	numeric(10)
					nombrePuntoVenta      ,         //     24	nb_puntoventa					string(100)
					tipoEnvio             ,         //     25	tp_envio            	numeric(5) 
					departamentoFirma               //    26	cd_dpto_ptovta				string(3)
			);
			ClienteBean clienteAlta = null;
			String ok = "";
			String tipoDoc="";
			String tipoDocws="";
			tipoDoc="DNI";
			tipoDocws="L";
			
			String rptaServAltaPropuesta = "";
			CalificacionClienteBean beanClasificacion = null;
			beanClasificacion = approvalsService.evaluarCliente(tipoDocIdentidad, numeroDocIdentidad, seguridad.generarTSec(2));
			
			if(beanClasificacion.getDescErrorServicio()==null || beanClasificacion.getDescErrorServicio().equals("")){
			if(beanClasificacion.getCodigoResultado()==1){//APROBADO PARAMETRIZAR
					clienteAlta = validarTipoCliente(contratoAltaBean.getClienteBean().getNumeroDocumento(),tipoDoc,tipoDocws,contratoAltaBean.getClienteBean().getCorreoCliente());
			        
					if(clienteAlta.getRptErrorService()!=null){//Si no es cliente ni no cliente(RENIEC)
			        	Ciudadano ciudadano = obtenerInformacionReniec(contratoAltaBean.getClienteBean().getNumeroDocumento());
			        	PersonaBean personaCreada = crearNoCliente(ciudadano,contratoAltaBean,tipoDocws);//validar cuando no regresa ciudadano
				    	
				    	if(personaCreada!=null){
							DatosReniecBean datoReniec = new DatosReniecBean();
							datoReniec.setDireccionAmdocs(contratoAltaBean.getDomicilio().getDireccion());
							//corregir y descomentar
//							datoReniec.setFechaCaducidad(Util.convertFormatDate("yyyymmdd", "dd/mm/yy", ciudadano.getFechaCaducidad()));
							
							datoReniec.setNumeroDni(contratoAltaBean.getClienteBean().getNumeroDocumento());
							if(datoReniecService.obtenerDatosReniecPersona(personaCreada.getNumeroDocumento())==null){
								datoReniecService.guardarDatoReniecPersona(datoReniec);
							}
							rptaServAltaPropuesta =transaccionAltaContrato(contratoAltaBean,clienteAlta,request.getTarifa());
				    	} 
			        }else{
			        		rptaServAltaPropuesta =transaccionAltaContrato(contratoAltaBean,clienteAlta,request.getTarifa());
			        }
					
					//TODO CAMBIAR ESTA LOGICA !!!
				  	ok="";
				  	if(rptaServAltaPropuesta!=null){
					  	if(rptaServAltaPropuesta.contains("external-financing-proposals")){
					  		ok="Se creó el contrato correctamente";
					  	}else{
					  		ok= "No se creó el contrato";
					  	}
					  	respuestaTDP.setMensajeTecnico(rptaServAltaPropuesta);
					}else{
						ok= "No se creó el contrato";
					}
//				  	respuestaTDP.setMensajeTecnico(rptaServAltaPropuesta);
				  	request.setRtFiltroCliente(beanClasificacion.getTituloMostrar().toString());
					request.setTxMensajeFuncional(ok);
					request.setTxMensajeTecnico(ok);
				}else{//RECHAZADO 
					request.setRtFiltroCliente(beanClasificacion.getTituloMostrar());
				  	request.setNbMotivoRechazo(beanClasificacion.getDsResultado()!=null?beanClasificacion.getDsResultado():null);
					request.setTxMensajeFuncional("CONTRATO NO CREADO POR FILTRO RECHAZADO");
					request.setTxMensajeTecnico("");
				}
			}else{//SUCEDIO UN ERROR EN LA EJECUCIÓN DEL FILTRO
				request.setRtFiltroCliente(beanClasificacion.getTituloMostrar());
				request.setTxMensajeFuncional(beanClasificacion.getDescErrorServicio());
				request.setTxMensajeTecnico("ERROR EN SW FILTRO");
				respuestaTDP.setMensajeTecnico(request.getTxMensajeTecnico());
				respuestaTDP.setMensajeFuncional(request.getTxMensajeFuncional());
				respuestaTDP.setimporteCuota((float) 0);
			}
			
			respuestaTDP.setIndicadorProceso(0);
			respuestaTDP.setimporteCuota((float) 0);
			logAltaContratoService.insert(request);

		
		  	
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			respuestaTDP.setIndicadorProceso(0);
			respuestaTDP.setMensajeFuncional("No se ejecuto el alta de contrato, por favor consulte con sistemas");
			respuestaTDP.setimporteCuota((float) 0.0);
			
			request.setTpIndicadorProceso("ERROR");
			request.setTxMensajeFuncional("");
			request.setTxMensajeTecnico(ex.getMessage());
			
			
			logAltaContratoService.insert(request);
		}

		long end = System.nanoTime();
		long elapsedTime = TimeUnit.NANOSECONDS.toMillis(end - begin);
		LOGGER.info("SOAP::>Final {} - Transaccion Finalizada en: {} ms", numeroDocIdentidad, elapsedTime);
		return respuestaTDP;
	}

	private String transaccionAltaContrato(ContratoAltaBean contratoAltaBean, ClienteBean clienteAlta, String tarifa) {
		String respuesta = "";
		// TODO Auto-generated method stub
		ContratoAltaBean contrato = new ContratoAltaBean();
		contrato.setClienteBean(clienteAlta);
		contrato.setAddNombreTarifa(tarifa);
		contrato.setMonedaCredito(1);// soles
		contrato.setImportePrestamo(contratoAltaBean.getImportePrestamo());

		contrato.setDiaPago(contratoAltaBean.getDiaPago());
		contrato.setAdddiaFacturacion(contratoAltaBean.getAdddiaFacturacion());
		contrato.setTelefonoFinanciado(contratoAltaBean.getTelefonoFinanciado());
		contrato.setCodTipoEnvio("");
		contrato.setNumeroContrato(contratoAltaBean.getKeyUnica());
		contrato.setValorEquipo(contratoAltaBean.getValorEquipo());
		// preguntar si vendarn por parametria:
		contrato.setProveedorTercero("TELF");
		contrato.setCanal("TELF");
		contrato.setProductoExterno("CTEL000985674");
		try {
			respuesta = proposalService.altaProposal(seguridad.generarTSec(2), contrato);
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return respuesta;
	}

	private PersonaBean crearNoCliente(Ciudadano ciudadano, ContratoAltaBean contratoAltaBean, String tipoDocws) {
		// TODO Auto-generated method stub
		PersonaBean personaBean = new PersonaBean();
		personaBean.setNumeroDocumento(contratoAltaBean.getClienteBean().getNumeroDocumento());
		personaBean.setTipoDocumento(tipoDocws);

		// TODO VALIDAR COMO SE HARA CON LOS CARACTERES EXTRAÑOS
		personaBean.setNombres(ciudadano.getNombres().replace('Ñ', 'N'));
		personaBean.setPaterno(ciudadano.getApellidoPaterno().replace('Ñ', 'N'));
		personaBean.setMaterno(ciudadano.getApellidoMaterno().replace('Ñ', 'N'));
		// TODO VALIDAR COMO SE HARA CON LOS CARACTERES EXTRAÑOS

		personaBean.setClienteNuevo(true);
		personaBean.setCodigoEstadoCivil(obtenerEstadoCivilPersona(ciudadano.getCodigoEstadoCivil()));
		personaBean.setCodigoSexo(obtenerSexoPersona(ciudadano.getSexo()));

		ciudadano.setFechaNacimiento(
				ciudadano.getFechaNacimiento().substring(0, 4) + "-" + ciudadano.getFechaNacimiento().substring(4, 6)
						+ "-" + ciudadano.getFechaNacimiento().substring(6, 8));
		personaBean.setNacimiento(ciudadano.getFechaNacimiento());

		// CL(|)TAVARA WEST(|)Nro. 1054(|)Mz. G/(|) (|) (|)UR.(|)DANIEL ALCIDES
		// CARRION ET.2(|)

		List<GrupoGeografico> grupoGeograficoLst = new ArrayList<>();

		GrupoGeografico grupoGeografico = new GrupoGeografico();
		grupoGeografico.setId("DEPARTMENT");// viene de no cliente
		grupoGeografico.setCode(contratoAltaBean.getDomicilio().getIdDepartamento());// viene
																						// de
																						// no
																						// cliente
		grupoGeograficoLst.add(grupoGeografico);

		GrupoGeografico grupoGeografico2 = new GrupoGeografico();
		grupoGeografico2.setId("PROVINCE");// viene de no cliente
		grupoGeografico2.setCode(contratoAltaBean.getDomicilio().getIdProvincia());// viene
																					// de
																					// no
																					// cliente
		grupoGeograficoLst.add(grupoGeografico2);

		GrupoGeografico grupoGeografico3 = new GrupoGeografico();
		grupoGeografico3.setId("DISTRICT");// viene de no cliente
		grupoGeografico3.setCode(contratoAltaBean.getDomicilio().getIdDistrito());// viene
																					// de
																					// no
																					// cliente
		grupoGeograficoLst.add(grupoGeografico3);

		List<DireccionBean> listDireccion = new ArrayList<DireccionBean>();
		DireccionBean direccionAlta = new DireccionBean();
		direccionAlta.setLstGrupoGeografico(grupoGeograficoLst);
		direccionAlta.setDsDireccion(contratoAltaBean.getDomicilio().getDireccion());
		listDireccion.add(direccionAlta);
		personaBean.setLstDirecciones(listDireccion);
		PersonaBean personaUpdate = null;
		try {
			personaUpdate = personService.altaNoCliente(personaBean, seguridad.generarTSec(2));
		} catch (Exception e) {
			ErrorAtla = "Sucedio un Error al crear NoCliente";
			LOGGER.error("Error Al crear el no Cliente.", e);
			return null;
		}
		return personaUpdate;
	}

	private Ciudadano obtenerInformacionReniec(String numeroDocumento) {
		// TODO Auto-generated method stub
		String registro = "P016739";// "P017737";
		StringBuilder sRutaSoap = new StringBuilder();
		sRutaSoap.append("http://118.180.34.15:9080/WSIntegracionRENIEC/services/WS_PersonaReniec");
		// Parametria requerida para el servicio de RENIEC
		// String fechaHoraEnvio = DateUtil.getFecha(new Date(),
		// "yyyy-MM-dd-HH.mm.ss.SSS");//P
		ConsultaPorDNIRequest consultaPorDNIRequest = new ConsultaPorDNIRequest();
		consultaPorDNIRequest.setRefRequestHeader(new RequestHeader());
		consultaPorDNIRequest.getRefRequestHeader().setCanal("S_C_");
		consultaPorDNIRequest.getRefRequestHeader().setCodigoAplicacion("HUASCARAN");// Parametro
		consultaPorDNIRequest.getRefRequestHeader().setIdEmpresa("RENI");// Parametro
		consultaPorDNIRequest.getRefRequestHeader().setUsuario(registro);
		consultaPorDNIRequest.getRefRequestHeader().setFechaHoraEnvio("2015-12-16-18.38.55.223456");// Fecha
																									// formato
																									// yyyy-MM-dd-HH24.mm.ss.sss
		// TODO EL ID DE TRANSACCION ES LA COMBINACION DE LA fechaEnvio +
		// codAplicacion + registro
		consultaPorDNIRequest.getRefRequestHeader().setIdTransaccion("20151216183855223456PICP017737");
		consultaPorDNIRequest.getRefRequestHeader().setCodigoInterfaz("CPERRENXDNI");// Parametro
		// TODO ADD-REQUEST
		consultaPorDNIRequest
				.setRefConsultaPorDNIRequest(new com.grupobbva.pe.sir.ents.body.consultapordni.ConsultaPorDNIRequest());
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setTipoAplicacion("E");// Parametro|
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setRegistroCodUsuario(registro);
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setNumeroDNIConsultado(numeroDocumento);
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setIndConsultaDatos("S");// Parametro
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setIndConsultaFoto("N");// Parametro
		consultaPorDNIRequest.getRefConsultaPorDNIRequest().setIndConsultaFirma("N");// Parametro

		ConsultaPorDNIResponse dniResponseDocument = null;
		// TODO CONSULTA A RENIEC DATOS DE PERSONA NATURAL
		dniResponseDocument = wS_PersonaReniec_Service.consultaPorDNI(consultaPorDNIRequest);

		Ciudadano ciudadano = new Ciudadano();
		ResponseHeader responseHeader = dniResponseDocument.getRefResponseHeader();
		if (responseHeader.getCodigoRespuesta().equals("0000")) {
			com.grupobbva.pe.sir.ents.body.consultapordni.ConsultaPorDNIResponse dniResponse = dniResponseDocument
					.getRefConsultaPorDNIResponse();
			RespuestaDNI respuestaDNI = dniResponse.getRespuestaDNI();
			RespuestaDatos respuestaDatos = dniResponse.getRespuestaDatos();
			DatosPersona datosPersona = respuestaDatos.getDatosPersona();
			DatosNacimiento datosNacimiento = respuestaDatos.getDatosNacimiento();
			ciudadano.setFechaCaducidad(respuestaDatos.getDatosDNI().getFechaExpedicion());
			ciudadano.setNumeroDNI(respuestaDNI.getNumDNIConsultado());
			ciudadano.setNombres(datosPersona.getNombres());
			ciudadano.setApellidoPaterno(datosPersona.getApellidoPaterno());
			ciudadano.setApellidoMaterno(datosPersona.getApellidoMaterno());
			ciudadano.setApellidoCasada(datosPersona.getApellidoCasada());
			ciudadano.setSexo(datosPersona.getSexo());
			ciudadano.setCodigoEstadoCivil(datosPersona.getCodigoEstadoCivil());
			ciudadano.setDescripcionEstadoCivil(datosPersona.getDescripcionEstadoCivil());
			ciudadano.setFechaNacimiento(datosNacimiento.getFecha());// DATOS DE
																		// NACIMIENTO
		}
		ciudadano.setCodigoRespuesta(responseHeader.getCodigoRespuesta());
		ciudadano.setMensajeRespuesta(responseHeader.getMensajeRespuesta());
		return ciudadano;
	}

	private ClienteBean validarTipoCliente(String numeroDocumento, String tipoDoc, String tipoDocws, String correo) {
		ClienteBean clienteValida = new ClienteBean();
		ClienteBean clienteEs = null;
		try {
			clienteEs = customerService.obtenerDatosCliente(seguridad.generarTSec(3), tipoDoc, numeroDocumento);

			if (clienteEs.getRptErrorService() == null || clienteEs.getRptErrorService().equals("")) {
				clienteValida = cargarDatosClienteAlta(clienteEs, null, correo);
				return clienteValida;// RETORNA CLIENTE
			} else {
				PersonaBean persona = null;
				persona = personService.buscarNoCliente(tipoDocws, numeroDocumento, "2", seguridad.generarTSec(2));
				if (persona.getRptErrorService() == null || persona.getRptErrorService().equals("")) {// EXISTE
					persona.setNumeroDocumento(numeroDocumento);
					clienteValida = cargarDatosClienteAlta(null, persona, correo);
					return clienteValida; // RETORNA CLIENTE BASADO EN PERSONA
				} else {
					ErrorAtla = persona.getRptErrorService();
					 clienteValida.setRptErrorService(clienteEs.getRptErrorService());; // NO EXISTE COMO CLIENTE NI COMO PERSONA
					 return clienteValida;
				}
			}

		} catch (Exception e) {
			LOGGER.info("Sucedio un Error", e);
			return null;
		}

	}

	private String obtenerSexoPersona(String sexo) {
		String sexoPersona = "";
		if (sexo.equals(PersonService.MASCULINO)) {
			sexoPersona = "M";
		} else if (sexo.equals(PersonService.FEMENINO)) {
			sexoPersona = "F";
		}
		return sexoPersona;
	}

	private String obtenerEstadoCivilPersona(String codigoEstadoCivil) {
		// TODO Auto-generated method stub
		String estadoCivil = "";
		if (codigoEstadoCivil.equals(PersonService.SOLTERO)) {
			estadoCivil = "S";
		} else if (codigoEstadoCivil.equals(PersonService.CASADO)) {
			estadoCivil = "C";
		} else if (codigoEstadoCivil.equals(PersonService.VIUDO)) {
			estadoCivil = "V";
		} else if (codigoEstadoCivil.equals(PersonService.DIVORCIADO)) {
			estadoCivil = "D";
		}

		return estadoCivil;
	}

	private ContratoAltaBean obtenerValoresBean(String llaveunica, // 1
																	// cd_llaveunica
																	// string(32)
			String atributo, // 2 nb_atributo string(50)
			String tarifa, // 3 cd_tarifa string(10)
			Double importeBien, // 4 im_bien numeric(14,2)
			Double importePrestamo, // 5 im_prestamo numeric(14,2)
			Integer diaFacturacion, // 6 nu_diafacturacion numeric(2)
			Integer diaPagocred, // 7 nu_diapagocred numeric(2)
			String tipoDocIdentidad, // 8 tp_docidentidad string(5)
			String numeroDocIdentidad, // 9 cd_docidentidad string(20)
			String direccion, // 10 nb_direccion string(100)
			String departamento, // 11 cd_departamento string(3)
			String provincia, // 12 cd_provincia string(3)
			String distrito, // 13 cd_distrito string(3)
			String telefonoFijo, // 14 nb_numerotelefono_FJ string(15)
			String telefonoReferencia, // 15 nb_numerotelefono_RF string(15)
			String telefonoFinanciado, // 16 nb_numerotelefono_FN string(15)
			String correo, // 17 nb_mail string(50)
			String transacporta, // 18 tp_transacporta string(5)
			String codigoCanal, // 19 cd_canal string(5)
			Integer codigoEntidad, // 21 cd_entidad numeric(5)
			String nombreEntidad, // 22 nb_entidad string(100)
			Integer codigoPuntoVenta, // 23 cd_puntoventa numeric(10)
			String nombrePuntoVenta, // 24 nb_puntoventa string(100)
			Integer tipoEnvio, // 25 tp_envio numeric(5)
			String departamentoFirma // 26 cd_dpto_ptovta string(3)
	) {
		ContratoAltaBean contratoAltaBean = new ContratoAltaBean();

		try {
			contratoAltaBean.setAtributos(atributo);
			contratoAltaBean.setIdPuntoVenta(codigoPuntoVenta);
			contratoAltaBean.setDescripcionPuntoVenta(nombrePuntoVenta);
			contratoAltaBean.setValorEquipo(importeBien);
			contratoAltaBean.setImportePrestamo(importePrestamo);

			contratoAltaBean.setIdTarifa(1);
			contratoAltaBean.setKeyUnica(llaveunica);
			contratoAltaBean.setTipoTransaccion(0);
			contratoAltaBean.setIdEntidad(0);
			contratoAltaBean.setDescripcionEntidad("");
			contratoAltaBean.setIdCanal(2);
			contratoAltaBean.setDescripcionCanal(codigoCanal);
			contratoAltaBean.setDiaPago(diaPagocred);
			contratoAltaBean.getClienteBean().setTipoDocumento("DNI");
			contratoAltaBean.getClienteBean().setNumeroDocumento(numeroDocIdentidad);
			contratoAltaBean.getClienteBean().setCorreoCliente(correo);
			contratoAltaBean.getDomicilio().setDireccion(direccion);
			contratoAltaBean.getDomicilio().setIdDistrito(distrito);
			contratoAltaBean.getDomicilio().setIdDepartamento(departamento);
			contratoAltaBean.getDomicilio().setIdProvincia(provincia);
			contratoAltaBean.getClienteBean().setTelefonoFijo(telefonoFijo);
			contratoAltaBean.getClienteBean().setTelefonoReferencial("");
			contratoAltaBean.setTelefonoFinanciado(telefonoFinanciado);
			contratoAltaBean.setIdTipoEnvio(0);

			contratoAltaBean.setAddDepartamentoFirma("");
			contratoAltaBean.setAddNombreTarifa(tarifa);
			contratoAltaBean.setAdddiaFacturacion(diaFacturacion);
			contratoAltaBean.getClienteBean().setAddtipoDocIdentidad(tipoDocIdentidad);
			contratoAltaBean.setAddtransacporta(transacporta);
			contratoAltaBean.setAddnombreCanal(codigoCanal);

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return contratoAltaBean;
	}

}
