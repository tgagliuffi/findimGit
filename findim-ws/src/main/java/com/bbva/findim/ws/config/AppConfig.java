package com.bbva.findim.ws.config;

import java.io.File;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bbva.findim.ws.util.PropertyUtil;

@Configuration
public class AppConfig {
	@Bean
	public PropertyUtil wsHuascaranProperties() throws IOException {
		String configPath = System.getProperty("findim-ws.config.path");
		return new PropertyUtil(configPath + File.separator + "findim-ws.properties");
	}

}
