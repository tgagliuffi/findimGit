package com.bbva.findim.web.service;

import com.bbva.findim.dom.BiometriaBean;
import com.bbva.findim.dom.BiometriaValidaMaxIntentosBean;
import com.bbva.findim.dom.ResultadoBiometriaBean;

public interface BiometriaRestService {

	Long registrar(String numeroDocumento, Integer codigoProceso);

	String actualizar(BiometriaBean biometriaBean);

	ResultadoBiometriaBean obtenerResultado(BiometriaBean biometriaBean);

	BiometriaValidaMaxIntentosBean validarMaximoIntentos(String numeroDocumento);

}
