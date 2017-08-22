package com.bbva.findim.bck.service;

import com.bbva.findim.bck.domain.trx.TablaCorporativa;

public interface ConfiguracionService {
	
	
	//List<Ubigeo> listarUbigeoPorProvincia(String codigo);

	//List<TipoCuenta> listarTipoCuenta();

	//TipoCuenta obtenerTipoCuenta(String codigo);

	//List<Pregunta> listarPreguntasCandado(Persona persona);

	TablaCorporativa obtenerTablaCorporativa(String codigoTablaCorporativa);
}
