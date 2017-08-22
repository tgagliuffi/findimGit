package com.bbva.findim.web.common;

import java.util.Properties;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bbva.findim.dom.util.PropertyUtil;

@ControllerAdvice(basePackages = "com.bbva.client.controller")
public class GlobalControllerAdvice {

	@Autowired
	private PropertyUtil propertyUtil;

	private Long timestamp;
	private Properties httpHeaders;

	@PostConstruct
	private void postConstruct() {
		timestamp = System.currentTimeMillis();
		httpHeaders = propertyUtil.filterByPrefix("http.header.");
	}

	@ModelAttribute
	public void globalConfiguration(Model model, HttpServletResponse response) {
		model.addAttribute("timestamp", timestamp);

		for (Entry<Object, Object> entry : httpHeaders.entrySet()) {
			response.setHeader(entry.getKey().toString(), entry.getValue().toString());
		}
	}

}
