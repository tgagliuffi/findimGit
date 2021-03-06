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

import com.bbva.findim.bck.domain.communs.Datum;
import com.bbva.findim.bck.domain.tariff.Condition;
import com.bbva.findim.bck.domain.tariff.Tariff;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.service.TariffService;
import com.bbva.findim.bck.util.PropertyUtilCnx;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.TarifaConstant;
import com.bbva.findim.dom.TarifaBean;
import com.bbva.findim.dom.EmpresaBean;

@Service
public class TariffServiceImpl extends BaseServiceBackImpl  implements TariffService {

	@Autowired
	private PropertyUtilCnx propertyUtilCnx;
	
	@Override
	public List<TarifaBean> mostrarTarifas(String tSec, EmpresaBean empresa) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.add(SeguridadBbvaService.HEADER_TSEC, tSec);
		List<TarifaBean> lstTarifaBean  = null;
		
		String url = propertyUtilCnx.getString(TarifaConstant.CODIGO_URL_TARIFF_LIST).toString();
		String paramUno = propertyUtilCnx.getString(TarifaConstant.CODIGO_PARAMETER_THIRDPARTY).toString();
		String paramDos = propertyUtilCnx.getString(TarifaConstant.CODIGO_PARAMETER_EXTERNALPRODUCT).toString();
		
		MultiValueMap<String, String> params = null;
		
		if(StringUtils.isNotBlank(empresa.getCdEmpresa()) && StringUtils.isNotBlank(empresa.getIndenticador()) ){
			params = new LinkedMultiValueMap<String, String>();
				params.add(paramUno, empresa.getCdEmpresa());
				params.add(paramDos, empresa.getIndenticador());
		}
			
		UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build();	
		ResponseEntity<Tariff> responseEntity = restTemplate.exchange(uriComponents.toUri(), HttpMethod.GET, new HttpEntity<String>(headers), Tariff.class);
			
		Tariff tariffResult = responseEntity.getBody();
		lstTarifaBean = new ArrayList<>();
		if(tariffResult!=null){
			if(tariffResult.getData().size()>0){
				for (int i = 0; i < tariffResult.getData().size(); i++) {
					Datum obj = tariffResult.getData().get(i);
					TarifaBean tarifaBean= new TarifaBean();
					tarifaBean.setCodigoTarifa(obj.getId());
					tarifaBean.setDescripcion(obj.getName());
					tarifaBean.setMoneda(obj.getCurrency());
					if(obj.getConditions().size()>0){
						for (int j = 0; j < obj.getConditions().size(); j++) {
							Condition condicion = obj.getConditions().get(j);
							if(condicion.getCategory().getId().equals(TarifaConstant.CODIGO_CATEGOTY_TARIFF_TERM)){
								tarifaBean.setCuota(condicion.getNumericValue()!=null?condicion.getNumericValue().toString():null);
							}
							if(condicion.getCategory().getId().equals(TarifaConstant.CODIGO_CATEGOTY_TARIFF_EAR)){
								tarifaBean.setTasa(condicion.getPercentageValue()!=null?condicion.getPercentageValue().toString():null);
							}
						}
					}
					lstTarifaBean.add(tarifaBean);
				}
			}
		}
		return lstTarifaBean;
	}
	
	@SuppressWarnings("unchecked")
	public TarifaBean obtenerTarifa(Object objetoRequest, String cdTarifa, String tSec, EmpresaBean empresa) throws Exception{
		TarifaBean tarifa = new TarifaBean();
		List<TarifaBean> listTarifa = null;
		String[] arreglo = new String[10];//TODO TGAGLIUFFI CAMBIAR MEJORAR
		//cdTarifa = BBVA001 | 18 | 30
		if(objetoRequest!=null){
			listTarifa = (List<TarifaBean>) objetoRequest;
			 arreglo = cdTarifa.replace("|", "-").split("-");

				for (int i = 0; i < listTarifa.size(); i++) {
					if(listTarifa.get(i).getCodigoTarifa().equals(arreglo[0].trim())){
						String[] descrip = listTarifa.get(i).getDescripcion().split("!");
						tarifa.setCdSubProducto(descrip[0]);
						tarifa.setCuota(listTarifa.get(i).getCuota());
						tarifa.setTasa(listTarifa.get(i).getTasa());
						tarifa.setCodigoTarifa(listTarifa.get(i).getCodigoTarifa());
						tarifa.setTasaSeguro(descrip[3]);
					}
				}	

		}else{
			listTarifa = mostrarTarifas(tSec, empresa);
			for (int i = 0; i < listTarifa.size(); i++) {
				if(listTarifa.get(i).getCodigoTarifa().equals(cdTarifa)){
					String[] descrip = listTarifa.get(i).getDescripcion().split("!");
					tarifa.setCdSubProducto(descrip[0]);
					tarifa.setCuota(listTarifa.get(i).getCuota());
					tarifa.setTasa(listTarifa.get(i).getTasa());
					tarifa.setCodigoTarifa(listTarifa.get(i).getCodigoTarifa());
					tarifa.setTasaSeguro(descrip[3]);
				}
			}	
		}
		return tarifa;
	}

}
