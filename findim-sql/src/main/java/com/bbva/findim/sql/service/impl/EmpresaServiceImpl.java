package com.bbva.findim.sql.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbva.findim.dom.EmpresaBean;
import com.bbva.findim.dom.EmpresaDetalle;
import com.bbva.findim.sql.dao.EmpresaDao;
import com.bbva.findim.sql.service.EmpresaService;

@Service("empresaService")
public class EmpresaServiceImpl implements EmpresaService {
	
	@Autowired
	private EmpresaDao empresaDAO;

	public EmpresaBean obtenerEmpresa(String cdEmpresa) throws Exception {
		// TODO Auto-generated method stub
		return empresaDAO.obtenerEmpresa(cdEmpresa);
	}

	public List<EmpresaDetalle> listarEmpresa(Long idEmpresa, String cdEmpresa) throws Exception {
		// TODO Auto-generated method stub
		return empresaDAO.listarEmpresa(idEmpresa, cdEmpresa);
	}

	
}
