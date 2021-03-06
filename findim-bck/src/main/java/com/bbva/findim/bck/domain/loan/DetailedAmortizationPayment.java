
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
    "id",
    "dueDate",
    "capitalAmount",
    "interestAmount",
    "pendingAmount",
    "expensesAmount",
    "subsidizedAmount",
    "amount"
})
public class DetailedAmortizationPayment {

    @JsonProperty("id")
    private String id;
    @JsonProperty("dueDate")
    private String dueDate;
    @JsonProperty("capitalAmount")
    private CapitalAmount capitalAmount;
    @JsonProperty("interestAmount")
    private InterestAmount interestAmount;
    @JsonProperty("pendingAmount")
    private PendingAmount pendingAmount;
    @JsonProperty("expensesAmount")
    private ExpensesAmount expensesAmount;
    @JsonProperty("subsidizedAmount")
    private SubsidizedAmount subsidizedAmount;
    @JsonProperty("amount")
    private Amount amount;
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

    @JsonProperty("dueDate")
    public String getDueDate() {
        return dueDate;
    }

    @JsonProperty("dueDate")
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

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

    @JsonProperty("pendingAmount")
    public PendingAmount getPendingAmount() {
        return pendingAmount;
    }

    @JsonProperty("pendingAmount")
    public void setPendingAmount(PendingAmount pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    @JsonProperty("expensesAmount")
    public ExpensesAmount getExpensesAmount() {
        return expensesAmount;
    }

    @JsonProperty("expensesAmount")
    public void setExpensesAmount(ExpensesAmount expensesAmount) {
        this.expensesAmount = expensesAmount;
    }

    @JsonProperty("subsidizedAmount")
    public SubsidizedAmount getSubsidizedAmount() {
        return subsidizedAmount;
    }

    @JsonProperty("subsidizedAmount")
    public void setSubsidizedAmount(SubsidizedAmount subsidizedAmount) {
        this.subsidizedAmount = subsidizedAmount;
    }

    @JsonProperty("amount")
    public Amount getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Amount amount) {
        this.amount = amount;
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
