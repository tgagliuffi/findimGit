package com.bbva.findim.bck.domain.gt;

import java.util.List;

public class AuthenticationDatum {
	
	private String idAuthenticationData;
	private List<String> authenticationData;
	
	public String getIdAuthenticationData() {
		return idAuthenticationData;
	}
	public void setIdAuthenticationData(String idAuthenticationData) {
		this.idAuthenticationData = idAuthenticationData;
	}
	public List<String> getAuthenticationData() {
		return authenticationData;
	}
	public void setAuthenticationData(List<String> authenticationData) {
		this.authenticationData = authenticationData;
	}

	
}
