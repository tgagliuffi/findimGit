package com.bbva.findim.bck.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaseServiceBackImpl {

	protected static final Logger LOGGER = LogManager.getLogger(BaseServiceBackImpl.class);
	
	@Autowired
	protected RestTemplate restTemplate;
	
//	@Autowired
//	protected ConfiguracionREST configuracionREST;
}
