package com.bbva.findim.ws.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.DatosPdfBean;
import com.bbva.findim.dom.FirmaContratoBean;
import com.bbva.findim.dom.RutasPdfBean;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.ws.service.ContratoService;
import com.bbva.findim.ws.util.ConvertUtil;
import com.bbva.findim.ws.util.Enumeradores.EnumRespuestaSignBox;
import com.bbva.findim.ws.util.PropertyUtil;
import com.bbva.findim.ws.util.Util;
import com.google.gson.Gson;

@Controller
public class ContratoController {

	private static final Logger LOGGER = LogManager.getLogger(ContratoController.class);

	@Autowired
	private ContratoService contratoService;
	
//	@Autowired
	//private AltasService altasService;

	@Autowired
	private PropertyUtil prop;
	

	@RequestMapping(value = "/generarPDF", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ClienteBean generarPDF(@RequestBody ClienteBean clienteBean) {
		LOGGER.info("Inicio-[InicioController - guardarDatosComplementarios]");
		try {

			RutasPdfBean rutasPdfBean = new RutasPdfBean();
			rutasPdfBean.setRutaContrato1Jasper(prop.getString("sistema.ruta.contrato1.jasper").trim());
			rutasPdfBean.setRutaContrato2Jasper(prop.getString("sistema.ruta.contrato2.jasper").trim());
			rutasPdfBean.setRutaCronogramaJasper(prop.getString("sistema.ruta.cronograma.jasper").trim());
			rutasPdfBean.setRutaGeneracionContrato(prop.getString("sistema.ruta.generacion.contrato").trim());
			rutasPdfBean.setRutaHRIinformativaDescJasper(prop.getString("sistema.ruta.hriinformativadesc.jasper").trim());
			rutasPdfBean.setRutaHRIinformativaJasper(prop.getString("sistema.ruta.hriinformativa.jasper").trim());
			rutasPdfBean.setRutaImagenBBVACF(prop.getString("sistema.ruta.logo.bbvacf").trim());
			rutasPdfBean.setRutaImagenFirmaRepresentante1(prop.getString("sistema.ruta.firma.representante1").trim());
			rutasPdfBean.setRutaImagenFirmaRepresentante2(prop.getString("sistema.ruta.firma.representante2").trim());
			rutasPdfBean.setRutaImagenLogoRimac(prop.getString("sistema.ruta.logo.rimac").trim());
			rutasPdfBean.setRutaPagareJasper(prop.getString("sistema.ruta.pagare.jasper").trim());
			rutasPdfBean.setRutaAceptacionJasper(prop.getString("sistema.ruta.aceptacion.jasper").trim());
			rutasPdfBean.setRutaPrestamoJasper(prop.getString("sistema.ruta.prestamo.jasper"));
			rutasPdfBean.setRutaSeguroDesgravamenJasper(prop.getString("sistema.ruta.seguro.desgravamen.jasper").trim());			
			rutasPdfBean.setRutaImagenLogoRimac(prop.getString("sistema.ruta.logo.rimac").trim());
			rutasPdfBean.setRutaContratoFijo(prop.getString("sistema.ruta.contrato.archivoFijo.contratofijo").trim());
			rutasPdfBean.setRutaPrestamoFijo(prop.getString("sistema.ruta.contrato.archivoFijo.prestamofijo").trim());
			rutasPdfBean.setRutaSeguroFijo(prop.getString("sistema.ruta.contrato.archivoFijo.segurofijo").trim());
			rutasPdfBean.setRutaConsolidado(prop.getString("sistema.ruta.contrato.consolidado").trim());
			
			DatosPdfBean datosPdfBean = new DatosPdfBean();
			datosPdfBean.setRutasPdfBean(rutasPdfBean);
			clienteBean.setDatosPdf(datosPdfBean);		
			clienteBean.setTceaWsdl(prop.getString("calculo.tcea.wsdl.location"));
			clienteBean.setTceaTimeout(ConvertUtil.convertToLong(prop.getString("calculo.tcea.timeout.millis")));

			Util.deleteFilesByPrefix(rutasPdfBean.getRutaGeneracionContrato(),Constantes.PREFIJO_TEMPORAL);
			
			clienteBean = contratoService.generarPDF(clienteBean);
		}catch(ParseException e){
			clienteBean = null;
			LOGGER.error("Error-[guardarDatosComplementarios]-", e); 
		}
		catch(Exception e){
			clienteBean = null;
			LOGGER.error("Error-[guardarDatosComplementarios]-", e); 
		}	
		return clienteBean;
	}



	@RequestMapping(value = "firmarContrato/{tipoDocumento}/{numeroDocumento}/{codigoContrato}/{idCliente}/{seguridad_usertype}/{seguridad_user}", method = RequestMethod.GET)
	@ResponseBody
	public ClienteBean firmarContrato(
			//@PathVariable("codigoAuxiliar") String codigoAuxiliar,
			@PathVariable("tipoDocumento") String tipoDocumento,
			@PathVariable("numeroDocumento") String numeroDocumento,
			@PathVariable("codigoContrato") String codigoContrato,
			@PathVariable("idCliente") Integer idCliente,
			@PathVariable("seguridad_usertype") String seguridad_usertype,
			@PathVariable("seguridad_user") String seguridad_user			
			) {

		LOGGER.info("> tipoDocumento: " + tipoDocumento);
		LOGGER.info("> numeroDocumento: " + numeroDocumento);
		//LOGGER.info("> codigoAuxiliar: " + codigoAuxiliar);
		LOGGER.info("> codigoContrato: " + codigoContrato);
		LOGGER.info("> idCliente: " + idCliente);

		FirmaContratoBean bean = new FirmaContratoBean();
		
		ClienteBean clienteBean = new ClienteBean();
		try{
			//bean.setCodigoAuxiliar(codigoAuxiliar);
			bean.setNumeroDocumento(numeroDocumento);
			bean.setTipoDocumento(tipoDocumento);
			bean.setCodigoContrato(codigoContrato);
			bean.setCodigoCliente(idCliente);

			bean.setSeguridad_usertype(seguridad_usertype);  		
			
			bean.setRutaSignBoxContract(prop.getString("firma.contrato.service.location"));
			bean.setRutaOrigen(prop.getString("sistema.ruta.generacion.contrato"));
			bean.setRutaSalida(prop.getString("sistema.ruta.contrato.firmado"));
			//INI HENRY 31/01/2017	
			bean.setRutaConsolidado(prop.getString("sistema.ruta.contrato.consolidado"));
			//FIN HENRY 31/01/2017	
			bean.setSeguridad_user(seguridad_user);

			int rptaFirmaContrato = contratoService.firmarContrato(bean);
			clienteBean.setResultadoFirma(rptaFirmaContrato);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			clienteBean.setResultadoFirma(EnumRespuestaSignBox.ERROR.getCodigo());
			return clienteBean;
		}	
		return clienteBean;

	}
}