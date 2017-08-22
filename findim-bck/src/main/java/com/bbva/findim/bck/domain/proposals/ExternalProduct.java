
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
    "commercialValue"
})
public class ExternalProduct {

    @JsonProperty("id")
    private String id;
    @JsonProperty("commercialValue")
    private CommercialValue commercialValue;
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

    public ExternalProduct withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("commercialValue")
    public CommercialValue getCommercialValue() {
        return commercialValue;
    }

    @JsonProperty("commercialValue")
    public void setCommercialValue(CommercialValue commercialValue) {
        this.commercialValue = commercialValue;
    }

    public ExternalProduct withCommercialValue(CommercialValue commercialValue) {
        this.commercialValue = commercialValue;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public ExternalProduct withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
