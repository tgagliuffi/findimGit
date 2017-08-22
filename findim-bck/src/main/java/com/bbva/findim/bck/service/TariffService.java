package com.bbva.findim.bck.service;

import java.util.List;

import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.dom.EmpresaBean;

public interface TariffService {
	
	List<TarifaBean> mostrarTarifas(String tSec, EmpresaBean empresaBean) throws Exception;
	TarifaBean obtenerTarifa(Object objetoRequest, String cdTarifa, String tSec, EmpresaBean empresaBean) throws Exception;

		

}
