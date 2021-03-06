package com.bbva.findim.bck.domain.gt;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "authentication",
    "backendUserRequest"
})
public class GrantingTicketRequest {
	
// 	@JsonProperty("authentication")
//    private Authentication authentication;
    @JsonProperty("backendUserRequest")
    private BackendUserRequest backendUserRequest;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("authentication")
    private com.bbva.findim.bck.domain.gtpic.Authentication authentication;
    
    /**@return The authentication */
    @JsonProperty("authentication")
    public com.bbva.findim.bck.domain.gtpic.Authentication getAuthentication() {
        return authentication;
    }

    /**  @param authentication The authentication  */
    @JsonProperty("authentication")
    public void setAuthentication(com.bbva.findim.bck.domain.gtpic.Authentication authentication) {
        this.authentication = authentication;
    }

    /**
     * 
     * @return
     *     The backendUserRequest
     */
    @JsonProperty("backendUserRequest")
    public BackendUserRequest getBackendUserRequest() {
        return backendUserRequest;
    }

    /**
     * 
     * @param backendUserRequest
     *     The backendUserRequest
     */
    @JsonProperty("backendUserRequest")
    public void setBackendUserRequest(BackendUserRequest backendUserRequest) {
        this.backendUserRequest = backendUserRequest;
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
