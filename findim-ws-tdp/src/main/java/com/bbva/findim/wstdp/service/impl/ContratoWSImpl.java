package com.bbva.findim.wstdp.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
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
import org.springframework.web.client.RestTemplate;

import com.bbva.findim.bck.service.ApprovalsService;
import com.bbva.findim.bck.service.CustomersService;
import com.bbva.findim.bck.service.LoanService;
import com.bbva.findim.bck.service.PersonService;
import com.bbva.findim.bck.service.ProposalService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.service.TariffService;
import com.bbva.findim.dom.AltaContratoRequest;
import com.bbva.findim.dom.CalificacionClienteBean;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.DatosReniecBean;
import com.bbva.findim.dom.DireccionBean;
import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.dom.GrupoGeografico;
import com.bbva.findim.dom.PersonaBean;
import com.bbva.findim.dom.RespuestaBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.dom.common.ConstantResponseMessage;
import com.bbva.findim.dom.reniec.Ciudadano;
import com.bbva.findim.dom.util.DateUtil;
import com.bbva.findim.sql.service.DatoReniecService;
import com.bbva.findim.sql.service.LogAltaContratoService;
import com.bbva.findim.sql.service.ParametroService;
import com.bbva.findim.wstdp.model.AltaContratoModel;
import com.bbva.findim.wstdp.service.ContratoWS;
import com.bbva.server.reniec.WS_PersonaReniec;

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
	
	@Autowired
	LoanService loanService;
	
	@Autowired
	TariffService tariffService;
	
	@Autowired
	ParametroService parametroService;
	
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
			cliente.setApellidoPaterno(noCliente.getPaterno());
			cliente.setApellidoMaterno(noCliente.getMaterno());
			cliente.setPrimerNombre(noCliente.getNombres());
			cliente.setNombreCompleto(noCliente.getNombres());
			cliente.setGenero(noCliente.getSexo());
			cliente.setEstadoCivil(Integer.parseInt(AltaContratoModel.obtenerEstadoCivilPersona(noCliente.getEstadoCivil(), null)));
			cliente.setFechaNacimiento(noCliente.getFhNacimiento());
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
		RestTemplate restTemplate = new RestTemplate();
		String uriServicio = prop.getProperty("sistema.uriservicio").toString();
		String uriEmpresa = uriServicio + "empresaService/obtenerEmpresa/" + prop.getProperty("empresa.telefonica");
		EmpresaBean empresa = restTemplate.getForObject(uriEmpresa, EmpresaBean.class);
		
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
			
			ContratoAltaBean contratoAltaBean = AltaContratoModel.obtenerValoresBean(
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
			String tipoDoc="DNI";
			String tipoDocws="L";
			PersonaBean personaCreada = null;
			CalificacionClienteBean beanClasificacion = null;
			Double importeCuota = 0.0;
			beanClasificacion = approvalsService.evaluarCliente(tipoDocIdentidad, numeroDocIdentidad, seguridad.generarTSec(2));
			
			if(beanClasificacion.getDescErrorServicio()==null || beanClasificacion.getDescErrorServicio().equals("")){
				if(beanClasificacion.getCodigoResultado()==1){//APROBADO PARAMETRIZAR =1
					clienteAlta = validarTipoCliente(contratoAltaBean.getClienteBean().getNumeroDocumento(),tipoDoc,tipoDocws,contratoAltaBean.getClienteBean().getCorreoCliente());
					if(clienteAlta.getRespuestaService()!=null && clienteAlta.getIdNaturaleza()==1){//NO ES CLIENTE 
						Ciudadano ciudadano = AltaContratoModel.obtenerInformacionReniec(contratoAltaBean.getClienteBean().getNumeroDocumento(), prop, wS_PersonaReniec_Service);
			        	if(ciudadano.getMensajeRespuesta().equals("Exito en Consulta a RENIEC")){
				        	personaCreada = crearNoCliente(ciudadano,contratoAltaBean,tipoDocws);//validar cuando no regresa ciudadano
					    	if(personaCreada.getRespuestaService()==null){
					    		personaCreada.setFhNacimiento(ciudadano.getFechaNacimiento());
					    		personaCreada.setEstadoCivil(ciudadano.getCodigoEstadoCivil());
					    		personaCreada.setSexo(ciudadano.getSexo());
					    		clienteAlta = cargarDatosClienteAlta(null, personaCreada, correo);
								if(datoReniecService.obtenerDatosReniecPersona(personaCreada.getNumeroDocumento())==null){
									DatosReniecBean datoReniec = new DatosReniecBean();
									datoReniec.setDireccionAmdocs(contratoAltaBean.getDomicilio().getDireccion());
									datoReniec.setFechaCaducidad(DateUtil.convertFormat("yyyymmdd", "dd/mm/yy", ciudadano.getFechaCaducidad()));
									datoReniec.setNumeroDni(contratoAltaBean.getClienteBean().getNumeroDocumento());
  									datoReniecService.guardarDatoReniecPersona(datoReniec);
								}
								contratoAltaBean =transaccionAltaContrato(contratoAltaBean,clienteAlta,request.getTarifa());
					    	} else{//ERROR AL CREAR LA PERSONA
				        		request.setTpIndicadorProceso(ConstantResponseMessage.CODE_RPTA_ERROR);
				    			request.setTxMensajeFuncional("ERROR AL CREAR LA PERSONA");
				    			request.setTxCodigoError(personaCreada.getRespuestaService().getErrorCode());
				    			request.setTxMensajeTecnico(personaCreada.getRespuestaService().getErrorDescription());
					    	}
			        	}else{
			        		request.setTxCodigoError(ciudadano.getCodigoRespuesta());
			        		request.setTpIndicadorProceso(ConstantResponseMessage.CODE_RPTA_ERROR);
			    			request.setTxMensajeFuncional("SERVICIO RENIEC NO RESPONDE");
			    			request.setTxMensajeTecnico(ciudadano.getMensajeRespuesta());
			        	}
			        }else{
			        	if(clienteAlta.getIdNaturaleza()==2) {
			        		Ciudadano ciudadano = AltaContratoModel.obtenerInformacionReniec(contratoAltaBean.getClienteBean().getNumeroDocumento(), prop, wS_PersonaReniec_Service);
				        	if(ciudadano.getMensajeRespuesta().equals("Exito en Consulta a RENIEC")){
				        		if(datoReniecService.obtenerDatosReniecPersona(clienteAlta.getNumeroDocumento())==null){
					        		DatosReniecBean datoReniec = new DatosReniecBean();
									datoReniec.setDireccionAmdocs(contratoAltaBean.getDomicilio().getDireccion());
									datoReniec.setFechaCaducidad(DateUtil.convertFormat("yyyymmdd", "dd/mm/yy", ciudadano.getFechaCaducidad()));
									datoReniec.setNumeroDni(contratoAltaBean.getClienteBean().getNumeroDocumento());
									datoReniecService.guardarDatoReniecPersona(datoReniec);
								}
				        	}
			        	}
			        	//reforzar validación
				        	contratoAltaBean =transaccionAltaContrato(contratoAltaBean,clienteAlta,request.getTarifa());
			        }
					//VALIDAR REPUESTA DEL ALTA CONTRATO
				  	if(contratoAltaBean.getRepuestaService().getExitoDescription()!=null){
				  		TarifaBean tarifaBean = tariffService.obtenerTarifa(null, tarifa, seguridad.generarTSec(2), empresa);
						SimulacionBean simulacion = AltaContratoModel.mapper("1", new BigDecimal(importeBien), tarifaBean, new BigDecimal(((importeBien-importePrestamo)*100/importeBien)),
														new BigDecimal((importeBien-importePrestamo)), new BigDecimal(importePrestamo), "");
						simulacion = loanService.simularPrestamo(simulacion, seguridad.generarTSec(2));
				  		if(contratoAltaBean.getRepuestaService().getExitoCode().equals(ConstantResponseMessage.CODE_RPTA_OK)){
				  			request.setTxMensajeFuncional(contratoAltaBean.getRepuestaService().getExitoDescription());
							request.setTxMensajeTecnico(contratoAltaBean.getRepuestaService().getExitoDescription());
							if(simulacion!=null)
								importeCuota = simulacion.getDetalle().get(0).getCuotaTotal();
				  		}else{
				  			request.setTxCodigoError(contratoAltaBean.getRepuestaService().getErrorCode());
				  			request.setTxMensajeFuncional(contratoAltaBean.getRepuestaService().getErrorCode());
							request.setTxMensajeTecnico(contratoAltaBean.getRepuestaService().getErrorCode());
				  		}
					}
				  	request.setRtFiltroCliente(beanClasificacion.getTituloMostrar().toString());
				}else{//RECHAZADO COMPLETAR DATOS DEL RECHAZO PARA EL LOG
					request.setRtFiltroCliente(beanClasificacion.getTituloMostrar());
				  	request.setNbMotivoRechazo(beanClasificacion.getDsResultado()!=null?beanClasificacion.getDsResultado():null);
					request.setTxMensajeFuncional(beanClasificacion.getTituloMostrar());
				}
			}else{//SUCEDIO UN ERROR EN LA EJECUCIÓN DEL FILTRO
				request.setRtFiltroCliente(beanClasificacion.getTituloMostrar());
				request.setTxCodigoError(ConstantResponseMessage.CODE_RPTA_ERROR);
				request.setTxMensajeFuncional(beanClasificacion.getDescErrorServicio());
				request.setTxMensajeTecnico(ConstantResponseMessage.MJS_ERROR_FILTRO);
			}
			//SETEAR REPUESTA CLIENTE
			respuestaTDP.setMensajeFuncional(request.getTxMensajeFuncional());
			respuestaTDP.setMensajeTecnico(request.getTxMensajeTecnico()!=null?request.getTxMensajeTecnico():null);
			respuestaTDP.setIndicadorProceso(0);
			respuestaTDP.setimporteCuota(importeCuota>0?new Float(importeCuota):0);
			
			logAltaContratoService.insert(request);
		  	
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			
			request.setTpIndicadorProceso(ConstantResponseMessage.CODE_RPTA_ERROR);
			request.setTxMensajeFuncional(ConstantResponseMessage.MSJ_EXCEPTION);
			request.setTxMensajeTecnico(ex.getMessage());
			
			respuestaTDP.setMensajeFuncional(request.getTxMensajeFuncional());
			respuestaTDP.setMensajeTecnico(ex.getMessage());
			respuestaTDP.setIndicadorProceso(0);
			respuestaTDP.setimporteCuota((float) 0);

			logAltaContratoService.insert(request);
		}

		long end = System.nanoTime();
		long elapsedTime = TimeUnit.NANOSECONDS.toMillis(end - begin);
		LOGGER.info("SOAP::>Final {} - Transaccion Finalizada en: {} ms", numeroDocIdentidad, elapsedTime);
		
		
		return respuestaTDP;
	}

	private ContratoAltaBean transaccionAltaContrato(ContratoAltaBean contratoAltaBean, ClienteBean clienteAlta, String tarifa)throws Exception {
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
		contrato.setProveedorTercero("TELF");
		contrato.setDescripcionCanal(contratoAltaBean.getDescripcionCanal());
		contrato.setProductoExterno("CTEL000985674");
		contrato = proposalService.altaProposal(seguridad.generarTSec(2), contrato);
		return contrato;
	}

	private PersonaBean crearNoCliente(Ciudadano ciudadano, ContratoAltaBean contratoAltaBean, String tipoDocws) {
		// TODO Auto-generated method stub
		PersonaBean personaUpdate = null;
		PersonaBean personaBean = null;
		try {
		
			personaBean = new PersonaBean();
			personaBean.setNumeroDocumento(contratoAltaBean.getClienteBean().getNumeroDocumento());
			personaBean.setTipoDocumento(tipoDocws);
	
			// TODO VALIDAR COMO SE HARA CON LOS CARACTERES EXTRAÑOS
			personaBean.setNombres(ciudadano.getNombres().replace('Ñ', 'N'));
			personaBean.setPaterno(ciudadano.getApellidoPaterno().replace('Ñ', 'N'));
			personaBean.setMaterno(ciudadano.getApellidoMaterno().replace('Ñ', 'N'));
			// TODO VALIDAR COMO SE HARA CON LOS CARACTERES EXTRAÑOS
	
			personaBean.setClienteNuevo(true);
			personaBean.setCodigoEstadoCivil(AltaContratoModel.obtenerEstadoCivilPersona(null, Integer.parseInt(ciudadano.getCodigoEstadoCivil())));
			personaBean.setCodigoSexo(AltaContratoModel.obtenerSexoPersona(ciudadano.getSexo()));
	
			ciudadano.setFechaNacimiento(ciudadano.getFechaNacimiento().substring(0, 4) + "-" + ciudadano.getFechaNacimiento().substring(4, 6)	+ "-" + ciudadano.getFechaNacimiento().substring(6, 8));
			personaBean.setNacimiento(ciudadano.getFechaNacimiento());
	
			List<GrupoGeografico> grupoGeograficoLst = new ArrayList<>();
			GrupoGeografico grupoGeografico = new GrupoGeografico();
			grupoGeografico.setId("DEPARTMENT");// viene de no cliente
			grupoGeografico.setCode(contratoAltaBean.getDomicilio().getIdDepartamento());// viene
			grupoGeograficoLst.add(grupoGeografico);
	
			GrupoGeografico grupoGeografico2 = new GrupoGeografico();
			grupoGeografico2.setId("PROVINCE");// viene de no cliente
			grupoGeografico2.setCode(contratoAltaBean.getDomicilio().getIdProvincia());// viene
			grupoGeograficoLst.add(grupoGeografico2);
	
			GrupoGeografico grupoGeografico3 = new GrupoGeografico();
			grupoGeografico3.setId("DISTRICT");// viene de no cliente
			grupoGeografico3.setCode(contratoAltaBean.getDomicilio().getIdDistrito());// viene
			grupoGeograficoLst.add(grupoGeografico3);
			
			List<DireccionBean> listDireccion = new ArrayList<DireccionBean>();
			DireccionBean direccionAlta = new DireccionBean();
			direccionAlta.setLstGrupoGeografico(grupoGeograficoLst);
			direccionAlta.setDsDireccion(contratoAltaBean.getDomicilio().getDireccion());
			listDireccion.add(direccionAlta);
			personaBean.setLstDirecciones(listDireccion);
			personaUpdate = personService.altaNoCliente(personaBean, seguridad.generarTSec(2));
		
		} catch (Exception e) {
			ErrorAtla = "Sucedio un Error al crear NoCliente";
			LOGGER.error("Error Al crear el no Cliente.", e);
			return personaUpdate ;
		}
		return personaUpdate;
	}

	private ClienteBean validarTipoCliente(String numeroDocumento, String tipoDoc, String tipoDocws, String correo) {
		ClienteBean clienteValida = new ClienteBean();
		ClienteBean clienteEs = null;
		try {
			clienteEs = customerService.obtenerDatosCliente(seguridad.generarTSec(3), tipoDoc, numeroDocumento);

			if (clienteEs.getRespuestaService().getExitoCode() != null ) {
				clienteValida.setIdNaturaleza(3);
				clienteValida = cargarDatosClienteAlta(clienteEs, null, correo);
				return clienteValida;// RETORNA CLIENTE
			} else {
				PersonaBean persona = null;
				persona = personService.buscarNoCliente(tipoDocws, numeroDocumento, "2", seguridad.generarTSec(2));
				if (persona.getRespuestaService().getExitoCode()!=null) {// EXISTE PERSONA
					persona.setNumeroDocumento(numeroDocumento);
					clienteValida.setIdNaturaleza(2);
					clienteValida = cargarDatosClienteAlta(null, persona, correo);
					return clienteValida; // RETORNA CLIENTE BASADO EN PERSONA
				} else {
					clienteValida.setIdNaturaleza(1);
					clienteValida.setRespuestaService(persona.getRespuestaService()); // NO EXISTE COMO CLIENTE NI COMO PERSONA
					return clienteValida;
				}
			}

		} catch (Exception e) {
			LOGGER.info("Sucedio un Error", e);
			clienteValida.setIdNaturaleza(1);
			return clienteValida;
		}

	}
}
