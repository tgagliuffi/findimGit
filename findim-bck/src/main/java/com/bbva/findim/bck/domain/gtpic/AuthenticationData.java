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
@JsonPropertyOrder({ "idAuthenticationData", "authenticationData" })
public class AuthenticationData {

	@JsonProperty("idAuthenticationData")
	private String idAuthenticationData;
	@JsonProperty("authenticationData")
	private List<String> authenticationData = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("idAuthenticationData")
	public String getIdAuthenticationData() {
		return idAuthenticationData;
	}

	@JsonProperty("idAuthenticationData")
	public void setIdAuthenticationData(String idAuthenticationData) {
		this.idAuthenticationData = idAuthenticationData;
	}

	@JsonProperty("authenticationData")
	public List<String> getAuthenticationData() {
		return authenticationData;
	}

	@JsonProperty("authenticationData")
	public void setAuthenticationData(List<String> authenticationData) {
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