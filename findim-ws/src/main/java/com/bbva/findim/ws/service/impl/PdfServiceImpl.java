package com.bbva.findim.ws.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoBean;
import com.bbva.findim.dom.DatosPdfBean;
import com.bbva.findim.dom.DetalleBean;
import com.bbva.findim.dom.FileUnicoMetadata;
import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.RutasPdfBean;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.sql.dao.ParametroDao;
import com.bbva.findim.ws.service.PdfAsyncService;
import com.bbva.findim.ws.service.PdfService;
import com.bbva.findim.ws.util.NumerosLetras;
import com.bbva.findim.ws.util.ServiceException;
import com.bbva.findim.ws.util.Util;

import net.sf.jasperreports.engine.JRParameter;

@EnableAsync
@Service
public class PdfServiceImpl implements PdfService{
	static Logger logger = LogManager.getLogger(PdfServiceImpl.class);
		
	@Autowired
	private ParametroDao parametroDao;
		
	@Autowired
	private PdfAsyncService pdfAsyncService;
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class, Exception.class })
	public String generacionPDF(ClienteBean clienteBean, ContratoBean contratoBean, Boolean esRegeneracion) {
		DatosPdfBean datosPdfBeanAux=new  DatosPdfBean();
		
		String nombreGenerado=null;
		Map<String, Object> parametros = null;
		try {			
			ParametroBean parametroRuc = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_12,Constantes.CD_DETALLE_1);
			ParametroBean parametroDomicilio = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_12,Constantes.CD_DETALLE_2);
			ParametroBean parametroNomRep1 = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_13,Constantes.CD_DETALLE_1);
			ParametroBean parametroDNIRep1 = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_13,Constantes.CD_DETALLE_2);
			ParametroBean parametroPoderRep1 = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_13,Constantes.CD_DETALLE_3);
			ParametroBean parametroNomRep2 = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_13,Constantes.CD_DETALLE_4);
			ParametroBean parametroDNIRep2 = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_13,Constantes.CD_DETALLE_5);
			ParametroBean parametroPoderRep2 = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_13,Constantes.CD_DETALLE_6);
			ParametroBean parametroNomComp = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_14,Constantes.CD_DETALLE_1);
			ParametroBean parametroNumPoliza = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_14,Constantes.CD_DETALLE_2);
			ParametroBean parametroTipoSimboloMoneda = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_4,Constantes.CD_DETALLE_1);
			ParametroBean parametroTipoPrestamo = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_22,Constantes.CD_DETALLE_1);			
			ParametroBean parametroPenalidad = parametroDao.obtenerParametroDetallePorIdParamDetalle(Constantes.CD_CABECERA_25,Constantes.CD_DETALLE_1);
			
			
			
//			Optional<GrupoGeografico> resultDistrito = clienteBean.getLstGrupoGeografico().stream().filter(x->x.getId().equals(Constantes.UBIGEO_DISTRITO)).findFirst();
//			Optional<GrupoGeografico> resultProvincia = clienteBean.getLstGrupoGeografico().stream().filter(x->x.getId().equals(Constantes.UBIGEO_PROVINCIA)).findFirst();
//			Optional<GrupoGeografico> resultDepartamento = clienteBean.getLstGrupoGeografico().stream().filter(x->x.getId().equals(Constantes.UBIGEO_DEPARTAMENTO)).findFirst();
//			
//			String distrito = (resultDistrito.isPresent()?resultDistrito.get().getNombreType():"");
//			String provincia = (resultProvincia.isPresent()?resultProvincia.get().getNombreType():"");
//			String departamento = (resultDepartamento.isPresent()?resultDepartamento.get().getNombreType():"");

			String distrito = "";
			String provincia = "";
			String departamento = "";
			
			String fechaFinalContrato = clienteBean.getSimulacionBean().getDetalle().get(clienteBean.getSimulacionBean().getDetalle().size()-1).getFechaVencimiento();
			Date fechaActual = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaActual);
						
			 datosPdfBeanAux.setCodigoContrato(clienteBean.getIdContrato());
			 datosPdfBeanAux.setCodigoCliente(clienteBean.getIdCliente());
			 datosPdfBeanAux.setDomicilio(parametroDomicilio.getNb_valorparamdeta());
			 datosPdfBeanAux.setRUC(parametroRuc.getNb_valorparamdeta());
			 datosPdfBeanAux.setNombreRepresentante1(parametroNomRep1.getNb_valorparamdeta());
			 datosPdfBeanAux.setDniRepresentante1(parametroDNIRep1.getNb_valorparamdeta());
			 datosPdfBeanAux.setPoderRepresentante1(parametroPoderRep1.getNb_valorparamdeta());
			 datosPdfBeanAux.setNombreRepresentante2(parametroNomRep2.getNb_valorparamdeta());
			 datosPdfBeanAux.setDniRepresentante2(parametroDNIRep2.getNb_valorparamdeta());
			 datosPdfBeanAux.setSimboloMonedaPdf(parametroTipoSimboloMoneda.getNb_valorparamdeta());
			 datosPdfBeanAux.setNombreCompleto(clienteBean.getNombreCompleto());			 
			 datosPdfBeanAux.setPoderRepresentante2(parametroPoderRep2.getNb_valorparamdeta());
			 datosPdfBeanAux.setCorreoCliente(clienteBean.getCorreoCliente()!=null?clienteBean.getCorreoCliente():contratoBean.getCorreo());
			 datosPdfBeanAux.setEstadoCivil(clienteBean.getEstadoCivil()+"");
			 datosPdfBeanAux.setTipoDocumento(clienteBean.getTipoDocumento());
			 datosPdfBeanAux.setNumeroDocumentoPdf(clienteBean.getNumeroDocumento());
			 datosPdfBeanAux.setDireccion(clienteBean.getDireccionCliente().getUbicacion().getDsDireccionCompleta());
			 datosPdfBeanAux.setDistrito(distrito);
			 datosPdfBeanAux.setProvincia(provincia);
			 datosPdfBeanAux.setDepartamento(departamento);
			 datosPdfBeanAux.setNumeroCuotas(contratoBean.getIntNumeroCuota());
			 datosPdfBeanAux.setFechaActual(Util.convertDateToString(fechaActual,Constantes.FORMATO_FECHA_SIMPLE));
			 datosPdfBeanAux.setPrecioEquipo(Double.parseDouble(contratoBean.getImporteBien()));
			 datosPdfBeanAux.setCuotaInicial(Double.parseDouble(contratoBean.getImporteInicial()));
			 datosPdfBeanAux.setImportePrestamo(Double.parseDouble(contratoBean.getImportePrestamo()));
			 datosPdfBeanAux.setFechaPrimerVencimiento(Util.changeFormatDate(Constantes.FORMATO_FECHA_YYYY_MM_DD,Constantes.FORMATO_FECHA_DD_MM_YYYY,clienteBean.getSimulacionBean().getDetalle().get(0).getFechaVencimiento()));
			 datosPdfBeanAux.setPrimerNombre(clienteBean.getPrimerNombre());
			 datosPdfBeanAux.setApellidoPaterno(clienteBean.getApellidoPaterno());
			 datosPdfBeanAux.setApellidoMaterno(clienteBean.getApellidoMaterno());
			 datosPdfBeanAux.setFechaNacimiento(Util.changeFormatDate(Constantes.FORMATO_FECHA_YYYY_MM_DD,Constantes.FORMATO_FECHA_DD_MM_YYYY,clienteBean.getFechaNacimiento()));
			 datosPdfBeanAux.setProfesionPdf(clienteBean.getCargo());
			 datosPdfBeanAux.setCodigoSexo(clienteBean.getGenero());
			 datosPdfBeanAux.setCodigoEstadoCivil(contratoBean.getCodigoEstado());
			 datosPdfBeanAux.setTipoMonedaPdf(parametroTipoSimboloMoneda.getNb_paramdetalle());
			 datosPdfBeanAux.setTipoPrestamoPdf(parametroTipoPrestamo.getNb_paramdetalle());
			 datosPdfBeanAux.setTipoSeguroPdf(contratoBean.getDescripcionTasaSeguro());
			 datosPdfBeanAux.setFechaDesembolsoPdf(contratoBean.getFechaDesembolso());
			 datosPdfBeanAux.setTea(clienteBean.getDatosPdf().getTea());
			 datosPdfBeanAux.setTcea(clienteBean.getDatosPdf().getTcea());
			 datosPdfBeanAux.setAuxCodigoContratoPdf(contratoBean.getCodigoAuxiliar());
			 datosPdfBeanAux.setNombreCompania(parametroNomComp.getNb_valorparamdeta());
			 datosPdfBeanAux.setRutasPdfBean(clienteBean.getDatosPdf().getRutasPdfBean());
			 datosPdfBeanAux.setListaCuota(clienteBean.getDatosPdf().getListaCuota());
			 datosPdfBeanAux.setFechaReDesembolso(Util.changeFormatDate(Constantes.FORMATO_FECHA_YYYY_MM_DD,Constantes.FORMATO_FECHA_DD_MM_YYYY,contratoBean.getFechaDesembolso()));
			 datosPdfBeanAux.setFechaVenta(Util.changeFormatDate(Constantes.FORMATO_FECHA_YYYY_MM_DD,Constantes.FORMATO_FECHA_DD_MM_YYYY,contratoBean.getFechaCreacion()));
			 datosPdfBeanAux.setFechaFinalContrato(Util.changeFormatDate(Constantes.FORMATO_FECHA_YYYY_MM_DD,Constantes.FORMATO_FECHA_DD_MM_YYYY,fechaFinalContrato));
			 datosPdfBeanAux.setTotalInteresCompensatorio(Double.parseDouble(clienteBean.getSimulacionBean().getTotales().getTotalIntereses()));
			 datosPdfBeanAux.setImporteSeguro(contratoBean.getImporteSeguroDesgravamen());
			 datosPdfBeanAux.setMontoTotalCuota(contratoBean.getImporteCuota());
			 datosPdfBeanAux.setMontoTotalPagar(contratoBean.getImporteTotal());
			 datosPdfBeanAux.setDiasPago(clienteBean.getSimulacionBean().getCabecera().getDiaPago());
			 datosPdfBeanAux.setNumeroPoliza(parametroNumPoliza.getNb_valorparamdeta());
			 datosPdfBeanAux.setPenalidad(Double.parseDouble(parametroPenalidad.getNb_valorparamdeta()));
			 datosPdfBeanAux.setCostoEnvio(Double.parseDouble(clienteBean.getSimulacionBean().getTotales().getComisionEnvio()));
			 datosPdfBeanAux.setDia(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
			 datosPdfBeanAux.setMes(Util.obtenerNombreMes(Calendar.MONTH));
			 datosPdfBeanAux.setAnio(String.valueOf(cal.get(Calendar.YEAR)));
			 datosPdfBeanAux.setTotalMontoAmortizacion(Double.parseDouble(clienteBean.getSimulacionBean().getTotales().getTotalAmortizacion()!=null?clienteBean.getSimulacionBean().getTotales().getTotalAmortizacion():"0.0"));
			 datosPdfBeanAux.setTotalMontoInteresDevengado(Double.parseDouble(clienteBean.getSimulacionBean().getTotales().getTotalIntereses()!=null?clienteBean.getSimulacionBean().getTotales().getTotalIntereses():"0.0"));
			 datosPdfBeanAux.setTotalMontoSeguroDesgravamen(Double.parseDouble(clienteBean.getSimulacionBean().getTotales().getTotalSeguroDesgravamen()!=null?clienteBean.getSimulacionBean().getTotales().getTotalSeguroDesgravamen():"0.0"));
			 datosPdfBeanAux.setTotalMontoGastosComisiones(Double.parseDouble(clienteBean.getSimulacionBean().getTotales().getComisionEnvio()!=null?clienteBean.getSimulacionBean().getTotales().getComisionEnvio():"0.0"));
			 datosPdfBeanAux.setTotalMontoCuotas(Double.parseDouble(clienteBean.getSimulacionBean().getTotales().getTotalCuotaTotal()!=null?clienteBean.getSimulacionBean().getTotales().getTotalCuotaTotal():"0.0"));
			datosPdfBeanAux.setRutasPdfBean(clienteBean.getDatosPdf().getRutasPdfBean());
			datosPdfBeanAux.setListaCuota(clienteBean.getDatosPdf().getListaCuota());
			datosPdfBeanAux.setTcea(clienteBean.getDatosPdf().getTcea());
			
			parametros=obtenerParametrosPDF(datosPdfBeanAux);			
			parametros.put("strcorreo", clienteBean.getCorreoCliente());
			parametros.put("strnombreLaboral", clienteBean.getCentroTrabajo());
			parametros.put("strcargo", clienteBean.getCargo());
			parametros.put("strmodalidad", clienteBean.getIdTipoModalidad());
			parametros.put("strenvioMail", clienteBean.getIdTipoEnvio());
			nombreGenerado=exporJasperToPdf(parametros,datosPdfBeanAux.getRutasPdfBean(), esRegeneracion);
		} catch (Exception e) {
			logger.error("Error al generar el PDF...[PdfServiceImpl - generacionPDF]"+e);
		}
		return nombreGenerado;
	}
	
	private String exporJasperToPdf(Map<String, Object> parametros,RutasPdfBean rutasPdfBean, Boolean esRegeneracion){
		String nombrePDF = null;
		String nroDNI = null;
		String codigoContrato = null;
		List<InputStream> pdfs = null;
		try{
			logger.info("Incio de Exportar PDF");
			if (parametros == null) {
				parametros = new HashMap<>();
			}				
			@SuppressWarnings("unchecked")
			List<DetalleBean> listaCuotas = (List<DetalleBean>)parametros.get("strListaCuota");
			codigoContrato = parametros.get("strIDContrato").toString();//TODO: quedara con el strIDContrato???
			nroDNI = parametros.get("strDNI").toString();//TODO: PEDIENTE
			
			Boolean hri = false;
			Boolean con = false;
			Boolean pre = false;
			Boolean cro = false;
			Boolean pag = false;
			Boolean seg = true;//pdfAsyncService.generarDocumentoSEG(parametros, rutasPdfBean, codigoContrato);
			Boolean ace = false;
			
			if(!esRegeneracion) {
				 hri = pdfAsyncService.generarDocumentoHRI(parametros, rutasPdfBean, codigoContrato, !Constantes.RUTA_FIRMADO);
				 con = pdfAsyncService.generarDocumentoCON(parametros, rutasPdfBean, codigoContrato);
				 pre = pdfAsyncService.generarDocumentoPRE(parametros, rutasPdfBean, codigoContrato);
				 cro = pdfAsyncService.generarDocumentoCRO(parametros, rutasPdfBean, codigoContrato, listaCuotas, !Constantes.RUTA_FIRMADO);
				 pag = pdfAsyncService.generarDocumentoPAG(parametros, rutasPdfBean, codigoContrato);
				 seg = true;//pdfAsyncService.generarDocumentoSEG(parametros, rutasPdfBean, codigoContrato);
				 ace = pdfAsyncService.generarDocumentoACE(parametros, rutasPdfBean, codigoContrato);
				 
				 Boolean documentosGeneradosOk = true;
					if (!hri || !con || !pre || !cro || !pag || !seg || !ace) {
						documentosGeneradosOk = false;
					}
					else{
						
						FileUnicoMetadata metadata = new FileUnicoMetadata();		
						metadata.setDoi(String.valueOf(parametros.get("strDNI")));
						metadata.setTipoDoi(String.valueOf(parametros.get("strTipoDocumento")));
						metadata.setNumeroContrato(codigoContrato);
						/*
						metadata.setCodigoCentral(titular.getCodigoCentral());				
						metadata.setOficinaGestora(firmaContrato.getOficinaGestora());				
						metadata.setProcedencia(firmaContrato.getProcedencia());
						metadata.setDocumento(template.getNbTipoFileunico());
						*/
						metadata.setEstadoConciliacion("NA");
						metadata.setEstadoValidacion("OK");
						
						String rutaHRI=rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_HRI +codigoContrato + Constantes.EXTENSION_TXT;
						Util.generarMetadata(rutaHRI,metadata);
						
						String rutaCON=rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_CON +codigoContrato + Constantes.EXTENSION_TXT;
						Util.generarMetadata(rutaCON,metadata);
						
						String rutaPRE=rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_PREPAG +codigoContrato + Constantes.EXTENSION_TXT;
						Util.generarMetadata(rutaPRE,metadata);
						
						String rutaCRO=rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_CRO +codigoContrato + Constantes.EXTENSION_TXT;
						Util.generarMetadata(rutaCRO,metadata);
						
						String rutaPAG=rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_PAG +codigoContrato + Constantes.EXTENSION_TXT;
						Util.generarMetadata(rutaPAG,metadata);
						
						String rutaACE=rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_ACE +codigoContrato + Constantes.EXTENSION_TXT;
						Util.generarMetadata(rutaACE,metadata);
					}
					

					if (!documentosGeneradosOk) {
						Util.deleteFilesByPrefix(rutasPdfBean.getRutaGeneracionContrato(), codigoContrato);
						Util.deleteFilesByPrefix(rutasPdfBean.getRutaConsolidado(), codigoContrato);
						throw new ServiceException("Error al exportar PDF contrato:" + codigoContrato);
					}

					logger.info("fin proceso hilos pdf...inicio generando consolidado");			
					 //Consolidado
					pdfs = new ArrayList<>();
					pdfs.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_HRI + codigoContrato + Constantes.EXTENSION_PDF));
					pdfs.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_CRO + codigoContrato + Constantes.EXTENSION_PDF));
					pdfs.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_CON + codigoContrato + Constantes.EXTENSION_PDF));
					pdfs.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_PAG + codigoContrato + Constantes.EXTENSION_PDF));
					pdfs.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_SOL + codigoContrato + Constantes.EXTENSION_PDF));
					pdfs.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_ACE + codigoContrato + Constantes.EXTENSION_PDF));

//					Date date = new Date();
//					SimpleDateFormat sdf = new SimpleDateFormat(Constantes.FORMATO_FECHA);
//					String strDate = sdf.format(date);
					nombrePDF = Util.generarNombreConsolidadoPDF(nroDNI,codigoContrato);//Constantes.DOCUMENTO_T + codigoCliente + codigoContrato + "" + strDate + Constantes.EXTENSION_PDF;
					OutputStream output = new FileOutputStream(rutasPdfBean.getRutaConsolidado() + nombrePDF);
					Util.concatenaPDF(pdfs, output, true);
					
			}else {
				 hri = pdfAsyncService.generarDocumentoHRI(parametros, rutasPdfBean, codigoContrato, !Constantes.RUTA_FIRMADO);
				 cro = pdfAsyncService.generarDocumentoCRO(parametros, rutasPdfBean, codigoContrato, listaCuotas, !Constantes.RUTA_FIRMADO);
			
				 Boolean documentosGeneradosOk = true;
					if (!hri || !cro ) {
						documentosGeneradosOk = false;
					}
					else{
						
						FileUnicoMetadata metadata = new FileUnicoMetadata();		
						metadata.setDoi(String.valueOf(parametros.get("strDNI")));
						metadata.setTipoDoi(String.valueOf(parametros.get("strTipoDocumento")));
						metadata.setNumeroContrato(codigoContrato);
						metadata.setEstadoConciliacion("NA");
						metadata.setEstadoValidacion("OK");
						
						String rutaHRI=rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_HRI +codigoContrato + Constantes.EXTENSION_TXT;
						Util.generarMetadata(rutaHRI,metadata);
						
						String rutaCRO=rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_CRO +codigoContrato + Constantes.EXTENSION_TXT;
						Util.generarMetadata(rutaCRO,metadata);
						
						}
					

					if (!documentosGeneradosOk) {
						Util.deleteFilesByPrefix(rutasPdfBean.getRutaGeneracionContrato(), codigoContrato);
						Util.deleteFilesByPrefix(rutasPdfBean.getRutaConsolidado(), codigoContrato);
						throw new ServiceException("Error al exportar PDF contrato:" + codigoContrato);
					}

					logger.info("fin proceso hilos pdf...inicio generando consolidado");			
					 //Consolidado
					pdfs = new ArrayList<>();
					pdfs.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_HRI + codigoContrato + Constantes.EXTENSION_PDF));
					pdfs.add(new FileInputStream(rutasPdfBean.getRutaGeneracionContrato() + Constantes.DOCUMENTO_CRO + codigoContrato + Constantes.EXTENSION_PDF));
				
//					Date date = new Date();
//					SimpleDateFormat sdf = new SimpleDateFormat(Constantes.FORMATO_FECHA);
					//String strDate = sdf.format(date);
					nombrePDF = Util.generarNombreConsolidadoPDF(nroDNI,codigoContrato);//Constantes.DOCUMENTO_T + codigoCliente + codigoContrato + "" + strDate + Constantes.EXTENSION_PDF;
					OutputStream output = new FileOutputStream(rutasPdfBean.getRutaConsolidado() + nombrePDF);
					Util.concatenaPDF(pdfs, output, true);
			}

			
			logger.info("fin proceso generar consolidado pdf:" + nombrePDF);
		}catch(Exception e){
			logger.error("error al exportar PDF", e);
			Util.deleteFilesByPrefix(rutasPdfBean.getRutaGeneracionContrato(),codigoContrato);
			Util.deleteFilesByPrefix(rutasPdfBean.getRutaConsolidado(),codigoContrato);
			throw new ServiceException("Error al exportar los PDFS contrato:" + codigoContrato);
		}
		return nombrePDF;
	}

	private Map<String, Object> obtenerParametrosPDF(DatosPdfBean datosPdfBean){
		Map<String, Object> parametros = null;
		try{
			DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
		    simbolo.setDecimalSeparator('.');
		    simbolo.setGroupingSeparator(',');
		    DecimalFormat formateador = new DecimalFormat("#####0.00", simbolo);		    
		    String tea  = datosPdfBean.getTea() == null ? "" : formateador.format(datosPdfBean.getTea());
			String tcea = datosPdfBean.getTcea() == null ? "" : formateador.format(datosPdfBean.getTcea());
		    
			parametros = new HashMap<>();
			parametros.put("strIDContrato", datosPdfBean.getCodigoContrato());
			parametros.put("strIDCliente", datosPdfBean.getCodigoCliente());
			parametros.put("strDomicilio", datosPdfBean.getDomicilio());
			parametros.put("strRuc", datosPdfBean.getRUC());
			parametros.put("strNombRep1", datosPdfBean.getNombreRepresentante1());
			parametros.put("strDniRep1", datosPdfBean.getDniRepresentante1());
			parametros.put("strPoderRep1", datosPdfBean.getPoderRepresentante1());
			parametros.put("strNombRep2", datosPdfBean.getNombreRepresentante2());
			parametros.put("strDniRep2", datosPdfBean.getDniRepresentante2());
			parametros.put("strPoderRep2", datosPdfBean.getPoderRepresentante2());
			parametros.put("strnumeroLetras", NumerosLetras.convertNumberToLetter(datosPdfBean.getImportePrestamo()));
			parametros.put("strsimboloMoneda", datosPdfBean.getSimboloMonedaPdf());			
			parametros.put("strIDContra", datosPdfBean.getCodigoContrato());
			parametros.put("strNombreCliente", datosPdfBean.getNombreCompleto());
			parametros.put("strcorreo", datosPdfBean.getCorreoCliente());
			parametros.put("strEstadoCivil", datosPdfBean.getEstadoCivil());
			parametros.put("strDNI", datosPdfBean.getNumeroDocumentoPdf());
			parametros.put("strTipoDocumento", datosPdfBean.getTipoDocumento());
			parametros.put("strDireccion", datosPdfBean.getDireccion());
			parametros.put("strDistrito", datosPdfBean.getDistrito());
			parametros.put("strProvincia", datosPdfBean.getProvincia());
			parametros.put("strDepartamento", datosPdfBean.getDepartamento());
			parametros.put("strNumeroCuotas", datosPdfBean.getNumeroCuotas());
			parametros.put("strFechaActual", datosPdfBean.getFechaActual());
			parametros.put("strImporteBien", datosPdfBean.getPrecioEquipo());
			parametros.put("strImporteInicial", datosPdfBean.getCuotaInicial());
			parametros.put("strImportePrestamo", datosPdfBean.getImportePrestamo());	
			parametros.put("strFechaPrimerVencimiento", datosPdfBean.getFechaPrimerVencimiento());
			parametros.put("strFechaNacimiento", datosPdfBean.getFechaNacimiento());
			parametros.put("strNombre", datosPdfBean.getPrimerNombre());
			parametros.put("strApePaterno", datosPdfBean.getApellidoPaterno());
			parametros.put("strApeMaterno", datosPdfBean.getApellidoMaterno());			
			parametros.put("strprofesion", datosPdfBean.getProfesionPdf());
			parametros.put("strIdSexo", datosPdfBean.getCodigoSexo());
			parametros.put("strIdEstadoCivil", datosPdfBean.getCodigoEstadoCivil());		
			parametros.put("strTipoMoneda", datosPdfBean.getTipoMonedaPdf());
			parametros.put("strTipoPrestamo", datosPdfBean.getTipoPrestamoPdf());
			parametros.put("strTipoSeguro", datosPdfBean.getTipoSeguroPdf());
			parametros.put("strFechaDesembolso", datosPdfBean.getFechaDesembolsoPdf());
			parametros.put("strTea", tea);
			parametros.put("strTcea", tcea);
			parametros.put("strNumeroDocumento", datosPdfBean.getNumeroDocumentoPdf());
			parametros.put("strIDAuxContrato", datosPdfBean.getAuxCodigoContratoPdf());
			parametros.put("strnombCompa", datosPdfBean.getNombreCompania());
			parametros.put("strLogoBBVA", datosPdfBean.getRutasPdfBean().getRutaImagenBBVACF());
			parametros.put("strFirmaRep1", datosPdfBean.getRutasPdfBean().getRutaImagenFirmaRepresentante1());
			parametros.put("strFirmaRep2", datosPdfBean.getRutasPdfBean().getRutaImagenFirmaRepresentante2());
			parametros.put("strLogoRIMAC", datosPdfBean.getRutasPdfBean().getRutaImagenLogoRimac());
			parametros.put("strListaCuota", datosPdfBean.getListaCuota());
			parametros.put("strFechaFormalizacion", datosPdfBean.getFechaReDesembolso() == null ? datosPdfBean.getFechaVenta() : datosPdfBean.getFechaReDesembolso());
			parametros.put("strFechaFinalContrato", datosPdfBean.getFechaFinalContrato());
			parametros.put("strTotalInteresCompensatorio", datosPdfBean.getTotalInteresCompensatorio());
			parametros.put("strImporteSeguro", datosPdfBean.getImporteSeguro());
			parametros.put("strMontoTotalCuota", datosPdfBean.getMontoTotalCuota());
			parametros.put("strMontoTotalPagar", datosPdfBean.getMontoTotalPagar());
			parametros.put("strDiaPago", datosPdfBean.getDiasPago());
			parametros.put("strnumPoliza", datosPdfBean.getNumeroPoliza());
			parametros.put("strPenalidad", datosPdfBean.getPenalidad());
			parametros.put("strCostoEnvio", datosPdfBean.getCostoEnvio());
			parametros.put("strDia", datosPdfBean.getDia());
			parametros.put("strMes", datosPdfBean.getMes());
			parametros.put("strAnio", datosPdfBean.getAnio());
			parametros.put(JRParameter.REPORT_LOCALE, Locale.ENGLISH);
			parametros.put("dbMontoTotalAmortizacion", datosPdfBean.getTotalMontoAmortizacion());
			parametros.put("dbMontoTotalInteresDevengado", datosPdfBean.getTotalMontoInteresDevengado());
			parametros.put("dbMontoTotalSeguroDesgravamen", datosPdfBean.getTotalMontoSeguroDesgravamen());
			parametros.put("dbMontoTotalGastosComisiones", datosPdfBean.getTotalMontoGastosComisiones());
			parametros.put("dbMontoTotalCuotas", datosPdfBean.getTotalMontoCuotas());
		}catch(Exception e){
			logger.error("error al obtener los parametros PDF" + e);	
		}
		return parametros;
	}
	
}
