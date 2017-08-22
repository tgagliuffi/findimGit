package com.bbva.findim.ws.service;

import java.math.BigDecimal;

import com.bbva.findim.dom.SimulacionBean;





public interface SimulacionPagoWS {
	
	
//	public List<Tarifa>  financiamiento();

	
	public SimulacionBean simularFinanciamiento1(String moneda,
			BigDecimal valorEquipo,String tarifaFinanciamiento, String PorcentajeCuotaInicial,
			BigDecimal cuotaInicial, double saldoFinanciar,
			double costoEnvio);
	
	String valorCuota(String fechaInicio, String fechaFin, int cantidadMesesPrestamo,
			String PorcentajeCuotaInicial, double saldoFinanciar, String seguroIndividual);
	
}
