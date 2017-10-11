package com.bbva.findim.dom.util;

public class Utilitario {
	
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


}
