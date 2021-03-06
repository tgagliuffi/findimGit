package com.bbva.findim.bck.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.bbva.findim.bck.domain.catalogs.CatalogsResult;
import com.bbva.findim.bck.domain.catalogs.Datum;
import com.bbva.findim.bck.service.CatalogService;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.util.PropertyUtilCnx;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.CatalogoConstant;
import com.bbva.findim.dom.CatalogoBean;

@Service
public class CatalogServiceImpl extends BaseServiceBackImpl implements CatalogService {
	
	@Autowired
	private PropertyUtilCnx propertyUtilCnx;
	
	public List<CatalogoBean> obtenerTablaCorporativa(String codigo, String tSec) throws Exception{
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		String url = propertyUtilCnx.getString(CatalogoConstant.CODIGO_URL_CATALOGS).toString();
		String id = propertyUtilCnx.getString(CatalogoConstant.CODIGO_CATALOG_ID_KEY).toString();
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		if(StringUtils.isNotBlank(codigo)){
			params.add(id, codigo);
		}
		
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();	
		ResponseEntity<CatalogsResult> responseEntity = restTemplate.exchange(uriComponents.toUri() ,HttpMethod.GET,   new HttpEntity<String>(headers),  CatalogsResult.class);
    	responseEntity.getBody();	
    	List<Datum> lstCatalogPercy = null; 
    		
    	List<Datum> lstCatalog = null;
    	List<CatalogoBean> lstCatalogoBean = null;
    	if(responseEntity.getBody()!=null){
    		lstCatalog = responseEntity.getBody().getData();
    		
        		
        	while((responseEntity.getBody().getPagination().getNextPage()!=null)){
        		String www= responseEntity.getBody().getPagination().getNextPage();
        		String[] prueba = www.split("&");
        		for (int i = 0; i < prueba.length; i++) {
					if(i>0){
						String[] partes = prueba[i].split("=");
						params.add(partes[0], partes[1]);
					}
				}
        		uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();
        		responseEntity = restTemplate.exchange(uriComponents.toUri() ,HttpMethod.GET,   new HttpEntity<String>(headers),  CatalogsResult.class);
        		lstCatalogPercy=responseEntity.getBody().getData();
        		lstCatalog.addAll(lstCatalogPercy);
        	}
    		
    		if(lstCatalog!=null){
    			lstCatalogoBean = new ArrayList<CatalogoBean>();
    			for (int i = 0; i < lstCatalog.size(); i++) {
    				Datum beanBack = lstCatalog.get(i);
					CatalogoBean beanFrom = new CatalogoBean();
					beanFrom.setIdCatalogo(beanBack.getCatalogId());
					for (int j = 0; j < beanBack.getValues().size(); j++) {
						beanFrom.setStValue(beanBack.getValues().get(j).getDescriptions().get(0).getValue());
					}
					lstCatalogoBean.add(beanFrom);
				}
    		}
    	}
    	 return lstCatalogoBean;
	}

}
