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

}
