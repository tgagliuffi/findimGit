package com.bbva.findim.bck.service;

import java.util.List;

import com.bbva.findim.dom.CatalogoBean;

public interface CatalogService {

	List<CatalogoBean> obtenerTablaCorporativa(String codigo, String tSec) throws Exception;
}
