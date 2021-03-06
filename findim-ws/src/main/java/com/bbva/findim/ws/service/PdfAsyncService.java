package com.bbva.findim.ws.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;

import com.bbva.findim.dom.DetalleBean;
import com.bbva.findim.dom.RutasPdfBean;

public interface PdfAsyncService {

	Boolean generarDocumentoHRI(Map<String, Object> parametros,RutasPdfBean rutasPdfBean,String codigoAuxContrato,Boolean firmado);

	Boolean generarDocumentoCON(Map<String, Object> parametros,RutasPdfBean rutasPdfBean,String codigoAuxContrato);

	Boolean generarDocumentoPRE(Map<String, Object> parametros,RutasPdfBean rutasPdfBean,String codigoAuxContrato);

	Boolean generarDocumentoCRO(Map<String, Object> parametros,RutasPdfBean rutasPdfBean,String codigoAuxContrato,List<DetalleBean> list,Boolean firmado);

	Boolean generarDocumentoPAG(Map<String, Object> parametros,RutasPdfBean rutasPdfBean,String codigoAuxContrato);

	Boolean generarDocumentoSEG(Map<String, Object> parametros,RutasPdfBean rutasPdfBean,String codigoAuxContrato);

	@Async
	Future<Boolean> generarDocumentoHRIgenerico(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,String rutaDestino);

	@Async
	Future<Boolean> generarDocumentoCROgenerico(Map<String, Object> parametros, RutasPdfBean rutasPdfBean,List<DetalleBean> listaCuota,String rutaDestino);

	byte[] generarCRO(Map<String, Object> parametros, RutasPdfBean rutasPdfBean, List<DetalleBean> listaCuota);

	Boolean generarDocumentoACE(Map<String, Object> parametros, RutasPdfBean rutasPdfBean, String codigoContrato);

}
