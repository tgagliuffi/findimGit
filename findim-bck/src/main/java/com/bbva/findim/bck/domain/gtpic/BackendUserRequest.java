package com.bbva.findim.bck.domain.gtpic;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "userId", "accessCode", "dialogId" })
public class BackendUserRequest {

	@JsonProperty("userId")
	private String userId;
	@JsonProperty("accessCode")
	private String accessCode;
	@JsonProperty("dialogId")
	private String dialogId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("userId")
	public String getUserId() {
		return userId;
	}

	@JsonProperty("userId")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JsonProperty("accessCode")
	public String getAccessCode() {
		return accessCode;
	}

	@JsonProperty("accessCode")
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	@JsonProperty("dialogId")
	public String getDialogId() {
		return dialogId;
	}

	@JsonProperty("dialogId")
	public void setDialogId(String dialogId) {
		this.dialogId = dialogId;
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
