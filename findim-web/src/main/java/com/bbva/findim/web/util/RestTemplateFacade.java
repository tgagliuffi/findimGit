package com.bbva.findim.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateFacade {

	@Autowired
	private RestTemplate restTemplate;

	public <T> T post(String url, ParameterizedTypeReference<T> type) {
		return restTemplate.exchange(url, HttpMethod.POST, null, type).getBody();
	}
	
	public <T> T post(String url, HttpEntity<?> entity, ParameterizedTypeReference<T> type) {
		return restTemplate.exchange(url, HttpMethod.POST, entity, type).getBody();
	}

	public <T> T put(String url, HttpEntity<?> entity, ParameterizedTypeReference<T> type) {
		return restTemplate.exchange(url, HttpMethod.PUT, entity, type).getBody();
	}

	public <T> T delete(String url, ParameterizedTypeReference<T> type) {
		return restTemplate.exchange(url, HttpMethod.DELETE, null, type).getBody();
	}

	public <T> T get(String url, ParameterizedTypeReference<T> type) {
		return restTemplate.exchange(url, HttpMethod.GET, null, type).getBody();
	}

}
