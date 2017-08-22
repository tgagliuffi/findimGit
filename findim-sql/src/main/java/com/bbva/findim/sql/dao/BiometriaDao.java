package com.bbva.findim.sql.dao;

import com.bbva.findim.dom.BiometriaBean;
import com.bbva.findim.dom.BiometriaValidaMaxIntentosBean;
import com.bbva.findim.dom.ResultadoBiometriaBean;

public interface BiometriaDao {

	Long registrar(BiometriaBean biometriaBean);

	String actualizar(BiometriaBean biometriaBean);

	ResultadoBiometriaBean obtenerResultado(BiometriaBean biometriaBean);

	BiometriaValidaMaxIntentosBean validarMaximoIntentos(String numeroDocumento);

}
