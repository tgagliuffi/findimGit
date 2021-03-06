package com.bbva.findim.ws.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.DetalleBean;
import com.bbva.findim.dom.RutasPdfBean;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.ws.service.PdfAsyncService;
import com.bbva.findim.ws.util.Util;
import com.bbva.findim.ws.util.UtilPersistencia;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class PdfAsyncServiceImpl implements PdfAsyncService{
	static Logger logger = LogManager.getLogger(PdfAsyncServiceImpl.class);
	
	@Override
	public Boolean generarDocumentoHRI(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,String codigoAuxContrato,Boolean firmado) {
		logger.info("Inicio generarDocumentoHRI");
		Boolean result=false;
		try{
			JasperReport reportHRIinformativa = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaHRIinformativaJasper());
			JasperReport reportHRIinformativaDesc = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaHRIinformativaDescJasper());	
			JasperPrint printHRIinformativa = JasperFillManager.fillReport(reportHRIinformativa, parametros,new JREmptyDataSource());
			JasperPrint printHRIinformativaDesc = JasperFillManager.fillReport(reportHRIinformativaDesc, parametros,new JREmptyDataSource());
			
			List<JasperPrint> jprintlist = new ArrayList<>();
	        jprintlist.add(printHRIinformativa);
	        jprintlist.add(printHRIinformativaDesc);
	        String ruta;
	        ruta=(firmado?rutasPdfBean.getRutaFirmados():rutasPdfBean.getRutaGeneracionContrato()) + Constantes.DOCUMENTO_HRI +codigoAuxContrato + Constantes.EXTENSION_PDF;
	        UtilPersistencia.exportJaspersToPDF(jprintlist,ruta);
	        result=true;
		}catch(Exception e){
			result=false;
			logger.error("error al generar el documento HRI PDF" + e);
		}
		logger.info("Fin generarDocumentoHRI");
		return result;
	}
	
	
	/**************************************************************************/
	@Override
	public Future<Boolean> generarDocumentoHRIgenerico(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,String rutaDestino) {
		logger.info("Inicio generarDocumentoHRIgenerico");
		Boolean result=false;
		try{
			JasperReport reportHRIinformativa = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaHRIinformativaJasper());
			JasperReport reportHRIinformativaDesc = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaHRIinformativaDescJasper());	
			JasperPrint printHRIinformativa = JasperFillManager.fillReport(reportHRIinformativa, parametros,new JREmptyDataSource());
			JasperPrint printHRIinformativaDesc = JasperFillManager.fillReport(reportHRIinformativaDesc, parametros,new JREmptyDataSource());
			
			List<JasperPrint> jprintlist = new ArrayList<>();
	        jprintlist.add(printHRIinformativa);
	        jprintlist.add(printHRIinformativaDesc);
	        UtilPersistencia.exportJaspersToPDF(jprintlist,rutaDestino);
	        result=true;
		}catch(Exception e){
			result=false;
			logger.error("error al generar el documento HRI PDF" + e);
		}
		logger.info("Fin generarDocumentoHRI");
		return new AsyncResult<>(result);
	}
	
	@Override
	
	public Future<Boolean> generarDocumentoCROgenerico(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,List<DetalleBean> listaCuota,String rutaDestino) {
		logger.info("Inicio generarDocumentoCROgenerico");
		Boolean result=false;
		try{
			JasperReport reportCronograma = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaCronogramaJasper());
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(listaCuota);
			JasperPrint printCronograma = JasperFillManager.fillReport(reportCronograma, parametros, jrDataSource);			 
			JasperExportManager.exportReportToPdfFile(printCronograma,rutaDestino);
			result=true;
		}catch(Exception e){
			logger.error("error al generar el documento CRONOGRAMA PDF" + e);
			result=false;
		}
		logger.info("Fin generarDocumentoCRO");
		return new AsyncResult<>(result);
	}
    /**********************************************************************/
	@Override
	public Boolean generarDocumentoCON(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,String codigoAuxContrato) {
		logger.info("Inicio generarDocumentoCON");
		Boolean result=false;
		try{
			JasperReport reportContrato = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaContrato1Jasper());
			JasperReport reportContratoDesc = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaContrato2Jasper());
			JasperPrint printContrato = JasperFillManager.fillReport(reportContrato, parametros, new JREmptyDataSource());
			JasperPrint printContratoDesc = JasperFillManager.fillReport(reportContratoDesc, parametros, new JREmptyDataSource());
			
			List<JasperPrint> jprintlistCON = new ArrayList<>();
			jprintlistCON.add(printContrato);
		    String rutaCON;
		    rutaCON=rutasPdfBean.getRutaGeneracionContrato() + Constantes.PREFIJO_TEMPORAL+Constantes.DOCUMENTO_CON +codigoAuxContrato + Constantes.EXTENSION_PDF;
		    UtilPersistencia.exportJaspersToPDF(jprintlistCON,rutaCON);
			
			List<JasperPrint> jprintlistCONDET = new ArrayList<>();
			jprintlistCONDET.add(printContratoDesc);
		    String rutaCONDET;
		    rutaCONDET=rutasPdfBean.getRutaGeneracionContrato() + Constantes.PREFIJO_TEMPORAL+Constantes.DOCUMENTO_CONDET +codigoAuxContrato + Constantes.EXTENSION_PDF;
			UtilPersistencia.exportJaspersToPDF(jprintlistCONDET,rutaCONDET);
			
			//Contrato
			List<InputStream> pdfsCON = new ArrayList<>();
			pdfsCON.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.PREFIJO_TEMPORAL+Constantes.DOCUMENTO_CON + codigoAuxContrato + Constantes.EXTENSION_PDF));
			pdfsCON.add(new FileInputStream(rutasPdfBean.getRutaContratoFijo()));
			pdfsCON.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.PREFIJO_TEMPORAL+Constantes.DOCUMENTO_CONDET + codigoAuxContrato + Constantes.EXTENSION_PDF));
			
			String nombreContrato = Constantes.DOCUMENTO_CON + codigoAuxContrato + Constantes.EXTENSION_PDF;
			OutputStream outputCON = new FileOutputStream(rutasPdfBean.getRutaGeneracionContrato() + nombreContrato);
			Util.concatenaPDF(pdfsCON, outputCON, true);
			result=true;
		}catch(Exception e){
			result=false;
			logger.error("error al generar el documento CONTRATO PDF" + e);
		}
		logger.info("Fin generarDocumentoCON");
		return result;
	}

	@Override
	public Boolean generarDocumentoPRE(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,String codigoAuxContrato) {
		logger.info("Inicio generarDocumentoPRE");
		Boolean result=false;
		try{
			JasperReport reportPrestamo = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaPrestamoJasper());
			JasperPrint printPrestamo = JasperFillManager.fillReport(reportPrestamo, parametros, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(printPrestamo, rutasPdfBean.getRutaGeneracionContrato() + Constantes.PREFIJO_TEMPORAL+Constantes.DOCUMENTO_SOL + codigoAuxContrato + Constantes.EXTENSION_PDF);			
			//Prestamo
			List<InputStream> pdfsSOL = new ArrayList<>();
			pdfsSOL.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.PREFIJO_TEMPORAL+Constantes.DOCUMENTO_SOL +codigoAuxContrato + Constantes.EXTENSION_PDF));
			pdfsSOL.add(new FileInputStream(rutasPdfBean.getRutaPrestamoFijo()));
			String nombreSolPrest = Constantes.DOCUMENTO_SOL + codigoAuxContrato + Constantes.EXTENSION_PDF;
			OutputStream outputSOL = new FileOutputStream(rutasPdfBean.getRutaGeneracionContrato() + nombreSolPrest);
			Util.concatenaPDF(pdfsSOL, outputSOL, true);
			result=true;
		}catch(JRException e){
			logger.error("error al generar el documento PRÉSTAMO PDF" + e);
			result=false;
		}catch(Exception e){
			logger.error("error al generar el documento PRÉSTAMO PDF", e);
			result=false;
		}
		logger.info("Fin generarDocumentoPRE");
		return result;
	}

	
	@Override
	public Boolean generarDocumentoCRO(Map<String, Object> parametros, RutasPdfBean rutasPdfBean, String codigoAuxContrato,List<DetalleBean> listaCuota,Boolean firmado) {
		logger.info("Inicio generarDocumentoCRO");
		Boolean result=false;
		String ruta=null;
		try{
			JasperReport reportCronograma = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaCronogramaJasper());
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(listaCuota);
			JasperPrint printCronograma = JasperFillManager.fillReport(reportCronograma, parametros, jrDataSource);	
			ruta=(firmado?rutasPdfBean.getRutaFirmados():rutasPdfBean.getRutaGeneracionContrato()) + Constantes.DOCUMENTO_CRO +codigoAuxContrato + Constantes.EXTENSION_PDF;			 
			JasperExportManager.exportReportToPdfFile(printCronograma, ruta);
			result=true;
		}catch(Exception e){
			logger.error("error al generar el documento CRONOGRAMA PDF", e);
			result=false;
		}
		logger.info("Fin generarDocumentoCRO");
		return result;
	}

	@Override
	public Boolean generarDocumentoPAG(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,String codigoAuxContrato) {
		logger.info("Inicio generarDocumentoPAG");
		Boolean result=false;
		try{
			JasperReport reportPagare = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaPagareJasper());	
			JasperPrint printPagare = JasperFillManager.fillReport(reportPagare, parametros, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(printPagare, rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_PAG + codigoAuxContrato + Constantes.EXTENSION_PDF);
			result=true;
		}catch(Exception e){
			logger.error("error al generar el documento PAGARÉ PDF", e);
			result=false;
		}
		logger.info("Fin generarDocumentoPAG");
		return result;
	}

	@Override
	public Boolean generarDocumentoSEG(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,String codigoAuxContrato) {
		logger.info("Inicio generarDocumentoSEG");
		Boolean result=false;
		try{
			JasperReport reportSeguro = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaSeguroDesgravamenJasper());
			JasperPrint printSeguro = JasperFillManager.fillReport(reportSeguro, parametros, new JREmptyDataSource());	
			JasperExportManager.exportReportToPdfFile(printSeguro, rutasPdfBean.getRutaGeneracionContrato() + Constantes.PREFIJO_TEMPORAL+Constantes.DOCUMENTO_SDE + codigoAuxContrato + Constantes.EXTENSION_PDF); 			
			//Seguro
			List<InputStream> pdfsSDE = new ArrayList<>();
			pdfsSDE.add(new FileInputStream(rutasPdfBean.getRutaSeguroFijo()));
			pdfsSDE.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.PREFIJO_TEMPORAL+Constantes.DOCUMENTO_SDE + codigoAuxContrato + Constantes.EXTENSION_PDF));
			String nombreSeguro = Constantes.DOCUMENTO_SDE + codigoAuxContrato + Constantes.EXTENSION_PDF;
			OutputStream outputSDE = new FileOutputStream(rutasPdfBean.getRutaGeneracionContrato() + nombreSeguro);
			Util.concatenaPDF(pdfsSDE, outputSDE, true);
			result = true;
		} catch (Exception e) {
			logger.error("error al generar el documento SEGURO PDF", e);
			result = false;
		}
		logger.info("Fin generarDocumentoSEG");
		return result;
	}

	@Override
	public byte[] generarCRO(Map<String, Object> parametros, RutasPdfBean rutasPdfBean, List<DetalleBean> listaCuota) {
		try {
			JasperReport reportCronograma = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaCronogramaJasper());
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(listaCuota);
			JasperPrint printCronograma = JasperFillManager.fillReport(reportCronograma, parametros, jrDataSource);
			return JasperExportManager.exportReportToPdf(printCronograma);
		} catch (Exception e) {
			logger.error("error al generar el documento CRONOGRAMA PDF", e);
			return null;
		}
	}


	@Override
	public Boolean generarDocumentoACE(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,
			String codigoContrato) {
		logger.info("Inicio generarDocumentoACE");
		Boolean result=false;
		try{
			JasperReport reportAceptacion = (JasperReport) JRLoader.loadObjectFromFile(rutasPdfBean.getRutaAceptacionJasper());	
			JasperPrint printAceptacion = JasperFillManager.fillReport(reportAceptacion, parametros, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(printAceptacion, rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_ACE + codigoContrato + Constantes.EXTENSION_PDF);
			result=true;
		}catch(Exception e){
			logger.error("error al generar el documento PAGARÉ PDF", e);
			result=false;
		}
		logger.info("Fin generarDocumentoPAG");
		return result;
	}



}
