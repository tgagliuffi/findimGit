package com.bbva.findim.ws.impl;

import org.springframework.stereotype.Service;

import com.bbva.findim.dom.ClienteBean;
import com.bbva.findim.dom.ContratoBean;
import com.bbva.findim.dom.DetalleBean;
import com.bbva.findim.dom.EventoContratoBean;
import com.bbva.findim.dom.FirmaContratoBean;
import com.bbva.findim.dom.RutasPdfBean;
import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.TceaBean;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.sql.dao.LogContratoEventoDao;
import com.bbva.findim.ws.service.ContratoService;
import com.bbva.findim.ws.service.FirmaContratoService;
import com.bbva.findim.ws.service.PdfService;
import com.bbva.findim.ws.util.Enumeradores.EnumRespuestaSignBox;
import com.bbva.findim.ws.util.ServiceException;
import com.bbva.findim.ws.util.Util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service("ContratoService")
public class ContratoServiceImpl implements ContratoService{
	

	private static final Logger logger = LogManager.getLogger(ContratoServiceImpl.class);
	
	@Autowired
	private PdfService pdfService;
	@Autowired
	private LogContratoEventoDao logContratoEventoDao;
	@Autowired
	private FirmaContratoService firmaContratoService;
	
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class, Exception.class })
	public ClienteBean generarPDF(ClienteBean clienteBean) throws ParseException, Exception{
		logger.debug("> Inicio generarPDF");
		SimulacionBean simulacionBean = clienteBean.getSimulacionBean();
		RutasPdfBean  rutasPdfBean = null;
		EventoContratoBean eventoContratoBean = new EventoContratoBean();
		String resultadoGeneracionDocumentos = "";
		
			ContratoBean contratoBean = new ContratoBean();
			
			for(ContratoBean bean : clienteBean.getListaContrato()){
				if(clienteBean.getIdContrato().equals(clienteBean.getIdContrato())){
					contratoBean = bean;
					break;
				}
			}
			
			rutasPdfBean=new RutasPdfBean();
			rutasPdfBean=clienteBean.getDatosPdf().getRutasPdfBean();
			String  tceaWsdl = clienteBean.getTceaWsdl();
			String tea = "";
			
			tea = clienteBean.getSimulacionBean().getListaTarifa().get(0).getTasa();
			
			//Generacion de PDF
			logger.debug("> INICIO Generacion de PDF");
			
			    TceaBean tceaBean = new TceaBean();
			    
			    List<Double> listaMontoCuota = new ArrayList<>();
			    List<String> listaFechaPagoCuota = new ArrayList<>();
			    
			    logger.debug("> INICIO consulta TCEA");
			    
				for (int i = 0; i < simulacionBean.getDetalle().size(); i++) {
					DetalleBean cuotabean = simulacionBean.getDetalle().get(i);
					
					listaMontoCuota.add(cuotabean.getCuotaTotal());
					listaFechaPagoCuota.add(Util.changeFormatDate(Constantes.FORMATO_FECHA_YYYY_MM_DD,Constantes.FORMATO_FECHA_SIMPLE,cuotabean.getFechaVencimiento()));
				}
				
				tceaBean.setMontoCuotas(listaMontoCuota);
				tceaBean.setFechaCuotas(listaFechaPagoCuota);
				tceaBean.setMontoTotal(Double.parseDouble(contratoBean.getImportePrestamo()));
				tceaBean.setFechaCurse(new Date());
				tceaBean.setWsdlLocation(tceaWsdl);
				logger.info("WsdlLocation: " + tceaBean.getWsdlLocation());
				logger.info("MontoCuotas: " + listaMontoCuota);
				logger.info("FechaVencimiento: " + listaFechaPagoCuota);
				logger.info("FechaCurse: " + tceaBean.getFechaCurse());
				
				clienteBean.getDatosPdf().setTcea(Double.parseDouble(simulacionBean.getCabecera().getTcea()));
				clienteBean.getDatosPdf().setTea(Double.parseDouble(tea));
				logger.debug("generarPDF");
				
				clienteBean.getDatosPdf().setRutasPdfBean(rutasPdfBean);
				clienteBean.getDatosPdf().setListaCuota(simulacionBean.getDetalle());
				clienteBean.setTipoRespuesta(Constantes.RespuestaContratoCierre.CierreIncorrecto.getInResultado());
				resultadoGeneracionDocumentos = pdfService.generacionPDF(clienteBean,contratoBean);
			    clienteBean.setNombreArchivo(resultadoGeneracionDocumentos == null ? "":resultadoGeneracionDocumentos);
			
			logger.debug("> FIN Generacion de PDF");			
			
			if(("").equals(clienteBean.getNombreArchivo().trim())){
				clienteBean.setDetalleFuncional("");
				clienteBean.setDetalleTecnico("");
				clienteBean.setTipoRespuesta(Constantes.RespuestaContratoCierre.CierreIncorrecto.getInResultado());
				throw new Exception("No se podrá visualizar Documento PDF");
			}
		    logger.debug("> FIN Realizar COMMIT y listar Contratos");
		    
		    eventoContratoBean.setCliente(clienteBean.getNumeroDocumento());
            eventoContratoBean.setTipoEvento(Constantes.EVENTO_ESTADO_CONTRATO);
            eventoContratoBean.setIdContrato(clienteBean.getIdContrato());
            logContratoEventoDao.registrarEventoContrato(eventoContratoBean);
					
		logger.debug("> FIN guardar datos complementarios");
		return clienteBean;
	}
	
	

	public int  firmarContrato(FirmaContratoBean firmaContratoBean) {

		List<String> listaTipoDocumento = new ArrayList<>(6);
		listaTipoDocumento.add(Constantes.DOCUMENTO_HRI);
		listaTipoDocumento.add(Constantes.DOCUMENTO_CRO);
		listaTipoDocumento.add(Constantes.DOCUMENTO_CON);
		listaTipoDocumento.add(Constantes.DOCUMENTO_PAG);
		listaTipoDocumento.add(Constantes.DOCUMENTO_SOL);
		//listaTipoDocumento.add(Constantes.DOCUMENTO_SDE);
		
		int rptaFirma = EnumRespuestaSignBox.CORRECTO.getCodigo();
		EventoContratoBean eventoContratoBean = new EventoContratoBean();
		EnumRespuestaSignBox resultado = null;

		try {
			for (String tipoContrato : listaTipoDocumento) {
				firmaContratoBean.setTipoContrato(tipoContrato);
				resultado = firmaContratoService.firmaDocumentoContrato(firmaContratoBean);
				
				if(!EnumRespuestaSignBox.CORRECTO.equals(resultado)){
					break;
				}
			}
			
			if (!EnumRespuestaSignBox.CORRECTO.equals(resultado)) {
				rptaFirma = EnumRespuestaSignBox.ERROR.getCodigo();
				 
			}else{				
				eventoContratoBean.setCliente(firmaContratoBean.getNumeroDocumento());
	            eventoContratoBean.setTipoEvento(Constantes.EVENTO_FIRMA_CONTRATO);
	            eventoContratoBean.setIdContrato(firmaContratoBean.getCodigoContrato());
	            logContratoEventoDao.registrarEventoContrato(eventoContratoBean);
					
				Util.deleteFilesByPrefix(firmaContratoBean.getRutaConsolidado(),firmaContratoBean.getCodigoContrato());
				//ruta pdf temporales
				Util.deleteFilesByPrefix(firmaContratoBean.getRutaOrigen(),firmaContratoBean.getCodigoContrato());				
			}		  	    	
		  
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rptaFirma = EnumRespuestaSignBox.ERROR.getCodigo();	
			//Util.deleteFilesByPrefix(firmaContratoBean.getRutaOrigen(),firmaContratoBean.getCodigoAuxiliar());
		}
		
		return rptaFirma;
	}


	
	

}
