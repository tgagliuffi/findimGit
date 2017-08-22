package com.bbva.findim.sql.dao;

import java.util.List;

import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.dom.EmpresaDetalle;

public interface EmpresaDao {
	
	EmpresaBean obtenerEmpresa(String cdEmpresa)throws Exception;
	List<EmpresaDetalle> listarEmpresa(Long idEmpresa, String cdEmpresa)throws Exception;

}
