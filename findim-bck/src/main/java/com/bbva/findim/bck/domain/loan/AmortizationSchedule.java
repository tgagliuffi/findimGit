
package com.bbva.findim.bck.domain.loan;

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
    "capitalTotalAmount",
    "detailedAmortizationPayments"
})
public class AmortizationSchedule {

    @JsonProperty("capitalTotalAmount")
    private CapitalTotalAmount capitalTotalAmount;
    @JsonProperty("detailedAmortizationPayments")
    private List<DetailedAmortizationPayment> detailedAmortizationPayments = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("capitalTotalAmount")
    public CapitalTotalAmount getCapitalTotalAmount() {
        return capitalTotalAmount;
    }

    @JsonProperty("capitalTotalAmount")
    public void setCapitalTotalAmount(CapitalTotalAmount capitalTotalAmount) {
        this.capitalTotalAmount = capitalTotalAmount;
    }

    @JsonProperty("detailedAmortizationPayments")
    public List<DetailedAmortizationPayment> getDetailedAmortizationPayments() {
        return detailedAmortizationPayments;
    }

    @JsonProperty("detailedAmortizationPayments")
    public void setDetailedAmortizationPayments(List<DetailedAmortizationPayment> detailedAmortizationPayments) {
        this.detailedAmortizationPayments = detailedAmortizationPayments;
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
