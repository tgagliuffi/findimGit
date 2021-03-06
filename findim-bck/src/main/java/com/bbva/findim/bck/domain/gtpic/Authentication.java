package com.bbva.findim.bck.domain.gtpic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "authentication", "backendUserRequest" })
public class Authentication {

	@JsonProperty("userID")
	private String userID;
	@JsonProperty("consumerID")
	private String consumerID;
	@JsonProperty("authenticationType")
	private String authenticationType;
	@JsonProperty("authenticationData")
	private List<AuthenticationData> authenticationData = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("userID")
	public String getUserID() {
		return userID;
	}

	@JsonProperty("userID")
	public void setUserID(String userID) {
		this.userID = userID;
	}

	@JsonProperty("consumerID")
	public String getConsumerID() {
		return consumerID;
	}

	@JsonProperty("consumerID")
	public void setConsumerID(String consumerID) {
		this.consumerID = consumerID;
	}

	@JsonProperty("authenticationType")
	public String getAuthenticationType() {
		return authenticationType;
	}

	@JsonProperty("authenticationType")
	public void setAuthenticationType(String authenticationType) {
		this.authenticationType = authenticationType;
	}

	@JsonProperty("authenticationData")
	public List<AuthenticationData> getAuthenticationData() {
		return authenticationData;
	}

	@JsonProperty("authenticationData")
	public void setAuthenticationData(List<AuthenticationData> authenticationData) {
		this.authenticationData = authenticationData;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
