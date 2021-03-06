
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
    "category",
    "numericValue",
    "percentageValue"
})
public class Condition {

    @JsonProperty("category")
    private Category category;
    @JsonProperty("numericValue")
    private Integer numericValue;
    @JsonProperty("percentageValue")
    private Integer percentageValue;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("category")
    public Category getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @JsonProperty("numericValue")
    public Integer getNumericValue() {
        return numericValue;
    }

    @JsonProperty("numericValue")
    public void setNumericValue(Integer numericValue) {
        this.numericValue = numericValue;
    }

    @JsonProperty("percentageValue")
    public Integer getPercentageValue() {
        return percentageValue;
    }

    @JsonProperty("percentageValue")
    public void setPercentageValue(Integer percentageValue) {
        this.percentageValue = percentageValue;
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
