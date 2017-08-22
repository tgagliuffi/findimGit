package com.bbva.findim.bck.domain.communs;

import java.io.File;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bbva.findim.bck.util.PropertyUtilCnx;



@Configuration
public class AppConfigCnx {

	@Bean
	public PropertyUtilCnx cnxPaasAsoServiceProperties() throws IOException {
//		return new PropertyUtilCnx("D:\\\\apps\\cnxServicePaasAso\\config" + File.separator + "cnxServicePaasAso.properties");
		String configPath = System.getProperty("findim-bck.config.path");
		System.out.println("configPath : "+ configPath);
		return new PropertyUtilCnx(configPath + File.separator + "findim-bck.properties");
	}

}
