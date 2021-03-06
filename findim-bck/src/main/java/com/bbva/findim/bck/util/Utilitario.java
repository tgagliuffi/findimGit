package com.bbva.findim.bck.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bbva.findim.bck.configuration.domain.error.Parametro;


public class Utilitario {

	public static String extraerValorDeParametros(List<Parametro> parametros,
			String codigoTipo, String codigo) {
		if(parametros == null) {
			return null;
		}
		if(parametros.isEmpty()) {
			return null;
		}
		if(StringUtils.isBlank(codigoTipo)) {
			return null;
		}
		if(StringUtils.isBlank(codigo)) {
			return null;
		}
		Parametro parametroABuscar = new Parametro(codigoTipo, codigo);
		int indice = parametros.indexOf(parametroABuscar);
		if(indice < 0) {
			return null;
		}
		Parametro parametroObtenido = parametros.get(indice);
		return parametroObtenido.getValor();
	}
	
	public static String nivelUbigeoHost(String ubigeoHostCompleto, String nivel){
		String res = "";
		if(nivel.equals(NivelUbigeo.DEPARTAMENTO)){
			res = ubigeoHostCompleto.substring(0,2);
		}else if(nivel.equals(NivelUbigeo.PROVINCIA)){
			res = ubigeoHostCompleto.substring(0,4);
		}else if(nivel.equals(NivelUbigeo.DISTRITO)){
			res = ubigeoHostCompleto;
		}
		return res;
	}
	
	
	public class NivelUbigeo{
		public static final String DEPARTAMENTO = "DEP";
		public static final String PROVINCIA = "PRO";
		public static final String DISTRITO = "DIS";
	}
	
	
	public static String replaceParametroUrl(String url){
		if(url!=null)
			return url.replace(", ", ";").replace("{", "(").replace("}", ")").replace("=", "==");
	  return null;				
	}
 
}
