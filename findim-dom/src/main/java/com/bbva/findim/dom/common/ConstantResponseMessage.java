package com.bbva.findim.dom.common;

public class ConstantResponseMessage {
	
	public static final String APPLICATION_JSON_UTF_8_VALUE = "application/json; charset=utf-8";
	
	public static final String CODE_RPTA_OK = "0000";
	public static final String CODE_RPTA_ERROR = "9999";
	
	public static final String CODE_RPTA_ERROR_DESCRIP = "Sucedio un Error inesperado.";
	public static final String MJS_ERROR_FILTRO = "ERROR EN SW FILTRO";
	public static final String MSJ_EXCEPTION = "Error. Comuníquese con sistemas.";
	
	
	
	public static final String MSJ_OK_CUSTOMER = "SE ENCONTRO CLIENTE";
	public static final String MSJ_OK_PERSON = "SE ENCONTRO PERSONA";

	public static final String MSJ_OK_PROPOSALES = "SE ENCONTRO PROPUESTA";
		public static final String MSJ_OK_ALTAPROP_UNO = "OK :Cliente: ";
		public static final String MSJ_OK_APTAPROP_DOS = "- Contrato: ";
		
	public static final String	RESP_SERV_200	= "200";
	public static final String	RESP_SERV_201	= "201";	
	public static final String	RESP_SERV_400	= "400";	
	public static final String	RESP_SERV_409	= "409";	
	public static final String	RESP_SERV_500	= "500";	
	
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

}
