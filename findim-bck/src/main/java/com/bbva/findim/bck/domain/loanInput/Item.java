
package com.bbva.findim.bck.domain.loanInput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbva.findim.bck.domain.communs.Value;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "commercialName",
    "type",
    "isNegotiable",
    "values"
})
public class Item {

    @JsonProperty("name")
    private Object name;
    @JsonProperty("commercialName")
    private Object commercialName;
    @JsonProperty("type")
    private Object type;
    @JsonProperty("isNegotiable")
    private Object isNegotiable;
    @JsonProperty("values")
    private List<Value> values;
//    private Value values;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public Object getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(Object name) {
        this.name = name;
    }

    @JsonProperty("commercialName")
    public Object getCommercialName() {
        return commercialName;
    }

    @JsonProperty("commercialName")
    public void setCommercialName(Object commercialName) {
        this.commercialName = commercialName;
    }

    @JsonProperty("type")
    public Object getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Object type) {
        this.type = type;
    }

    @JsonProperty("isNegotiable")
    public Object getIsNegotiable() {
        return isNegotiable;
    }

    @JsonProperty("isNegotiable")
    public void setIsNegotiable(Object isNegotiable) {
        this.isNegotiable = isNegotiable;
    }

    @JsonProperty("values")
    public List<Value> getValues() {
        return values;
    }

    @JsonProperty("values")
    public void setValues(List<Value> values) {
        this.values = values;
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
