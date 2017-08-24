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
import java.util.regex.Pattern;

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
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoBean;
import com.bbva.findim.dom.DatosReniecBean;
import com.bbva.findim.dom.LogContratoBean;
import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.web.common.Constantes;
import com.bbva.findim.dom.util.ConvertUtil;
import com.bbva.findim.dom.util.PropertyUtil;
import com.bbva.findim.dom.CalificacionClienteBean;
import com.bbva.findim.dom.CatalogoBean;
import com.bbva.findim.dom.DatosLaboralesBean;
import com.bbva.findim.dom.DireccionBean;
import com.bbva.findim.dom.DocumentoIdentidadBean;
import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.dom.GrupoGeografico;
import com.bbva.findim.dom.PersonaBean;
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
		String gsonString = null;

		String rptaAltaCliente = null;
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
					if (datosNoClienteReniec == null) {
						// TODO PENDIENTE LLAMADA A RENIEC(SOLO EN CASO ESPECIAL
						// QUE EL NO CLIENTE YA EXISITIA
						datosNoClienteReniec = new DatosReniecBean();
						datosNoClienteReniec.setDireccionAmdocs(
								"AV(|)Caminos del Inca(|)Nro. 3755(|)Mz.W4/ Lt.19(|)Piso 10(|)Dpto./Int. 101(|)URB(|)LOS ROSALES(|)");
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String stringFechaConHora = "2020-09-15";
						Date fechaConHora = sdf.parse(stringFechaConHora);
						datosNoClienteReniec.setFechaCaducidad(fechaConHora);
						datosNoClienteReniec.setNumeroDni(clienteBean.getNumeroDocumento());
					}
					clienteBean = completarDatosCliente(clienteBean, datosNoClienteReniec);
					rptaAltaCliente = customerService.altaCliente(seguridad.generarTSec(3), clienteBean, null);

					if (rptaAltaCliente.equals("[OK. ALTA EFECTUADA]")) {
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

						//TODO REVISAR ESTO
						String userType = (String) session.getAttribute("seguridad_usertype");
						String seguridadUser = (String) session.getAttribute("seguridad_user");
//						clienteBean.setRutaServicioRest(rutaServicioRest);
//						clienteBean.setSeguridad_usertype(userType);
//						clienteBean.setSeguridad_user(seguridadUser);
						
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
			// ite : Cambiar nombre generarPfd //validar generacion de contratos
			// y condicionar el flujo
			// TODO ppazos: se esta cayendo en este metodo por eso no esta
			// refrescando la lista de contratos
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

		
		String gsonString = null;
		EmpresaBean empresa = null;
		ClienteBean customerEnvio = null;
		List<ContratoBean> lista = null;
		String msgErrorFront = null;
		try {
			empresa = (EmpresaBean) request.getSession().getAttribute("empresa");
			if (empresa != null) {
				customerEnvio = new ClienteBean();
				lista = new ArrayList<>();
				String tipoDoc = "DNI";
				String tipoDocws = "L";
		
				lista = proposalService.listarPropuesta(seguridad.generarTSec(3), empresa.getIndenticador(),empresa.getCdEmpresa(), tipoDoc, numeroDocumento, empresa.getFechaExpiracion());
					
				if(lista!=null && lista.size()>0){
								customerEnvio = customerService.obtenerDatosCliente(seguridad.generarTSec(3), tipoDoc,numeroDocumento);// OBTENEMOS LA INFORMACION DEL CLIENTE
					if(customerEnvio.getRptErrorService()!=null && customerEnvio.getRptErrorService().indexOf("CLIENTE NO EXISTE")>0){//NO EXISTE EL CLIENTE
						PersonaBean persona = null;
						persona = personService.buscarNoCliente(tipoDocws, numeroDocumento, "2",seguridad.generarTSec(3));// OBTENIENDO INFORMACION DEL NO CLIENTE
						if (persona.getRptErrorService() == null) {
							customerEnvio = mapperCustomerPersona(customerEnvio,persona);
						} else {//PERCY : SALIMOS PORQUE ES UN ERROR - MOSTRAR MENSAJE
							customerEnvio.setTipoRespuesta(1);
							msgErrorFront = "Sucedio un error.";
						}
					}else{
						customerEnvio.setEsCliente(true);
						customerEnvio.setNumeroDocumento(numeroDocumento);
						customerEnvio.setTipoDocumento(tipoDocumento);
						customerEnvio.setNombreCompleto((customerEnvio.getPrimerNombre() != null ? customerEnvio.getPrimerNombre() : "")
										+ " " + (customerEnvio.getSegundoNombre() != null ? customerEnvio.getSegundoNombre() : "")
										+ " " + customerEnvio.getApellidoPaterno() + " " + customerEnvio.getApellidoMaterno());
						customerEnvio = cargaInicialClienteBean(customerEnvio);
						//TODO VALIDAR MENSAJE DE ERROR EN EL CATCH
						customerEnvio.setCargo(obtenerCargo(customerEnvio.getIdTipoOcupacion(), customerEnvio.getListaCatalogo()));
						customerEnvio.setTipoRespuesta(1);
					}
					
					if (msgErrorFront==null && lista.size() > 0) {
						customerEnvio.setTipoDocumento(tipoDocumento);
						customerEnvio.setListaContrato(lista);
					}
				}else{
					customerEnvio.setTipoRespuesta(1);
					customerEnvio.setTipoError(2);
				}

				request.getSession().setAttribute("clienteSession", customerEnvio);
				Gson gson = new Gson();
				gsonString = gson.toJson(customerEnvio);
			}

			logger.debug("busquedaContrato===========>:" + customerEnvio);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			request.getSession().setAttribute("clienteSession", customerEnvio);
			Gson gson = new Gson();
			gsonString = gson.toJson(customerEnvio);
			return gsonString;
		}
		return gsonString;
	}

	private ClienteBean mapperCustomerPersona(ClienteBean customerEnvio, PersonaBean persona) {
		// TODO Auto-generated method stub
		customerEnvio.setEsCliente(false);
		customerEnvio = cargaInicialClienteBean(customerEnvio);
		customerEnvio.setCargo(obtenerCargo(customerEnvio.getIdTipoOcupacion(), customerEnvio.getListaCatalogo()));

	
		customerEnvio.setTipoRespuesta(1);
		customerEnvio.setPrimerNombre(persona.getNombres());
		customerEnvio.setApellidoPaterno(persona.getPaterno());
		customerEnvio.setApellidoMaterno(persona.getMaterno());
		customerEnvio.setFechaNacimiento(persona.getNacimiento());

		DocumentoIdentidadBean documentoIdentidadBean = new DocumentoIdentidadBean();
		/* PER */documentoIdentidadBean
				.setPais(persona.getLstDirecciones().get(0).getIdCountry());// VALIDAR
		// documentoIdentidadBean.setFechaExpiracion((java.sql.Date)
		// persona.getCaducidad());//validar PER
		customerEnvio.setLstDocumentoIdentidadBean(new ArrayList<DocumentoIdentidadBean>());
		customerEnvio.getLstDocumentoIdentidadBean().add(documentoIdentidadBean);

		// pendiente
		List<String> nacionalidades = new ArrayList<>();
		nacionalidades.add("PER");// se setea al otro  lado
		customerEnvio.setNacionalidades(nacionalidades);

		// DATOS DIRECCION
		DireccionBean direccionBean = new DireccionBean();
		direccionBean.setDsDireccion(persona.getLstDirecciones().get(0).getDsDireccion());
		direccionBean.setIdCountry("PER");
		direccionBean.setIdTipoDireccion("LEGAL");// pendiente
		direccionBean.setIdTipoPropiedad("OWNER");// pendiente

		List<GrupoGeografico> grupoGeograficoLst = new ArrayList<>();
		GrupoGeografico grupoGeografico = new GrupoGeografico();
		grupoGeografico.setId("DEPARTMENT");
		grupoGeografico.setCode(
				persona.getLstDirecciones().get(0).getLstGrupoGeografico().get(0).getCode());
		grupoGeograficoLst.add(grupoGeografico);

		GrupoGeografico grupoGeografico2 = new GrupoGeografico();
		grupoGeografico2.setId("PROVINCE");
		grupoGeografico2.setCode(
				persona.getLstDirecciones().get(0).getLstGrupoGeografico().get(1).getCode());
		grupoGeograficoLst.add(grupoGeografico2);

		GrupoGeografico grupoGeografico3 = new GrupoGeografico();
		grupoGeografico3.setId("DISTRICT");
		grupoGeografico3.setCode(
				persona.getLstDirecciones().get(0).getLstGrupoGeografico().get(2).getCode());
		grupoGeograficoLst.add(grupoGeografico3);

		// <--PENDIENTE RENIEC
		// GrupoGeografico grupoGeografico4= new
		// GrupoGeografico();
		// grupoGeografico4.setId("URBANIZATION");
		// grupoGeografico4.setNombre("Corpac");
		// grupoGeograficoLst.add(grupoGeografico4);
		// PENDIENTE RENIEC -->

		/*
		 * ESTO SE LLENA CON LA DATA DE LA WEB
		 * GrupoGeografico grupoGeografico5= new
		 * GrupoGeografico();
		 * grupoGeografico5.setId("DESCENT");
		 * grupoGeografico5.
		 * setNombre("República de Ecuador");
		 * grupoGeograficoLst.add(grupoGeografico5);
		 * 
		 * GrupoGeografico grupoGeografico6= new
		 * GrupoGeografico();
		 * grupoGeografico6.setId("EXTERIOR_NUMBER");
		 * grupoGeografico6.setNombre("1234");
		 * grupoGeograficoLst.add(grupoGeografico6);
		 */
		//
		direccionBean.setLstGrupoGeografico(grupoGeograficoLst);

		List<DireccionBean> lstDireccionBean = new ArrayList<>();
		lstDireccionBean.add(direccionBean);
		customerEnvio.setLstDireccionBean(lstDireccionBean);
		customerEnvio.setGenero("0");// 0persona.getCodigoSexo()
		customerEnvio.setEstadoCivil(1);// persona.getEstadoCivil();persona.getCodigoEstadoCivil()

		customerEnvio.setDireccion("");//TODO 
		customerEnvio
				.setNombreCompleto((persona.getNombres() != null ? persona.getNombres() : "")
						+ " " + persona.getPaterno() + " " + persona.getMaterno());
		customerEnvio.setTipoRespuesta(1);
		
		return customerEnvio;
	}

	private String obtenerCargo(String idTipoOcupacion, List<CatalogoBean> catalogoOficios2) {
		// TODO Auto-generated method stub
		String cargo = "";
		for (CatalogoBean catalogoBean : catalogoOficios2) {
			if (catalogoBean.getIdCatalogo().equals(idTipoOcupacion)) {
				cargo = catalogoBean.getStValue();
				break;
			}
		}
		return cargo;
	}

	public ClienteBean cargarInforacionCliente(PersonaBean persona) {
		return null;
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
		String uriServicio = prop.getString("sistema.uriservicio");
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

	public ClienteBean completarDatosCliente(ClienteBean clienteBean, DatosReniecBean datosNoClienteReniec) {

		clienteBean.setFechaExpiracionDocumento(datosNoClienteReniec.getFechaCaducidad());
		List<GrupoGeografico> gruposDireccion = obtenerDireccionAmdocsReniec(datosNoClienteReniec.getDireccionAmdocs());
		;
		if (gruposDireccion.size() > 0) {
			for (GrupoGeografico grupoGeografico : gruposDireccion) {
				clienteBean.getLstDireccionBean().get(0).getLstGrupoGeografico().add(grupoGeografico);
			}
		}
		return clienteBean;
	}

	private List<GrupoGeografico> obtenerDireccionAmdocsReniec(String direccionAmdocs) {
		List<GrupoGeografico> listaRespuesta = new ArrayList<>();
		String[] direccionSeparada = direccionAmdocs.split(Pattern.quote("(|)"));

		GrupoGeografico grupoGeografico1 = new GrupoGeografico();
		grupoGeografico1.setId(obtenerTraduccionAmdocs(direccionSeparada[0]));
		grupoGeografico1.setNombre(direccionSeparada[1]);
		listaRespuesta.add(grupoGeografico1);

		String[] numero = direccionSeparada[2].split(Pattern.quote("."));
		GrupoGeografico grupoGeografico = new GrupoGeografico();
		grupoGeografico.setId(obtenerTraduccionAmdocs(numero[0]));
		grupoGeografico.setNombre(numero[1].trim());
		listaRespuesta.add(grupoGeografico);

		GrupoGeografico grupoGeografico2 = new GrupoGeografico();
		grupoGeografico2.setId(obtenerTraduccionAmdocs(direccionSeparada[6]));
		grupoGeografico2.setNombre(direccionSeparada[7]);
		listaRespuesta.add(grupoGeografico2);

		return listaRespuesta;
	}

	private String obtenerTraduccionAmdocs(String string) {
		// TODO MEJORAR(CAMBIAR POR BD O ENUM)
		String traduccion = "";
		if (string.equals("AV")) {
			traduccion = "AVENUE";
		} else if (string.equals("Nro")) {
			traduccion = "EXTERIOR_NUMBER";
		} else if (string.equals("URB")) {
			traduccion = "URBANIZATION";
		}
		return traduccion;
	}
}
