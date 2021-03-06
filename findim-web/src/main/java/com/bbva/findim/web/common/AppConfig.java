package com.bbva.findim.web.common;
//CAMBIO TERESA
import java.io.File;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bbva.findim.dom.util.PropertyUtil;

@Configuration
public class AppConfig {

	@Bean
	public PropertyUtil wsHuascaranProperties() throws IOException {
		String configPath = System.getProperty("findim-web.config.path");
		return new PropertyUtil(configPath + File.separator + "findim-web.properties");
	}

}
