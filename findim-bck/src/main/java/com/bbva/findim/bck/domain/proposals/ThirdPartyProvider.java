
package com.bbva.findim.bck.domain.proposals;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "externalSalesChannel"
})
public class ThirdPartyProvider {

    @JsonProperty("id")
    private String id;
    @JsonProperty("externalSalesChannel")
    private ExternalSalesChannel externalSalesChannel;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("externalSalesChannel")
    public ExternalSalesChannel getExternalSalesChannel() {
        return externalSalesChannel;
    }

    @JsonProperty("externalSalesChannel")
    public void setExternalSalesChannel(ExternalSalesChannel externalSalesChannel) {
        this.externalSalesChannel = externalSalesChannel;
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
