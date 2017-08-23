package com.bbva.findim.ws.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.BiometriaBean;
import com.bbva.findim.dom.BiometriaValidaMaxIntentosBean;
import com.bbva.findim.dom.ResultadoBiometriaBean;
import com.bbva.findim.sql.dao.BiometriaDao;
import com.bbva.findim.ws.service.BiometriaService;


@Service
@Qualifier("biometriaService")
public class BiometriaServiceImpl implements BiometriaService {
	
	@Autowired
	private BiometriaDao biometriaDao;

	@Override
	public Long registrar(BiometriaBean biometriaBean) {
		return biometriaDao.registrar(biometriaBean);
	}

	@Override
	public String actualizar(BiometriaBean biometriaBean) {
		return biometriaDao.actualizar(biometriaBean);
	}

	@Override
	public ResultadoBiometriaBean obtenerResultado(BiometriaBean biometriaBean) {
		ResultadoBiometriaBean  resultadoBiometria = biometriaDao.obtenerResultado(biometriaBean);
		return resultadoBiometria;
	}

	@Override
	public BiometriaValidaMaxIntentosBean validarMaximoIntentos(String numeroDocumento) {
		return biometriaDao.validarMaximoIntentos(numeroDocumento);
	}
}
