package com.bbva.findim.bck.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bbva.findim.bck.domain.gt.BackendUserRequest;
import com.bbva.findim.bck.domain.gt.GrantingTicketRequest;
import com.bbva.findim.bck.domain.gt.GrantingTicketResponse;
import com.bbva.findim.bck.domain.gtpic.Authentication;
import com.bbva.findim.bck.domain.gtpic.AuthenticationData;
import com.bbva.findim.bck.service.SeguridadBbvaService;
import com.bbva.findim.bck.util.PropertyUtilCnx;
import com.bbva.findim.bck.util.ConstantesConection.Parametro.GrantingTicket;

@Service
public class SeguridadBbvaServiceImpl extends BaseServiceBackImpl implements SeguridadBbvaService {
	
	@Autowired
	private PropertyUtilCnx propertyUtilCnx;
	
	public String generarTSec(int tipo) {
		//1=Pic; 2=bnet; 3=test 
		String url = null,consumerId = null, userId= null, authenticationType= null, autDatumKey= null, autDatumValue= null;
		
		 url = propertyUtilCnx.getString(GrantingTicket.CODIGO_URL_GRANTINGTICKET);
		
	
		if (tipo == 1){
			 consumerId =  propertyUtilCnx.getString(GrantingTicket.CODIGO_CONSUMERID_GRANTINGTICKET);
			 userId =  propertyUtilCnx.getString(GrantingTicket.CODIGO_USERID_GRANTINGTICKET);
			 authenticationType =  propertyUtilCnx.getString(GrantingTicket.CODIGO_AUTHENTICTYPE_GRANTINGTICKET);
			 autDatumKey =  propertyUtilCnx.getString(GrantingTicket.CODIGO_AUTHENTICAID_GRANTINGTICKET);
			 autDatumValue =  propertyUtilCnx.getString(GrantingTicket.CODIGO_AUTHENTICAVAL_GRANTINGTICKET);		
		} else if (tipo == 2){
			 consumerId =  propertyUtilCnx.getString(GrantingTicket.CODIGO_CONSUMERID_GRANTINGTICKET_PUBLIC);
			 userId =  propertyUtilCnx.getString(GrantingTicket.CODIGO_USERID_GRANTINGTICKET_PUBLIC);
			 authenticationType =  propertyUtilCnx.getString(GrantingTicket.CODIGO_AUTHENTICTYPE_GRANTINGTICKET_PUBLIC);
			 autDatumKey =  propertyUtilCnx.getString(GrantingTicket.CODIGO_AUTHENTICAID_GRANTINGTICKET_PUBLIC);
			 autDatumValue =  propertyUtilCnx.getString(GrantingTicket.CODIGO_AUTHENTICAVAL_GRANTINGTICKET_PUBLIC);		
		}else if (tipo == 3){
			 consumerId =  propertyUtilCnx.getString(GrantingTicket.CODIGO_CONSUMERID_GRANTINGTICKET_BACK);
			 userId =  propertyUtilCnx.getString(GrantingTicket.CODIGO_USERID_GRANTINGTICKET_BACK);
			 authenticationType =  propertyUtilCnx.getString(GrantingTicket.CODIGO_AUTHENTICTYPE_GRANTINGTICKET_BACK);
			 autDatumKey =  propertyUtilCnx.getString(GrantingTicket.CODIGO_AUTHENTICAID_GRANTINGTICKET_BACK);
			 autDatumValue =  propertyUtilCnx.getString(GrantingTicket.CODIGO_AUTHENTICAVAL_GRANTINGTICKET_BACK);		
	
		}
		
		Object request = generarRequest(consumerId, userId, authenticationType, autDatumKey, autDatumValue, tipo);
		
		ResponseEntity<GrantingTicketResponse> responseEntity = restTemplate.postForEntity(url, request, GrantingTicketResponse.class);		
		List<String> respuestas = responseEntity.getHeaders().get(HEADER_TSEC);
		if(respuestas != null && !respuestas.isEmpty()){
			return respuestas.get(0);
		}		
		return null;
	}

	private Object generarRequest(String consumerId, String userId, String authenticationType, String autDatumKey, String autDatumValue, int tipo) {
		Authentication aReq = new Authentication();
		aReq.setUserID(userId); 
		aReq.setConsumerID(consumerId);
		aReq.setAuthenticationType(authenticationType);
		aReq.setAuthenticationData(new ArrayList<AuthenticationData>());
		
		AuthenticationData aDat = new AuthenticationData();
		aDat.setIdAuthenticationData(autDatumKey);
		aDat.setAuthenticationData(new ArrayList<String>());
		aDat.getAuthenticationData().add(autDatumValue);
		
		aReq.getAuthenticationData().add(aDat);
		
		GrantingTicketRequest request = new GrantingTicketRequest();
		request.setAuthentication(aReq);
		request.setBackendUserRequest(new BackendUserRequest());
		if(tipo==3){
			request.getBackendUserRequest().setAccessCode(userId);
		}else{
			request.getBackendUserRequest().setAccessCode(StringUtils.EMPTY);

		}
		request.getBackendUserRequest().setDialogId(StringUtils.EMPTY);
		request.getBackendUserRequest().setUserId(StringUtils.EMPTY);
		return request;
		
	}
	
}
