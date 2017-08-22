package com.bbva.findim.web.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class RestUtil {

	private RestUtil() {
	}

	public static <T> HttpEntity<T> getHttpEntity(T t) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<>(t, headers);
	}

}
