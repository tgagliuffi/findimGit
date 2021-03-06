
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
    "occupationType",
    "incomes",
    "workPlace"
})
public class EconomicData {

    @JsonProperty("occupationType")
    private OccupationType occupationType;
    @JsonProperty("incomes")
    private List<Income> incomes = null;
    @JsonProperty("workPlace")
    private WorkPlace workPlace;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("occupationType")
    public OccupationType getOccupationType() {
        return occupationType;
    }

    @JsonProperty("occupationType")
    public void setOccupationType(OccupationType occupationType) {
        this.occupationType = occupationType;
    }

    @JsonProperty("incomes")
    public List<Income> getIncomes() {
        return incomes;
    }

    @JsonProperty("incomes")
    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    @JsonProperty("workPlace")
    public WorkPlace getWorkPlace() {
        return workPlace;
    }

    @JsonProperty("workPlace")
    public void setWorkPlace(WorkPlace workPlace) {
        this.workPlace = workPlace;
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
