
package com.bbva.findim.bck.domain.loanInput;

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
    "delinquencyCalculationMethod",
    "insuranceCoverage"
})
public class Indicators {

    @JsonProperty("delinquencyCalculationMethod")
    private Object delinquencyCalculationMethod;
    @JsonProperty("insuranceCoverage")
    private InsuranceCoverage insuranceCoverage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("delinquencyCalculationMethod")
    public Object getDelinquencyCalculationMethod() {
        return delinquencyCalculationMethod;
    }

    @JsonProperty("delinquencyCalculationMethod")
    public void setDelinquencyCalculationMethod(Object delinquencyCalculationMethod) {
        this.delinquencyCalculationMethod = delinquencyCalculationMethod;
    }

    @JsonProperty("insuranceCoverage")
    public InsuranceCoverage getInsuranceCoverage() {
        return insuranceCoverage;
    }

    @JsonProperty("insuranceCoverage")
    public void setInsuranceCoverage(InsuranceCoverage insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
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
