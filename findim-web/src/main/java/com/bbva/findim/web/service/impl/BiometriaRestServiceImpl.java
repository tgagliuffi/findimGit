package com.bbva.findim.web.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bbva.findim.dom.BiometriaBean;
import com.bbva.findim.dom.BiometriaValidaMaxIntentosBean;
import com.bbva.findim.dom.ResultadoBiometriaBean;
import com.bbva.findim.dom.util.PropertyUtil;
import com.bbva.findim.web.service.BiometriaRestService;
import com.bbva.findim.web.util.RestTemplateFacade;
import com.bbva.findim.web.util.RestUtil;

@Service
public class BiometriaRestServiceImpl implements BiometriaRestService {

	private static final Logger LOGGER = LogManager.getLogger(BiometriaRestServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RestTemplateFacade restTemplateFacade;

	@Autowired
	private PropertyUtil prop;

	@Override
	public Long registrar(String numeroDocumento, Integer codigoProceso) {
		Long result = null;
		try {
			String uri = prop.getString("sistema.uriservicio") + "biometria";

			BiometriaBean biometriaBean = new BiometriaBean();
			biometriaBean.setNumeroDocumento(numeroDocumento);
			biometriaBean.setCodigoProceso(codigoProceso);

			ResponseEntity<Long> responseEntity = restTemplate.postForEntity(uri, RestUtil.getHttpEntity(biometriaBean), Long.class);
			result = responseEntity.getBody();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public String actualizar(BiometriaBean biometriaBean) {
		String result = null;
		try {
			String uri = prop.getString("sistema.uriservicio") + "biometria";

			ParameterizedTypeReference<String> stringType = new ParameterizedTypeReference<String>() {};
			result = restTemplateFacade.put(uri, RestUtil.getHttpEntity(biometriaBean), stringType);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public ResultadoBiometriaBean obtenerResultado(BiometriaBean biometriaBean) {
		ResultadoBiometriaBean result = null;
		try {
			String uri = prop.getString("sistema.uriservicio") + "biometria/resultado";

			ParameterizedTypeReference<ResultadoBiometriaBean> resultadoBiometriaBeanType = new ParameterizedTypeReference<ResultadoBiometriaBean>() {};
			result = restTemplateFacade.post(uri, RestUtil.getHttpEntity(biometriaBean), resultadoBiometriaBeanType);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

	@Override
	public BiometriaValidaMaxIntentosBean validarMaximoIntentos(String numeroDocumento) {
		BiometriaValidaMaxIntentosBean result = null;
		try {
			String uri = prop.getString("sistema.uriservicio") + "biometria/validarMaximoIntentos/{numeroDocumento}";

			ResponseEntity<BiometriaValidaMaxIntentosBean> entity = restTemplate.getForEntity(uri, BiometriaValidaMaxIntentosBean.class, numeroDocumento);
			result = entity == null ? null : entity.getBody();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return result;
	}

}
