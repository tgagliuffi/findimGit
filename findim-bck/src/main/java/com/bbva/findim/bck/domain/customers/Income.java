
package com.bbva.findim.bck.domain.customers;

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
@JsonPropertyOrder({
    "incomeType",
    "incomeValues"
})
public class Income {

    @JsonProperty("incomeType")
    private IncomeType incomeType;
    @JsonProperty("incomeValues")
    private List<IncomeValue> incomeValues = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("incomeType")
    public IncomeType getIncomeType() {
        return incomeType;
    }

    @JsonProperty("incomeType")
    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    @JsonProperty("incomeValues")
    public List<IncomeValue> getIncomeValues() {
        return incomeValues;
    }

    @JsonProperty("incomeValues")
    public void setIncomeValues(List<IncomeValue> incomeValues) {
        this.incomeValues = incomeValues;
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
