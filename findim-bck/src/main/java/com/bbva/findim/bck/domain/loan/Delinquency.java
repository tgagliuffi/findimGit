
package com.bbva.findim.bck.domain.loan;

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
    "capitalAmount",
    "interestAmount",
    "expensesAmount",
    "interestsArrearsAmount"
})
public class Delinquency {

    @JsonProperty("capitalAmount")
    private CapitalAmount capitalAmount;
    @JsonProperty("interestAmount")
    private InterestAmount interestAmount;
    @JsonProperty("expensesAmount")
    private ExpensesAmount expensesAmount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("interestsArrearsAmount")
    private InterestsArrearsAmount interestsArrearsAmount;
    @JsonIgnore
    @JsonProperty("capitalAmount")
    public CapitalAmount getCapitalAmount() {
        return capitalAmount;
    }

    @JsonProperty("capitalAmount")
    public void setCapitalAmount(CapitalAmount capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    @JsonProperty("interestAmount")
    public InterestAmount getInterestAmount() {
        return interestAmount;
    }

    @JsonProperty("interestAmount")
    public void setInterestAmount(InterestAmount interestAmount) {
        this.interestAmount = interestAmount;
    }

    @JsonProperty("expensesAmount")
    public ExpensesAmount getExpensesAmount() {
        return expensesAmount;
    }

    @JsonProperty("expensesAmount")
    public void setExpensesAmount(ExpensesAmount expensesAmount) {
        this.expensesAmount = expensesAmount;
    }

    @JsonProperty("interestsArrearsAmount")
    public InterestsArrearsAmount getInterestsArrearsAmount() {
        return interestsArrearsAmount;
    }

    @JsonProperty("interestsArrearsAmount")
    public void setInterestsArrearsAmount(InterestsArrearsAmount interestsArrearsAmount) {
        this.interestsArrearsAmount = interestsArrearsAmount;
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
