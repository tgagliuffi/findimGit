package com.bbva.findim.bck.domain.gt;

import java.util.List;


public class Authentication {
	
	private String userID;
	private String consumerID;
	private String authenticationType;
	
	private List<AuthenticationDatum>  authenticationData;
	
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getConsumerID() {
		return consumerID;
	}
	public void setConsumerID(String consumerID) {
		this.consumerID = consumerID;
	}
	public String getAuthenticationType() {
		return authenticationType;
	}
	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}
	public List<AuthenticationDatum> getAuthenticationData() {
		return authenticationData;
	}
	public void setAuthenticationData(List<AuthenticationDatum> authenticationData) {
		this.authenticationData = authenticationData;
	}
	
	

}
