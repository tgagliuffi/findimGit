package com.bbva.findim.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.cxf.helpers.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.bbva.findim.bck.service.ApprovalsService;
import com.bbva.findim.bck.service.CatalogService;
import com.bbva.findim.bck.service.CustomersService;
import com.bbva.findim.bck.service.LoanService;
import com.bbva.findim.bck.service.PersonService;
import com.bbva.findim.bck.service.ProposalService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.service.TariffService;
import com.bbva.findim.dom.CabeceraBean;
import com.bbva.findim.dom.CalificacionClienteBean;
import com.bbva.findim.dom.CatalogoBean;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoBean;
import com.bbva.findim.dom.DatosLaboralesBean;
import com.bbva.findim.dom.DatosReniecBean;
import com.bbva.findim.dom.DireccionClienteBean;
import com.bbva.findim.dom.DocumentoIdentidadBean;
import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.dom.LogContratoBean;
import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.PersonaBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.dom.UbicacionDireccionBean;
import com.bbva.findim.dom.util.ConvertUtil;
import com.bbva.findim.dom.util.PropertyUtil;
import com.bbva.findim.web.common.Constantes;
import com.bbva.findim.web.service.ContratoRestService;
import com.google.gson.Gson;

@Controller
public class Inicio {
	private static final Logger logger = LogManager.getLogger(Inicio.class);

	@Autowired
	private ContratoRestService contratoRestService;

	@Autowired
	private PropertyUtil prop;

	@Autowired
	private SeguridadBbvaService seguridad;

	@Autowired
	private ApprovalsService approvalsService;

	@Autowired
	ProposalService proposalService;

	@Autowired
	CustomersService customerService;

	@Autowired
	PersonService personService;

	@Autowired
	CatalogService catalogService;

	@Autowired
	private LoanService loanService;

	@Autowired
	private TariffService tariffService;
	

	private static List<String> codigos = null;
	List<ParametroBean> listaDireccionesOracle = null;
	EmpresaBean empresa = null;

	public ClienteBean cargaInicialClienteBean(ClienteBean clienteBean) {
		List<CatalogoBean> catalogoOficios = null;
	    List<CatalogoBean> catalogoDirecciones = null;
		List<ParametroBean> listaDireccionesOracle = null;

		RestTemplate restTemplate = new RestTemplate();
		listaDireccionesOracle = new ArrayList<>();
		String uriServicio = prop.getString("sistema.uriservicio").toString();
		final String uri = uriServicio + "listaDetalleParametro/64";

		try {

			ParametroBean parametros[] = restTemplate.getForObject(uri, ParametroBean[].class);
			listaDireccionesOracle = Arrays.asList(parametros);
			codigos = new ArrayList<>();
			for (ParametroBean parametroBean : listaDireccionesOracle) {
				codigos.add(parametroBean.getNb_paramdetalle());
			}

			//LISTA DE OFICIO
			catalogoOficios = catalogService.obtenerTablaCorporativa("0721", seguridad.generarTSec(3));
			for (CatalogoBean catalogoBean : catalogoOficios) {
				String texto2 = catalogoBean.getStValue().substring(40, 43).trim();
				catalogoBean.setNbCatalogo(texto2);
				String texto1 = catalogoBean.getStValue().substring(0, 40).trim();
				catalogoBean.setStValue(texto1);
			}
			clienteBean.setListaCatalogo(catalogoOficios);
			
			
			//LISTA DEDIRECCIONES PASS
			catalogoDirecciones = catalogService.obtenerTablaCorporativa("0018", seguridad.generarTSec(3));
			for (CatalogoBean catalogoBean : catalogoDirecciones) {
				String texto2 = catalogoBean.getIdCatalogo().substring(1, catalogoBean.getIdCatalogo().length()).trim();
				String remplazado = texto2.replace(".", "");
				catalogoBean.setIdCatalogo(remplazado);
			}
			clienteBean.setListaCatalooDireccion(catalogoDirecciones);
			
			//LISTA DE TIPO_DE_ENVIO
			List<ParametroBean> listaComprobantePago = new ArrayList<>();
			final String uriComprbPago = uriServicio + "listaDetalleParametro/19";
			ParametroBean parametrosComprPago[] = restTemplate.getForObject(uriComprbPago, ParametroBean[].class);
			listaComprobantePago = Arrays.asList(parametrosComprPago);
			clienteBean.setListaComprobantePago(listaComprobantePago);
			
			return clienteBean;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return clienteBean;
		}
	}

	@RequestMapping(value = "/consultaCliente/{paginaVista}", method = RequestMethod.GET)
	public String navegacionVista(@PathVariable String paginaVista, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String paginaFiltro = prop.getString("pagina.filtrocliente");
		String paginaSimulacion = prop.getString("pagina.simulacion");
		String paginaBusquedaContrato = prop.getString("pagina.busquedacontrato");
		String bioMatchActivo = prop.getString("bio.match.activo");
		String bioMatchUrlBase = prop.getString("bio.match.local.ws");
		String pagina = "index";
		String uriServicio = prop.getString("sistema.uriservicio").toString();
		String uriEmpresa = uriServicio + "empresaService/obtenerEmpresa/" + prop.getString("empresa.telefonica");

		try {
			RestTemplate restTemplate = new RestTemplate();
			empresa = restTemplate.getForObject(uriEmpresa, EmpresaBean.class);

			pagina = (paginaVista.equalsIgnoreCase(paginaFiltro) ? paginaFiltro
					: (paginaVista.equalsIgnoreCase(paginaSimulacion) ? paginaSimulacion
							: (paginaVista.equalsIgnoreCase(paginaBusquedaContrato) ? paginaBusquedaContrato
									: (pagina))));
			model.addAttribute("bioMatchActivo", "1".equals(bioMatchActivo)); 
			model.addAttribute("bioMatchUrlBase", bioMatchUrlBase);
			request.getSession().setAttribute("empresa", empresa);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return pagina;
	}

	@RequestMapping("impresionAnulacion")
	public ModelAndView impresionAnulacion(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("impresionAnulacion");

		String dniCliente = request.getParameter("dniCliente");
		String nombreCliente = request.getParameter("nombreCliente");
		String numeroContrato = request.getParameter("numeroContrato");
		Integer cdContrato = ConvertUtil.convertToInteger(request.getParameter("cdContrato"));
		logger.info("cdContrato: " + cdContrato);

		LogContratoBean logContratoBean = new LogContratoBean();
		logContratoBean.setCodigoContrato(cdContrato);
		contratoRestService.registrarLogContrato(logContratoBean);

		mv.addObject("dniCliente", dniCliente);
		mv.addObject("nombreCliente", nombreCliente);
		mv.addObject("numeroContrato", numeroContrato);

		return mv;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		try {
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			String formattedDate = dateFormat.format(date);
			model.addAttribute("serverTime", formattedDate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "index";
	}

	@RequestMapping(value = "/filtroCliente", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody String filtroCliente(Model model,
			@RequestParam(value = "tipoDocumento", required = false) String tipoDocumento,
			@RequestParam(value = "numeroDocumento", required = false) String numeroDocumento,
			HttpServletRequest request, HttpServletResponse response) {
		Gson gson = null;
		String gsonString = null;
		CalificacionClienteBean calificacionClienteBean = null;
		try {
			logger.info("INICIO EL LLAMADO APPROVALS");
			calificacionClienteBean = new CalificacionClienteBean();
			String tsecApp = seguridad.generarTSec(3);
			calificacionClienteBean = approvalsService.evaluarCliente(tipoDocumento, numeroDocumento, tsecApp);
			gson = new Gson();
			gsonString = gson.toJson(calificacionClienteBean);
			logger.info("ClienteBean:" + calificacionClienteBean);
			logger.info("FIN EL LLAMADO APPROVALS");

		} catch (Exception e) {
			calificacionClienteBean.setTipoRespuesta(0);
			gsonString = gson.toJson(calificacionClienteBean);
			logger.error(e.getMessage(), e);
			return gsonString;
		}
		return gsonString;
	}

	@RequestMapping(value = "/guardarDatosComplementarios", method = RequestMethod.POST, produces = Constantes.APPLICATION_JSON_UTF_8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String guardarDatosComplementarios(@RequestBody ContratoBean contratoBean, HttpSession session) {
		logger.debug("Inicio:guardarDatosComplementarios");

		// Recuperar Cliente de Session
		ClienteBean clienteBean = (ClienteBean) session.getAttribute("clienteSession");
		EmpresaBean empresa = null;
		DatosReniecBean datosNoClienteReniec = new DatosReniecBean();

		RestTemplate restTemplate = new RestTemplate();
		String uriServicio = prop.getString("sistema.uriservicio").toString();
		final String uriReniec = uriServicio + "obtenerDatoReniec/" + clienteBean.getNumeroDocumento();
		final String uriGetAdressFormatHost = uriServicio + "obtenerDireccionHost/";
		String gsonString = null;
		int rptaUpdatePropuesta = 0;

		try {
			empresa = (EmpresaBean) session.getAttribute("empresa");
			// ******************************************************************************************************************************
			// COMPLETAR DATOS NOCLIENTE/CLIENTE
			// *****************************************************************************************************************************
			if (empresa != null) {
				if (!clienteBean.isEsCliente()) {// FLUJO NO CLIENTE
					clienteBean.setDatosLaboralesBean((new DatosLaboralesBean()));
					clienteBean.getDatosLaboralesBean().setIdOcupacion((contratoBean.getIdTipoOcupacion()));
					
					datosNoClienteReniec = restTemplate.getForObject(uriReniec, DatosReniecBean.class);
					String newAdressHost = restTemplate.getForObject(uriGetAdressFormatHost+datosNoClienteReniec.getNumeroDni(), String.class);
					clienteBean.setDireccion(newAdressHost);
					clienteBean = mapperPersonForClient(clienteBean, null, datosNoClienteReniec, "PER");
					clienteBean = customerService.altaCliente(seguridad.generarTSec(3), clienteBean, null);

					if (clienteBean.getRepuestaService().getExitoDescription()!=null) {
						ContratoBean contratoUpdate = new ContratoBean();
						contratoUpdate.setCodigoContrato(contratoBean.getCodigoContrato());
						contratoUpdate.setTipoEnvio(contratoBean.getNombreEnvio());
						contratoUpdate.setCorreo(contratoBean.getCorreo());
						contratoUpdate.setEstadoContrato(ProposalService.EstadoHost.PENDING_SIGNATURE.getEstado());

						ClienteBean clienteBeanPDF = generarPDF(clienteBean, contratoBean);
						if (clienteBeanPDF != null) {
							rptaUpdatePropuesta = proposalService.updateProposal(seguridad.generarTSec(3),
									contratoUpdate);
							clienteBean = clienteBeanPDF;
						}
						
						String userType = (String) session.getAttribute("seguridad_usertype");
						String seguridadUser = (String) session.getAttribute("seguridad_user");
						clienteBean.setRutaServicioRest("");//lienteBean.setRutaServicioRest(rutaServicioRest);
						clienteBean.setSeguridad_usertype(userType);
						clienteBean.setSeguridad_user(seguridadUser);
						
						if (rptaUpdatePropuesta == 1) {
							empresa = (EmpresaBean) session.getAttribute("empresa");
							if (empresa != null) {
								List<ContratoBean> contratos = proposalService.listarPropuesta(seguridad.generarTSec(3),
										empresa.getIndenticador(), empresa.getCdEmpresa(), "DNI",
										clienteBean.getNumeroDocumento(), empresa.getFechaExpiracion());
								clienteBean.setListaContrato(contratos);
								session.setAttribute("clienteSession", clienteBean);
							}
						}
					} else {
						//Error al grabar cliente
					}
				} else {// FLUJO CLIENTE
					ContratoBean contratoUpdate = new ContratoBean();
					contratoUpdate.setCodigoContrato(contratoBean.getCodigoContrato());
					contratoUpdate.setTipoEnvio(contratoBean.getNombreEnvio());
					contratoUpdate.setCorreo(contratoBean.getCorreo());
					
					if(contratoBean.getEstadoContrato()!=null)
					contratoUpdate.setEstadoContrato(contratoBean.getEstadoContrato());

					ClienteBean clienteBeanPDF = generarPDF(clienteBean, contratoBean);
					if (clienteBeanPDF != null) {
						rptaUpdatePropuesta = proposalService.updateProposal(seguridad.generarTSec(3), contratoUpdate);
						clienteBean = clienteBeanPDF;
					}

					if (rptaUpdatePropuesta == 1) {
						empresa = (EmpresaBean) session.getAttribute("empresa");
						if (empresa != null) {
							List<ContratoBean> contratos = proposalService.listarPropuesta(seguridad.generarTSec(3),
									empresa.getIndenticador(), empresa.getCdEmpresa(), "DNI",
									clienteBean.getNumeroDocumento(), empresa.getFechaExpiracion());
							clienteBean.setListaContrato(contratos);
							session.setAttribute("clienteSession", clienteBean);
						}
					}
				}

				List<ContratoBean> contratos = proposalService.listarPropuesta(seguridad.generarTSec(3),
						empresa.getIndenticador(), empresa.getCdEmpresa(), "DNI", clienteBean.getNumeroDocumento(),
						empresa.getFechaExpiracion());
				clienteBean.setListaContrato(contratos);
				gsonString = new Gson().toJson(clienteBean);
				logger.info("ClienteBean:" + clienteBean);

			}
		} catch (Exception e) {
			logger.error("Sucedío un error en el metodo guardarDatosComplementarios ", e);
		}
		return gsonString;
	}

	private ClienteBean generarPDF(ClienteBean clienteBean, ContratoBean contratoBean) {

		ClienteBean response = null;
		try {
			SimulacionBean simulacionBean = null;
			List<TarifaBean> listaTarifa = new ArrayList<TarifaBean>();
			TarifaBean tarifaBean = tariffService.obtenerTarifa(null, contratoBean.getCodigoTarifa(),
					seguridad.generarTSec(3), empresa);
			listaTarifa.add(tarifaBean);
			simulacionBean = mapper(contratoBean.getTipoMoneda(), new BigDecimal(contratoBean.getImporteBien()), // valorEquipo,
					tarifaBean, null, new BigDecimal(contratoBean.getImporteInicial()), // cuotaInicial,
					new BigDecimal(contratoBean.getImportePrestamo()), contratoBean.getIdTipoEnvio().toString(), 0,
					clienteBean.getFechaNacimiento());

			SimulacionBean cronograma = loanService.simularPrestamo(simulacionBean, seguridad.generarTSec(3));
			cronograma.setListaTarifa(listaTarifa);
			clienteBean.setSimulacionBean(cronograma);
			clienteBean.setIdContrato(contratoBean.getCodigoContrato());
			String rutaServicioRest = prop.getString("sistema.uriservicio");
			clienteBean.setRutaServicioRest(rutaServicioRest);
			response = contratoRestService.generarPDF(clienteBean);
		} catch (Exception e) {
			response = null;
			logger.error("Sucedío un error en el metodo guardarDatosComplementarios ", e);
		}

		return response;
	}

	@RequestMapping(value = "/busquedaContrato", method = RequestMethod.GET, produces = Constantes.APPLICATION_JSON_UTF_8_VALUE)
	@ResponseBody
	public String busquedaContrato(Model model,
			@RequestParam(value = "tipoDocumento", required = false) String tipoDocumento,
			@RequestParam(value = "numeroDocumento", required = false) String numeroDocumento,
			HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		String seguridad_data	   = (String) session.getAttribute("seguridad_data");	   
		String seguridad_user      = (String) session.getAttribute("seguridad_user");     
		String seguridad_usertype  = (String) session.getAttribute("seguridad_usertype"); 
		String seguridad_channel   = (String) session.getAttribute("seguridad_channel");  
		String seguridad_message   = (String) session.getAttribute("seguridad_message");  
		String seguridad_status    = (String) session.getAttribute("seguridad_status");  
		
		String gsonString = null;
		EmpresaBean empresa = null;
		ClienteBean clienteAltaBean = null;
		List<ContratoBean> lista = null;
		String msgErrorFront = null;
		
		RestTemplate restTemplate = new RestTemplate();
		String uriServicio = prop.getString("sistema.uriservicio").toString();
		
		try {
			empresa = (EmpresaBean) request.getSession().getAttribute("empresa");
			if (empresa != null) {
				clienteAltaBean = new ClienteBean();
				lista = new ArrayList<>();
				String tipoDoc = "DNI";
				String tipoDocws = "L";
		
				lista = proposalService.listarPropuesta(seguridad.generarTSec(3), empresa.getIndenticador(),empresa.getCdEmpresa(), tipoDoc, numeroDocumento, empresa.getFechaExpiracion());
					
				if(lista!=null && lista.size()>0){
					clienteAltaBean = customerService.obtenerDatosCliente(seguridad.generarTSec(3), tipoDoc,numeroDocumento);// OBTENEMOS LA INFORMACION DEL CLIENTE
					
					/*----------Seguridad INI-----------------------------------------------------------*/
					clienteAltaBean.setSeguridad_data(seguridad_data);    
					clienteAltaBean.setSeguridad_user(seguridad_user);
					clienteAltaBean.setSeguridad_usertype(seguridad_usertype);
					clienteAltaBean.setSeguridad_channel(seguridad_channel);
					clienteAltaBean.setSeguridad_message(seguridad_message);
					clienteAltaBean.setSeguridad_status(seguridad_status);
					  
					/*----------Seguridad FIN-----------------------------------------------------------*/
					 
					if(clienteAltaBean.getRepuestaService().getErrorCode()!=null && clienteAltaBean.getRepuestaService().getErrorDescription().indexOf("PERSONA INEXISTENTE")>0){//NO EXISTE EL CLIENTE
						PersonaBean persona = null;
						persona = personService.buscarNoCliente(tipoDocws, numeroDocumento, "2",seguridad.generarTSec(3));// OBTENIENDO INFORMACION DEL NO CLIENTE
								if (persona.getRespuestaService() == null) {//se encontro persona
									final String uriReniec = uriServicio + "obtenerDatoReniec/" + numeroDocumento;
									DatosReniecBean datosNoClienteReniec = restTemplate.getForObject(uriReniec, DatosReniecBean.class);
									persona.setDsDireccionAndocs(datosNoClienteReniec!=null?datosNoClienteReniec.getDireccionAmdocs():null);
									persona.setCaducidad(datosNoClienteReniec!=null?datosNoClienteReniec.getFechaCaducidad():null);
									clienteAltaBean = mapperPersonForClient(clienteAltaBean,persona, null, "PER");
								} else {
									clienteAltaBean.setTipoRespuesta(1);
									msgErrorFront = "Sucedio un error.";
								}
					}else{
						clienteAltaBean.setEsCliente(true);
						clienteAltaBean.setNumeroDocumento(numeroDocumento);
						clienteAltaBean.setTipoDocumento(tipoDocumento);
						clienteAltaBean.setNombreCompleto((clienteAltaBean.getPrimerNombre() != null ? clienteAltaBean.getPrimerNombre() : "")	+ " " + (clienteAltaBean.getSegundoNombre() != null ? clienteAltaBean.getSegundoNombre() : "")	+ " " + clienteAltaBean.getApellidoPaterno() + " " + clienteAltaBean.getApellidoMaterno());
						clienteAltaBean = cargaInicialClienteBean(clienteAltaBean);
						clienteAltaBean.setDireccion(clienteAltaBean.getDireccionCliente().getUbicacion().getDsDireccionCompleta());
						clienteAltaBean.setCargo(obtenerCargo(clienteAltaBean.getIdTipoOcupacion(), clienteAltaBean.getListaCatalogo()));
						clienteAltaBean.setTipoRespuesta(1);
					}
					
					if (msgErrorFront==null && lista.size() > 0) {
						clienteAltaBean.setNumeroDocumento(numeroDocumento);
						clienteAltaBean.setTipoDocumento(tipoDocumento);
						clienteAltaBean.setListaContrato(lista);
					}
				}else{
					clienteAltaBean.setTipoRespuesta(1);
					clienteAltaBean.setTipoError(2);
				}
				clienteAltaBean.setTipoRespuesta(1);
				request.getSession().setAttribute("clienteSession", clienteAltaBean);
				Gson gson = new Gson();
				gsonString = gson.toJson(clienteAltaBean);
			}

			logger.debug("busquedaContrato===========>:" + clienteAltaBean);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			request.getSession().setAttribute("clienteSession", clienteAltaBean);
			Gson gson = new Gson();
			gsonString = gson.toJson(clienteAltaBean);
			return gsonString;
		}
		return gsonString;
	}
	
	private ClienteBean mapperPersonForClient(ClienteBean clienteAltaBean, PersonaBean persona, DatosReniecBean clienteReniec, String cdPais) {
		// TODO Auto-generated method stub
		clienteAltaBean.setEsCliente(false);
		clienteAltaBean = cargaInicialClienteBean(clienteAltaBean);
		clienteAltaBean.setCargo(obtenerCargo(clienteAltaBean.getIdTipoOcupacion(), clienteAltaBean.getListaCatalogo()));

		if(persona!=null) {
			clienteAltaBean.setPrimerNombre(persona.getNombres());
			clienteAltaBean.setApellidoPaterno(persona.getPaterno());
			clienteAltaBean.setApellidoMaterno(persona.getMaterno());
			clienteAltaBean.setNombreCompleto(persona.getNombres() + " " + persona.getPaterno() + " " + persona.getMaterno());
			clienteAltaBean.setFechaNacimiento(persona.getNacimiento());
			
			clienteAltaBean.setGenero(persona.getCodigoSexo().equals(Constantes.Genero.M.toString())?Constantes.Genero.M.getGenero():Constantes.Genero.F.getGenero());// 
			
			if(persona.getEstadoCivil().equals(Constantes.EstadoCivil.S.name()))
				clienteAltaBean.setEstadoCivil(Constantes.EstadoCivil.S.getEstadoCivil());
			else if (persona.getEstadoCivil().equals(Constantes.EstadoCivil.C.name()))
				clienteAltaBean.setEstadoCivil(Constantes.EstadoCivil.C.getEstadoCivil());
			else if  (persona.getEstadoCivil().equals(Constantes.EstadoCivil.D.name()))
				clienteAltaBean.setEstadoCivil(Constantes.EstadoCivil.D.getEstadoCivil());
			else if  (persona.getEstadoCivil().equals(Constantes.EstadoCivil.V.name()))
				clienteAltaBean.setEstadoCivil(Constantes.EstadoCivil.V.getEstadoCivil());
			else 
				clienteAltaBean.setEstadoCivil(Constantes.EstadoCivil.S.getEstadoCivil());
			
			if(persona.getDsDireccionAndocs()!=null) {
				clienteAltaBean.setDireccionCliente(new DireccionClienteBean());
				clienteAltaBean.getDireccionCliente().setStrDireccionInputTlf(persona.getDsDireccionAndocs());
				clienteAltaBean.getDireccionCliente().setUbicacion(new UbicacionDireccionBean());
				clienteAltaBean.getDireccionCliente().getUbicacion().setDsUbigeo(persona.getDsUbigeoComplet());
				clienteAltaBean.getDireccionCliente().getUbicacion().setNbPais(cdPais);
				clienteAltaBean.setDireccion(persona.getDsDireccionAndocs().replace("(|)", " "));
			}
			
			clienteAltaBean.setLstDocumentoIdentidadBean(new ArrayList<DocumentoIdentidadBean>());
			clienteAltaBean.getLstDocumentoIdentidadBean().add(new DocumentoIdentidadBean());
			clienteAltaBean.getLstDocumentoIdentidadBean().get(0).setPais(cdPais);
			clienteAltaBean.getLstDocumentoIdentidadBean().get(0).setNroDocumento(persona.getNumeroDocumento());
			clienteAltaBean.getLstDocumentoIdentidadBean().get(0).setTipoDocumentoIdentidad(persona.getTipoDocumento());
			
			clienteAltaBean.setNacionalidades(new ArrayList<>());
			clienteAltaBean.getNacionalidades().add(cdPais);
		}else {
		
			clienteAltaBean.getDireccionCliente().setStrDireccionInputTlf(clienteAltaBean.getDireccion());
			clienteAltaBean.getDireccionCliente().getUbicacion().setNbPais(cdPais);
			
			clienteAltaBean.setLstDocumentoIdentidadBean(new ArrayList<DocumentoIdentidadBean>());
			clienteAltaBean.getLstDocumentoIdentidadBean().add(new DocumentoIdentidadBean());
			clienteAltaBean.getLstDocumentoIdentidadBean().get(0).setPais(cdPais);
			clienteAltaBean.getLstDocumentoIdentidadBean().get(0).setNroDocumento(clienteAltaBean.getNumeroDocumento());
			clienteAltaBean.getLstDocumentoIdentidadBean().get(0).setTipoDocumentoIdentidad(clienteAltaBean.getTipoDocumento());
			clienteAltaBean.setNacionalidades(new ArrayList<>());
			clienteAltaBean.getNacionalidades().add(cdPais);
		}

		

		return clienteAltaBean;
	}

	private String obtenerCargo(String idTipoOcupacion, List<CatalogoBean> catalogoOficios2) {
		String cargo = "";
		for (CatalogoBean catalogoBean : catalogoOficios2) {
			if (catalogoBean.getIdCatalogo().equals(idTipoOcupacion)) {
				cargo = catalogoBean.getStValue();
				break;
			}
		}
		return cargo;
	}

	@RequestMapping(value = "/tarifa", method = RequestMethod.GET)
	@ResponseBody
	public String tarifa(Model model, HttpServletRequest request, HttpServletResponse response) {

		Gson gson = new Gson();
		String gsonString = null;
		TarifaBean tarifaBean = null;

		String uriServicio = prop.getString("sistema.uriservicio").toString();
		String fecha = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		final String uri = uriServicio + "tarifa/" + fecha;

		try {
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> map = new HashMap<String, String>();
			ResponseEntity<TarifaBean> responseEntity = restTemplate.getForEntity(uri, TarifaBean.class, map);

			tarifaBean = responseEntity.getBody();
			gson = new Gson();
			gsonString = gson.toJson(tarifaBean);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return gsonString;
	}

	@RequestMapping(value = "/contrato/pdf/{filename}/view")
	public void getPdfPath(@PathVariable("filename") String filename, HttpServletResponse response) throws IOException {
		String baseDirectory = "";
//		String uriServicio = prop.getString("sistema.uriservicio");
		if(filename.startsWith(com.bbva.findim.dom.common.Constantes.DOCUMENTO_ACE))
			baseDirectory = prop.getString("sistema.ruta.generacion.contrato");
		else
			baseDirectory = prop.getString("sistema.ruta.contrato.consolidado");	
		String path = baseDirectory + filename;
		try (InputStream is = new FileInputStream(new File(path))) {
			response.setContentType("application/pdf");
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		}
	}

	@RequestMapping(value = "firmarContrato", method = RequestMethod.POST)
	@ResponseBody
	public ClienteBean firmarContrato(@RequestBody ContratoBean contrato, HttpSession session) {
		logger.info("tipoDocumento: " + contrato.getClienteContrato().getTipoDocumento());
		logger.info("numeroDocumento: " + contrato.getClienteContrato().getNumeroDocumento());
		logger.info("codigoContrato: " + contrato.getCodigoContrato());
		logger.info("idCliente: " + contrato.getClienteContrato().getIdCliente());
		ClienteBean clienteBean = null;

		try {
			clienteBean = (ClienteBean) session.getAttribute("clienteSession");
			String uriServicio = prop.getString("sistema.uriservicio");
			// String uriTemplate = uriServicio +
			// "firmarContrato/{codigoAuxiliar}/{tipoDocumento}/{numeroDocumento}/{codigoContrato}/{idCliente}/{seguridad_usertype}/{seguridad_user}";
			String uriTemplate = uriServicio
					+ "firmarContrato/{tipoDocumento}/{numeroDocumento}/{codigoContrato}/{idCliente}/{seguridad_usertype}/{seguridad_user}";

			Optional<ContratoBean> resultContrato = clienteBean.getListaContrato().stream()
					.filter(x -> x.getCodigoContrato().equals(contrato.getCodigoContrato())).findFirst();
			String tipoEnvio = (resultContrato.isPresent() ? resultContrato.get().getTipoEnvio() : "");
			String correo = (resultContrato.isPresent() ? resultContrato.get().getCorreo() : "");

			Map<String, Object> params = new HashMap<>(6);
			params.put("tipoDocumento", clienteBean.getTipoDocumento());
			params.put("numeroDocumento", clienteBean.getNumeroDocumento());
			params.put("codigoContrato", contrato.getCodigoContrato());
			params.put("idCliente", contrato.getClienteContrato().getIdCliente());
			params.put("seguridad_usertype", (String) session.getAttribute("seguridad_usertype"));
			params.put("seguridad_user", (String) session.getAttribute("seguridad_user"));

			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<ClienteBean> responseEntity = restTemplate.getForEntity(uriTemplate, ClienteBean.class,
					params);
			ClienteBean clienteBeanFirma = responseEntity.getBody();

			ContratoBean contratoUpdate = new ContratoBean();
			contratoUpdate.setCodigoContrato(contrato.getCodigoContrato());
			contratoUpdate.setTipoEnvio(tipoEnvio);
			contratoUpdate.setCorreo(correo);
			contratoUpdate.setEstadoContrato(ProposalService.EstadoHost.SIGNED.getEstado());

			int rptaUpdatePropuesta = proposalService.updateProposal(seguridad.generarTSec(3), contratoUpdate);

			if (rptaUpdatePropuesta == 1) {
				empresa = (EmpresaBean) session.getAttribute("empresa");
				if (empresa != null) {
					List<ContratoBean> contratos = proposalService.listarPropuesta(seguridad.generarTSec(3),
							empresa.getIndenticador(), empresa.getCdEmpresa(), clienteBean.getTipoDocumento(),
							clienteBean.getNumeroDocumento(), empresa.getFechaExpiracion());
					clienteBean.setListaContrato(contratos);
					session.setAttribute("clienteSession", clienteBean);
				}
			}
			clienteBean.setResultadoFirma(clienteBeanFirma.getResultadoFirma());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return clienteBean;
	}

	public SimulacionBean mapper(String tipoMoneda, BigDecimal valorEquipo, TarifaBean tarifaFinanciamiento,
			BigDecimal porcentajeCuotaInicial, BigDecimal cuotaInicial, BigDecimal saldoAFinanciar, String metodoEnvio,
			int diaPago, String fechaNacimientoCliente) {
		SimulacionBean simulacion = new SimulacionBean();

		simulacion.setCdSubProducto(tarifaFinanciamiento.getCdSubProducto());
		simulacion.setCabecera(new CabeceraBean());
		simulacion.getCabecera().setCuotaInicial(cuotaInicial);
		simulacion.getCabecera().setMoneda(tipoMoneda);

		if (metodoEnvio.equals("0")) {// VIRTUAL
			simulacion.getCabecera().setMetodoEnvio(0);
		} else if (metodoEnvio.equals("10")) {// FISICO
			simulacion.getCabecera().setMetodoEnvio(1);
		} else if (metodoEnvio.equals("20")) {// AMBOS
			simulacion.getCabecera().setMetodoEnvio(1);
		}

		simulacion.getCabecera().setSaldoFinanciar(saldoAFinanciar);
		simulacion.getCabecera().setNumeroCuotas(Integer.parseInt(tarifaFinanciamiento.getCuota()));
		simulacion.getCabecera().setDiaPago(diaPago);
		simulacion.getCabecera().setTea(tarifaFinanciamiento.getTasa());
		simulacion.getCabecera().setCodigoTarifa(tarifaFinanciamiento.getCodigoTarifa());
		simulacion.getCabecera().setValorEquipo(valorEquipo);

		simulacion.setFechaNacimientoCliente(fechaNacimientoCliente);

		return simulacion;

	}

}
