package com.bbva.findim.web.service;

import java.math.BigDecimal;

import com.bbva.findim.dom.SimulacionBean;
import com.bbva.findim.dom.EmpresaBean;;

public interface SimulacionPagoService {
	public String fecha();
	public String fechaVencimiento();	
	public SimulacionBean datosInicialesSimulacion(Integer idPadre,EmpresaBean empresa);
	public String cargoFijoMaximo(BigDecimal cargoFijo ,BigDecimal valorCuota );
}
