package com.bbva.findim.sql.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.ContratoAltaBean;
import com.bbva.findim.dom.OficinaBean;
import com.bbva.findim.dom.UbigeoBean;
import com.bbva.findim.dom.common.Constantes;
import com.bbva.findim.dom.util.Utilitario;
import com.bbva.findim.sql.dao.OficinaDao;
import com.bbva.findim.sql.service.OficinaService;

@Service("oficinaService")
public class OficinaServiceImpl  implements OficinaService{
	
	private static final Logger logger = LogManager.getLogger(OficinaServiceImpl.class);
	@Autowired
	private OficinaDao oficinaDAO;
	
	 public String oficinaAsignadaPorUbigeoReniec(ContratoAltaBean contrato, int tipoCliente) throws Exception {
	     List<OficinaBean> listaOficinasEncontradas = null;
		 String codigoOficina = null, dpto=null, prov=null, dist=null;
		 
		 if(tipoCliente!=3) {
			 dpto = contrato.getDomicilio().getIdDepartamento();
			 prov = contrato.getDomicilio().getIdDepartamento() + contrato.getDomicilio().getIdProvincia();
			 dist = prov+(contrato.getDomicilio().getIdDistrito().length()>2?contrato.getDomicilio().getIdDistrito().substring(1,3):contrato.getDomicilio().getIdDistrito());
			 
			 UbigeoBean ubigeoBean = oficinaDAO.obtenerUbigeoHost(dpto, prov, dist);
	           
		       
	            if(ubigeoBean!=null){
	            	 String ubigeoHost = ubigeoBean.getUbgHost();
	            	 boolean esLima = ( "01".equals(Utilitario.nivelUbigeoHost(ubigeoHost, Utilitario.NivelUbigeo.DEPARTAMENTO)) );

	     	        //buscar una oficina del distrito
	     	        listaOficinasEncontradas = oficinaDAO.listarOficinasPorUbigeoHost(ubigeoHost, Utilitario.NivelUbigeo.DISTRITO);
	     	        if(listaOficinasEncontradas!=null && listaOficinasEncontradas.size()>0){
	     	            codigoOficina = listaOficinasEncontradas.get(0).getCdOficina();
	     	        }

	     	        if(codigoOficina==null){
	     	            //buscar una oficina de la provincia
	     	            listaOficinasEncontradas = oficinaDAO.listarOficinasPorUbigeoHost(ubigeoHost, Utilitario.NivelUbigeo.PROVINCIA);
	     	            if(listaOficinasEncontradas!=null && listaOficinasEncontradas.size()>0){
	     	                codigoOficina = listaOficinasEncontradas.get(0).getCdOficina();
	     	            }
	     	        }

	     	        if(codigoOficina==null){
	     	            if(!esLima){
	     	                //buscar una oficina del departamento
	     	                listaOficinasEncontradas = oficinaDAO.listarOficinasPorUbigeoHost(ubigeoHost, Utilitario.NivelUbigeo.DEPARTAMENTO);
	     	                if(listaOficinasEncontradas!=null && listaOficinasEncontradas.size()>0){
	     	                    codigoOficina = listaOficinasEncontradas.get(0).getCdOficina();
	     	                }
	     	            }else{
	     	                codigoOficina = Constantes.OFICINA_DEFAULT_LIMA;//0486
	     	            }
	     	        }
	            }else if(codigoOficina==null){
		            //TODO si no se encontró oficina, usar predeterminada <--------- existiria este caso??
		            codigoOficina = Constantes.OFICINA_DEFAULT_LIMA;
		        }
	            
		 }else {
			 codigoOficina = "0000";
		 }
	       
		 return codigoOficina;
	 }
	 
	@SuppressWarnings("rawtypes")
	public List<String>  cargarOficina(List<OficinaBean> lstOficinas) {
		 List<String> lstLogCarga = null;
			 try {
				 //truncar tabla oficinas
				 Integer rptaTruncateOficinas = oficinaDAO.truncateOficinas();
				 if(rptaTruncateOficinas==1){
					 lstLogCarga = new ArrayList<String>();
					 for (Iterator iterator = lstOficinas.iterator(); iterator.hasNext();) {
						OficinaBean oficinaBean = (OficinaBean) iterator.next();
						if(oficinaDAO.insert(oficinaBean)==false) {
							lstLogCarga.add("ERROR EN AL INSERTAR LA OFICINA = " + oficinaBean.getCdOficina());
						}
					}
					 	
					 
				 }
				
			} catch (Exception e) {
				logger.error(e);
			}
		 
		 return lstLogCarga;
	 }

}
