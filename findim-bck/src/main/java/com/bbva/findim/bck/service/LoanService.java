package com.bbva.findim.bck.service;

import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.PrestamoBean;

public interface LoanService {
	PrestamoBean obtenerPrestamo(PrestamoBean prestamoBean, String tSec) throws Exception;
	SimulacionBean simularPrestamo(SimulacionBean simulacion, String tSec) throws Exception;
	
	enum Moneda {
		PEN("Soles"), USD("Dólares");
		private String moneda;
		Moneda(String moneda) {
	        this.moneda = moneda;
	    }
		public String getMoneda() {
	        return this.moneda;
	    }
	}
}
