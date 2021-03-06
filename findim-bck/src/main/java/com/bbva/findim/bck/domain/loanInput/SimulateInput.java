
package com.bbva.findim.bck.domain.loanInput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbva.findim.bck.domain.loan.InterestPeriodicity;
import com.bbva.findim.bck.domain.loan.LoanResult;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "product",
    "awardedAmount",
    "term",
    "nextPaymentDate",
    "paymentDay",
    "interests",
    "requestDate",
    "capitalPeriodicity",
    "interestPeriodicity",
    "waitingPeriod",
    "conditions",
    "commercialValueAmount",
    "indicators",
    "relatedContracts",
    "participants"
})
public class SimulateInput {

    @JsonProperty("product")
    private Product product;
    @JsonProperty("awardedAmount")
    private AwardedAmount awardedAmount;
    @JsonProperty("term")
    private Term term;
    @JsonProperty("nextPaymentDate")
    private Object nextPaymentDate;
    @JsonProperty("paymentDay")
    private Integer paymentDay;
    @JsonProperty("interests")
    private List<Interest> interests = null;
    @JsonProperty("requestDate")
    private Object requestDate;
    @JsonProperty("capitalPeriodicity")
    private Object capitalPeriodicity;
    @JsonProperty("interestPeriodicity")
    private InterestPeriodicity interestPeriodicity;
    @JsonProperty("waitingPeriod")
    private Object waitingPeriod;
    @JsonProperty("conditions")
    private Conditions conditions;
    @JsonProperty("commercialValueAmount")
    private CommercialValueAmount commercialValueAmount;
    @JsonProperty("indicators")
    private Indicators indicators;
    @JsonProperty("relatedContracts")
    private List<RelatedContract> relatedContracts = null;
    @JsonProperty("participants")
    private List<Participant> participants = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    private LoanResult loan =null;
    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonProperty("awardedAmount")
    public AwardedAmount getAwardedAmount() {
        return awardedAmount;
    }

    @JsonProperty("awardedAmount")
    public void setAwardedAmount(AwardedAmount awardedAmount) {
        this.awardedAmount = awardedAmount;
    }

    @JsonProperty("term")
    public Term getTerm() {
        return term;
    }

    @JsonProperty("term")
    public void setTerm(Term term) {
        this.term = term;
    }

    @JsonProperty("nextPaymentDate")
    public Object getNextPaymentDate() {
        return nextPaymentDate;
    }

    @JsonProperty("nextPaymentDate")
    public void setNextPaymentDate(Object nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    @JsonProperty("paymentDay")
    public Integer getPaymentDay() {
        return paymentDay;
    }

    @JsonProperty("paymentDay")
    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    @JsonProperty("interests")
    public List<Interest> getInterests() {
        return interests;
    }

    @JsonProperty("interests")
    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    @JsonProperty("requestDate")
    public Object getRequestDate() {
        return requestDate;
    }

    @JsonProperty("requestDate")
    public void setRequestDate(Object requestDate) {
        this.requestDate = requestDate;
    }

    @JsonProperty("capitalPeriodicity")
    public Object getCapitalPeriodicity() {
        return capitalPeriodicity;
    }

    @JsonProperty("capitalPeriodicity")
    public void setCapitalPeriodicity(Object capitalPeriodicity) {
        this.capitalPeriodicity = capitalPeriodicity;
    }

    @JsonProperty("interestPeriodicity")
    public InterestPeriodicity getInterestPeriodicity() {
        return interestPeriodicity;
    }

    @JsonProperty("interestPeriodicity")
    public void setInterestPeriodicity(InterestPeriodicity interestPeriodicity) {
        this.interestPeriodicity = interestPeriodicity;
    }

    @JsonProperty("waitingPeriod")
    public Object getWaitingPeriod() {
        return waitingPeriod;
    }

    @JsonProperty("waitingPeriod")
    public void setWaitingPeriod(Object waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    @JsonProperty("conditions")
    public Conditions getConditions() {
        return conditions;
    }

    @JsonProperty("conditions")
    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    @JsonProperty("commercialValueAmount")
    public CommercialValueAmount getCommercialValueAmount() {
        return commercialValueAmount;
    }

    @JsonProperty("commercialValueAmount")
    public void setCommercialValueAmount(CommercialValueAmount commercialValueAmount) {
        this.commercialValueAmount = commercialValueAmount;
    }

    @JsonProperty("indicators")
    public Indicators getIndicators() {
        return indicators;
    }

    @JsonProperty("indicators")
    public void setIndicators(Indicators indicators) {
        this.indicators = indicators;
    }

    @JsonProperty("relatedContracts")
    public List<RelatedContract> getRelatedContracts() {
        return relatedContracts;
    }

    @JsonProperty("relatedContracts")
    public void setRelatedContracts(List<RelatedContract> relatedContracts) {
        this.relatedContracts = relatedContracts;
    }

    @JsonProperty("participants")
    public List<Participant> getParticipants() {
        return participants;
    }

    @JsonProperty("participants")
    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public LoanResult getLoan() {
		return loan;
	}

	public void setLoan(LoanResult loan) {
		this.loan = loan;
	}

    
}
