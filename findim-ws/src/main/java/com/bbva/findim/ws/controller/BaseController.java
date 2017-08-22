package com.bbva.findim.ws.controller;

import com.bbva.findim.dom.RutasPdfBean;
import com.bbva.findim.ws.util.PropertyUtil;

public class BaseController {

	public static RutasPdfBean obtenerRutasPDFBean(PropertyUtil prop) {
		RutasPdfBean rutasPdfBean = new RutasPdfBean();
		rutasPdfBean.setRutaContrato1Jasper(prop.getString("sistema.ruta.contrato1.jasper"));
		rutasPdfBean.setRutaContrato2Jasper(prop.getString("sistema.ruta.contrato2.jasper"));
		rutasPdfBean.setRutaCronogramaJasper(prop.getString("sistema.ruta.cronograma.jasper"));
		rutasPdfBean.setRutaGeneracionContrato(prop.getString("sistema.ruta.generacion.contrato"));
		rutasPdfBean.setRutaHRIinformativaDescJasper(prop.getString("sistema.ruta.hriinformativadesc.jasper"));
		rutasPdfBean.setRutaHRIinformativaJasper(prop.getString("sistema.ruta.hriinformativa.jasper"));
		rutasPdfBean.setRutaImagenBBVACF(prop.getString("sistema.ruta.logo.bbvacf"));
		rutasPdfBean.setRutaImagenFirmaRepresentante1(prop.getString("sistema.ruta.firma.representante1"));
		rutasPdfBean.setRutaImagenFirmaRepresentante2(prop.getString("sistema.ruta.firma.representante2"));
		rutasPdfBean.setRutaImagenLogoRimac(prop.getString("sistema.ruta.logo.rimac"));
		rutasPdfBean.setRutaPagareJasper(prop.getString("sistema.ruta.pagare.jasper"));
		rutasPdfBean.setRutaPrestamoJasper(prop.getString("sistema.ruta.prestamo.jasper"));
		rutasPdfBean.setRutaSeguroDesgravamenJasper(prop.getString("sistema.ruta.seguro.desgravamen.jasper"));
		rutasPdfBean.setRutaImagenLogoRimac(prop.getString("sistema.ruta.logo.rimac"));
		rutasPdfBean.setRutaContratoFijo(prop.getString("sistema.ruta.contrato.archivoFijo.contratofijo"));
		rutasPdfBean.setRutaPrestamoFijo(prop.getString("sistema.ruta.contrato.archivoFijo.prestamofijo"));
		rutasPdfBean.setRutaSeguroFijo(prop.getString("sistema.ruta.contrato.archivoFijo.segurofijo"));
		rutasPdfBean.setRutaConsolidado(prop.getString("sistema.ruta.contrato.consolidado"));
		rutasPdfBean.setRutaFirmados(prop.getString("sistema.ruta.contrato.firmado"));
		rutasPdfBean.setRutaComprobantePrepagoParcialJasper(prop.getString("sistema.ruta.comprobante.prepago.parcial.jasper"));
		rutasPdfBean.setRutaComprobantePrepagoTotalJasper(prop.getString("sistema.ruta.comprobante.prepago.total.jasper"));
		rutasPdfBean.setRutaArchivosPrepago(prop.getString("sistema.ruta.comprobante.prepago"));
		return rutasPdfBean;
	}

}
