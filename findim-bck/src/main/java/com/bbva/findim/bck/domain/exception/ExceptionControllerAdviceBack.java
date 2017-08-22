package com.bbva.findim.bck.domain.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import pe.com.bbva.zp.configuracion.dominio.error.ErrorResponse;

@ControllerAdvice
public class ExceptionControllerAdviceBack {
	
	protected static final Logger LOGGER = LogManager.getLogger(ExceptionControllerAdviceBack.class);
	
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(BaseException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(ex.getCodigo());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Throwable ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode("00");
		error.setMessage(ex.getMessage());
		String complementoError = "";
		if(ex instanceof HttpClientErrorException) {
			HttpClientErrorException httpClientErrorException = (HttpClientErrorException) ex;
			complementoError += " " + httpClientErrorException.getResponseBodyAsString();
		}

		LOGGER.error("Error interno." + complementoError, ex);
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}