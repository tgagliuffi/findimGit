package com.bbva.findim.ws.controller;		
		
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbva.findim.dom.ParametroBean;
import com.bbva.findim.dom.ProcesoBatchLogBean;
import com.bbva.findim.dom.ProcesoBatchLogDtBean;
import com.bbva.findim.dom.ProcesoTareaBean;
import com.bbva.findim.sql.service.BatchLogDtService;
import com.bbva.findim.sql.service.BatchLogService;
import com.bbva.findim.sql.service.ParametroService;		
		
@Controller		
public class ParametriaController {		
		
	private static final Logger LOGGER = LogManager.getLogger(ParametriaController.class);		
			
			
	@Autowired		
	private ParametroService parametroService;
	
	@Autowired		
	private BatchLogService batchLogService;
	
	@Autowired		
	private BatchLogDtService batchLogDtService;
			
			
	@RequestMapping(value = "/listaDetalleParametro/{idPadre}",method = RequestMethod.GET,headers="Accept=application/json")		
	@ResponseBody		
	public List<ParametroBean> listaParametros(@PathVariable("idPadre") Integer idPadre) {		
		List<ParametroBean> listaParametroBean = null;		
		try {		
			LOGGER.info("[InicioController contratoService.busquedaContrato]"); 			
			//Oracle					
			listaParametroBean = parametroService.listarParametrosDetalle(idPadre);		
			LOGGER.info("[InicioController listaParametroBean]:" + listaParametroBean); 		
		} catch (Exception e) {		
			LOGGER.error(e.getMessage(), e);		
		}			
		return listaParametroBean;		
	}	
	@RequestMapping(value = "/obtenerDireccionHost/{dirAndocs}",method = RequestMethod.GET,headers="Accept=application/json")		
	@ResponseBody	
	public String obtenerEquivalenciaH(@PathVariable("dirAndocs") String nroDni) {
		String dirIngles = null;
		try {		
			LOGGER.info("[ParametriaController Inicio :  obtenerDirCastIngles]"); 			
			dirIngles = parametroService.obtenerDireccionIngles(nroDni);
			LOGGER.info("[ParametriaController Fin : obtenerDirCastIngles]:" + dirIngles); 		
		} catch (Exception e) {		
			LOGGER.error(e.getMessage(), e);	
			return dirIngles;
		}			
		return dirIngles;	
	}
	
	@RequestMapping(value = "/listarDetalleProceso/{cdProceso}",method = RequestMethod.GET,headers="Accept=application/json")		
	@ResponseBody	
	public List<ProcesoBatchLogDtBean> listarDetalleProceso(@PathVariable("cdProceso") String cdProceso) {		
		List<ProcesoBatchLogDtBean> listaDetalleProceso = null;		
		try {		
			ProcesoBatchLogBean beanBatch =batchLogService.obtenerProcesoBatch(cdProceso);
			LOGGER.info("[InicioController contratoService.busquedaContrato]"); 			
			//Oracle	
			listaDetalleProceso = batchLogDtService.listarDetallesProcesoBatch(beanBatch.getIdProceso().toString());		
			LOGGER.info("[InicioController listaDetalleProceso]:" + listaDetalleProceso); 		
		} catch (Exception e) {		
			LOGGER.error(e.getMessage(), e);		
		}			
		return listaDetalleProceso;		
	}
	
	@RequestMapping(value = "/obtenerTarea/{idTarea}",method = RequestMethod.GET,headers="Accept=application/json")		
	@ResponseBody	
	public ProcesoTareaBean obtenerTarea(@PathVariable("idTarea") String idTarea) {		
//		ProcesoTareaBean procesoTareaBean = null;		
//		try {		
//			ProcesoTareaBean procesoTareaBean =batchLogService.obtenerProcesoBatch(idTarea);
//			LOGGER.info("[InicioController contratoService.busquedaContrato]"); 			
//			//Oracle	
//			listaDetalleProceso = batchLogDtService.listarDetallesProcesoBatch(beanBatch.getIdProceso().toString());		
//			LOGGER.info("[InicioController listaDetalleProceso]:" + listaDetalleProceso); 		
//		} catch (Exception e) {		
//			LOGGER.error(e.getMessage(), e);		
//		}			
		return null;		
	}
	
			
}