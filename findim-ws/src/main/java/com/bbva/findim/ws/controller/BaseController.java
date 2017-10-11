package com.bbva.findim.ws.controller;

import com.bbva.findim.dom.RutasPdfBean;
import com.bbva.findim.ws.util.PropertyUtil;

public class BaseController {

	public static RutasPdfBean obtenerRutasPDFBean(PropertyUtil prop) {
		RutasPdfBean rutasPdfBean = new RutasPdfBean();
		String configPath = System.getProperty("findim-ws.config.path");
		rutasPdfBean.setRutaContrato1Jasper(configPath+prop.getString("sistema.ruta.contrato1.jasper"));
		rutasPdfBean.setRutaContrato2Jasper(configPath+prop.getString("sistema.ruta.contrato2.jasper"));
		rutasPdfBean.setRutaCronogramaJasper(configPath+prop.getString("sistema.ruta.cronograma.jasper"));
		rutasPdfBean.setRutaGeneracionContrato(prop.getString("sistema.ruta.generacion.contrato"));
		rutasPdfBean.setRutaHRIinformativaDescJasper(configPath+prop.getString("sistema.ruta.hriinformativadesc.jasper"));
		rutasPdfBean.setRutaHRIinformativaJasper(configPath+prop.getString("sistema.ruta.hriinformativa.jasper"));
		rutasPdfBean.setRutaImagenBBVACF(configPath+prop.getString("sistema.ruta.logo.bbvacf"));
		rutasPdfBean.setRutaImagenFirmaRepresentante1(configPath+prop.getString("sistema.ruta.firma.representante1"));
		rutasPdfBean.setRutaImagenFirmaRepresentante2(configPath+prop.getString("sistema.ruta.firma.representante2"));
		rutasPdfBean.setRutaImagenLogoRimac(configPath+prop.getString("sistema.ruta.logo.rimac"));
		rutasPdfBean.setRutaPagareJasper(configPath+prop.getString("sistema.ruta.pagare.jasper"));
		rutasPdfBean.setRutaPrestamoJasper(configPath+prop.getString("sistema.ruta.prestamo.jasper"));
		rutasPdfBean.setRutaSeguroDesgravamenJasper(configPath+prop.getString("sistema.ruta.seguro.desgravamen.jasper"));
		rutasPdfBean.setRutaImagenLogoRimac(configPath+prop.getString("sistema.ruta.logo.rimac"));
		rutasPdfBean.setRutaContratoFijo(configPath+prop.getString("sistema.ruta.contrato.archivoFijo.contratofijo"));
		rutasPdfBean.setRutaPrestamoFijo(configPath+prop.getString("sistema.ruta.contrato.archivoFijo.prestamofijo"));
		rutasPdfBean.setRutaSeguroFijo(configPath+prop.getString("sistema.ruta.contrato.archivoFijo.segurofijo"));
		rutasPdfBean.setRutaConsolidado(prop.getString("sistema.ruta.contrato.consolidado"));
		rutasPdfBean.setRutaFirmados(configPath+prop.getString("sistema.ruta.contrato.firmado"));
		return rutasPdfBean;
	}

}
