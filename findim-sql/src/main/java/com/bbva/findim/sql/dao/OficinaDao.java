package com.bbva.findim.sql.dao;

import java.util.List;

import com.bbva.findim.dom.OficinaBean;
import com.bbva.findim.dom.UbigeoBean;

public interface OficinaDao {
	
    List<OficinaBean> listarOficinasPorUbigeoHost(String ubigeoHost,  String distrito) throws Exception;
    UbigeoBean obtenerUbigeoHost(String dpto, String prov, String dist)throws Exception;

}
