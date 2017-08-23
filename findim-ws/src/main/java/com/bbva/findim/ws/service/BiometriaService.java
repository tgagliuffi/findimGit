package com.bbva.findim.ws.service;

import com.bbva.findim.dom.BiometriaBean;
import com.bbva.findim.dom.BiometriaValidaMaxIntentosBean;
import com.bbva.findim.dom.ResultadoBiometriaBean;

public interface BiometriaService {

	Long registrar(BiometriaBean biometriaBean);

	String actualizar(BiometriaBean biometriaBean);

	BiometriaValidaMaxIntentosBean validarMaximoIntentos(String numeroDocumento);

	ResultadoBiometriaBean obtenerResultado(BiometriaBean biometriaBean);

}
