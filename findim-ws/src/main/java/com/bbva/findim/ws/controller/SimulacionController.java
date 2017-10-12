package com.bbva.findim.ws.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.findim.bck.service.CustomerService;
import com.bbva.findim.bck.service.LoanService;
import com.bbva.findim.bck.service.ProposalService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.service.TariffService;
import com.bbva.findim.dom.CabeceraBean;
import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoBean;
import com.bbva.findim.dom.DatosPdfBean;
import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ReCronogramaRest;
import com.bbva.findim.dom.RutasPdfBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.dom.common.ConstantResponseMessage;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.sql.service.EmpresaService;
import com.bbva.findim.sql.service.ParametroService;
import com.bbva.findim.ws.service.ContratoService;
import com.bbva.findim.ws.util.PropertyUtil;
import com.bbva.findim.ws.util.Util;

@Controller
public class SimulacionController {
	     
	@Autowired
	PropertyUtil prop;
	
	@Autowired
	ContratoService contratoService;
	
	@Autowired
	EmpresaService empresaService;
	
	@Autowired 
	LoanService loanService;
	
	@Autowired
	TariffService tariffService;
	
	@Autowired 
	SeguridadBbvaService seguridad;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired 
	ProposalService proposalService;
	
	@Autowired
	ParametroService parametroService;
	
	private static final Logger logger = LogManager.getLogger(SimulacionController.class);
	
	@RequestMapping(value = "/regenerarCronograma", method = RequestMethod.POST,headers="Accept=application/json")
	@ResponseBody
	public ResponseEntity<Integer> regenerarCronograma(@RequestBody  ReCronogramaRest cronogramaIn)throws Exception {
		ClienteBean clienteBean = null;
		try {
			EmpresaBean empresa = empresaService.obtenerEmpresa(prop.getString("empresa.telefonica"));
			List<ParametroBean> lstIndicadoresVia = parametroService.listarParametrosDetalle(64);
			System.out.println("getEmpresa : " + empresa.getNombreEmpresa());
			List<TarifaBean> listaTarifa = new ArrayList<TarifaBean>();
			TarifaBean tarifaBean = tariffService.obtenerTarifa(null, cronogramaIn.getCdTarifa(),seguridad.generarTSec(3), empresa);
			listaTarifa.add(tarifaBean);
			
			SimulacionBean simulacionBean = new SimulacionBean();
			simulacionBean = mapper(cronogramaIn.getMoneda(), new BigDecimal(cronogramaIn.getImporteBien()), tarifaBean, null, new BigDecimal(cronogramaIn.getImporteInicial()), 
											new BigDecimal(cronogramaIn.getImportePrestamo()), cronogramaIn.getTipoEnvio(), 0, cronogramaIn.getFechaNacimiento());

			SimulacionBean cronograma = loanService.simularPrestamo(simulacionBean, seguridad.generarTSec(3));
			cronograma.setListaTarifa(listaTarifa);
			
			clienteBean  = customerService.obtenerDatosCliente(seguridad.generarTSec(3), cronogramaIn.getTipoDocumento(), cronogramaIn.getNroDocumento(), lstIndicadoresVia);
			clienteBean.setNumeroDocumento(clienteBean.getLstDocumentoIdentidadBean().get(0).getNroDocumento());
			clienteBean.setTipoDocumento(clienteBean.getLstDocumentoIdentidadBean().get(0).getTipoDocumentoIdentidad());
			clienteBean.setSimulacionBean(cronograma);
			clienteBean.setIdContrato(cronogramaIn.getCdPropuesta());
			
			//obtener el cronograma
			ContratoBean contrato = proposalService.obtenerPropuesta(seguridad.generarTSec(3), empresa.getIndenticador(), empresa.getCdEmpresa(), cronogramaIn.getTipoDocumento(), cronogramaIn.getNroDocumento(), empresa.getFechaExpiracion(), cronogramaIn.getCdPropuesta());
			clienteBean.setListaContrato(new ArrayList<ContratoBean>());
			clienteBean.getListaContrato().add(contrato);
			
			//datos de generacion de documentos
			RutasPdfBean rutasPdfBean = new RutasPdfBean();
			String configPath = System.getProperty("findim-ws.config.path");
			rutasPdfBean.setRutaContrato1Jasper(configPath+prop.getString("sistema.ruta.contrato1.jasper").trim());
			rutasPdfBean.setRutaContrato2Jasper(configPath+prop.getString("sistema.ruta.contrato2.jasper").trim());
			rutasPdfBean.setRutaCronogramaJasper(configPath+prop.getString("sistema.ruta.cronograma.jasper").trim());
			rutasPdfBean.setRutaGeneracionContrato(configPath+prop.getString("sistema.ruta.generacion.contrato").trim());
			rutasPdfBean.setRutaHRIinformativaDescJasper(configPath+prop.getString("sistema.ruta.hriinformativadesc.jasper").trim());
			rutasPdfBean.setRutaHRIinformativaJasper(configPath+prop.getString("sistema.ruta.hriinformativa.jasper").trim());
			rutasPdfBean.setRutaImagenBBVACF(configPath+prop.getString("sistema.ruta.logo.bbvacf").trim());
			rutasPdfBean.setRutaImagenFirmaRepresentante1(configPath+prop.getString("sistema.ruta.firma.representante1").trim());
			rutasPdfBean.setRutaImagenFirmaRepresentante2(configPath+prop.getString("sistema.ruta.firma.representante2").trim());
			rutasPdfBean.setRutaImagenLogoRimac(configPath+prop.getString("sistema.ruta.logo.rimac").trim());
			rutasPdfBean.setRutaPagareJasper(configPath+prop.getString("sistema.ruta.pagare.jasper").trim());
			rutasPdfBean.setRutaAceptacionJasper(configPath+prop.getString("sistema.ruta.aceptacion.jasper").trim());
			rutasPdfBean.setRutaPrestamoJasper(configPath+prop.getString("sistema.ruta.prestamo.jasper"));
			rutasPdfBean.setRutaSeguroDesgravamenJasper(configPath+prop.getString("sistema.ruta.seguro.desgravamen.jasper").trim());			
			rutasPdfBean.setRutaImagenLogoRimac(configPath+prop.getString("sistema.ruta.logo.rimac").trim());
			rutasPdfBean.setRutaContratoFijo(configPath+prop.getString("sistema.ruta.contrato.archivoFijo.contratofijo").trim());
			rutasPdfBean.setRutaPrestamoFijo(configPath+prop.getString("sistema.ruta.contrato.archivoFijo.prestamofijo").trim());
			rutasPdfBean.setRutaSeguroFijo(configPath+prop.getString("sistema.ruta.contrato.archivoFijo.segurofijo").trim());
			rutasPdfBean.setRutaConsolidado(prop.getString("sistema.ruta.contrato.consolidado").trim());
			
			DatosPdfBean datosPdfBean = new DatosPdfBean();
			datosPdfBean.setRutasPdfBean(rutasPdfBean);
			clienteBean.setDatosPdf(datosPdfBean);		

			Util.deleteFilesByPrefix(rutasPdfBean.getRutaGeneracionContrato(),Constantes.PREFIJO_TEMPORAL);
			
			generarPDF(clienteBean);
			Integer response = clienteBean.getTipoRespuesta();
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.info("Sucedio un  Error en la generaación de pfd ", e);
			Integer response = clienteBean.getTipoRespuesta();
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	
	public ClienteBean generarPDF(ClienteBean clienteBean) {
		try {
			clienteBean = contratoService.generarPDF(clienteBean, false);
			clienteBean.setTipoRespuesta(ConstantResponseMessage.TipoRespuestaWS.Satisfactorio.getTipoRespuestaWS());
			logger.debug("Envio de setTipoRespuesta:" + ConstantResponseMessage.TipoRespuestaWS.Satisfactorio.getTipoRespuestaWS());
			logger.debug("clienteBean:" + clienteBean);
		} catch (Exception e) {
			clienteBean.setTipoRespuesta(ConstantResponseMessage.TipoRespuestaWS.Error.getTipoRespuestaWS());
			clienteBean.setTipoError(ConstantResponseMessage.TipoError.HttpException.getError());
			logger.error("envio del error: ", e);
		}
		logger.debug("respuest del ClienteBean" + clienteBean);
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

		if (metodoEnvio.equals("D")) {// VIRTUAL
			simulacion.getCabecera().setMetodoEnvio(0);
		} else if (metodoEnvio.equals("F")) {// FISICO
			simulacion.getCabecera().setMetodoEnvio(1);
		} else if (metodoEnvio.equals("A")) {// AMBOS
			simulacion.getCabecera().setMetodoEnvio(1);
		}else {//NONE
			simulacion.getCabecera().setMetodoEnvio(0);
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
