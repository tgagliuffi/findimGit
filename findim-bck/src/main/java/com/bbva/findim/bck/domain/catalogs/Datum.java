
package com.bbva.findim.bck.domain.catalogs;

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
    "catalogId",
    "values"
})
public class Datum {

    @JsonProperty("catalogId")
    private String catalogId;
    @JsonProperty("values")
    private List<Value> values = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("catalogId")
    public String getCatalogId() {
        return catalogId;
    }

    @JsonProperty("catalogId")
    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
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
