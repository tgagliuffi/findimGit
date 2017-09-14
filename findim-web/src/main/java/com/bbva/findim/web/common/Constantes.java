package com.bbva.findim.web.common;

public class Constantes {
	
	public static final String APPLICATION_JSON_UTF_8_VALUE = "application/json; charset=utf-8";

	public static final String MENSAJE_NO_DATA = "No Disponible";
	
	public enum TipoError {
		DataAccessException(102),
		InvalidResultSetAccessException(103),
		ParseException(104),
		Exception(105),
		SQLException(106),
		HttpException(404);

	    private Integer inError;
	    TipoError(Integer inError) {
	        this.inError = inError;
	    }	    
	    public Integer getError() {
	        return inError;
	    }
	}
	
	public enum TipoRespuestaWS {
		Satisfactorio(1),
		Error(0);

	    private Integer tipoRespuesta;
	    TipoRespuestaWS(Integer tipoRespuesta) {
	        this.tipoRespuesta = tipoRespuesta;
	    }	    
	    public Integer getTipoRespuestaWS() {
	        return tipoRespuesta;
	    }
	}
	
	public enum RespuestaBusquedaContratoWS {
		NoExisteCliente(1),
		ClienteSinContrato(2),
		ClienteRechazado(3);
	    private Integer respuesta;
		RespuestaBusquedaContratoWS(Integer respuesta) {
	        this.respuesta = respuesta;
	    }	    
	    public Integer getRespuestaBusquedaContratoWS() {
	        return respuesta;
	    }
	}
	
	public enum Genero {
		M("1"),
		F("2");
	    private String genero;
	    Genero(String genero) {
	        this.genero = genero;
	    }	    
	    public String getGenero() {
	        return genero;
	    }
	}

	public enum EstadoCivil {
		S(1),
		C(2),
		V(3),
		D(4),
		F(1),
		U(1),
		X(1);
	    private Integer estadoCivil;
	    EstadoCivil(Integer estadoCivil) {
	        this.estadoCivil = estadoCivil;
	    }	    
	    public Integer getEstadoCivil() {
	        return estadoCivil;
	    }
	}



}
