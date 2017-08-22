package com.bbva.findim.bck.configuration.domain.error;

import java.io.IOException;

import org.apache.cxf.helpers.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

public class MyCustomResponseErrorHandler implements ResponseErrorHandler {
	
	private ResponseErrorHandler myErrorHandler = new DefaultResponseErrorHandler();

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		  return myErrorHandler.hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		 String body = IOUtils.toString(response.getBody());
	        MyCustomException exception = new MyCustomException(response.getStatusCode(), body, body);
	        throw exception;

	}

}
