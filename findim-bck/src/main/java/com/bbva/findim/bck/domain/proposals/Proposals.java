
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
    "holder",
    "tariff",
    "currency",
    "initialAmount",
    "billingDay",
    "paymentDay",
    "delivery",
    "operation",
    "externalProduct",
    "thirdPartyProvider",
    "branch"
})
public class Proposals {

    @JsonProperty("holder")
    private Holder holder;
    @JsonProperty("tariff")
    private Tariff tariff;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("initialAmount")
    private InitialAmount initialAmount;
    @JsonProperty("billingDay")
    private Integer billingDay;
    @JsonProperty("paymentDay")
    private Integer paymentDay;
    @JsonProperty("delivery")
    private Delivery delivery;
    @JsonProperty("operation")
    private Operation operation;
    @JsonProperty("externalProduct")
    private ExternalProduct externalProduct;
    @JsonProperty("thirdPartyProvider")
    private ThirdPartyProvider thirdPartyProvider;
    @JsonProperty("branch")
    private Branch branch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("holder")
    public Holder getHolder() {
        return holder;
    }

    @JsonProperty("holder")
    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    @JsonProperty("tariff")
    public Tariff getTariff() {
        return tariff;
    }

    @JsonProperty("tariff")
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonProperty("initialAmount")
    public InitialAmount getInitialAmount() {
        return initialAmount;
    }

    @JsonProperty("initialAmount")
    public void setInitialAmount(InitialAmount initialAmount) {
        this.initialAmount = initialAmount;
    }

    @JsonProperty("billingDay")
    public Integer getBillingDay() {
        return billingDay;
    }

    @JsonProperty("billingDay")
    public void setBillingDay(Integer billingDay) {
        this.billingDay = billingDay;
    }

    @JsonProperty("paymentDay")
    public Integer getPaymentDay() {
        return paymentDay;
    }

    @JsonProperty("paymentDay")
    public void setPaymentDay(Integer paymentDay) {
        this.paymentDay = paymentDay;
    }

    @JsonProperty("delivery")
    public Delivery getDelivery() {
        return delivery;
    }

    @JsonProperty("delivery")
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    @JsonProperty("operation")
    public Operation getOperation() {
        return operation;
    }

    @JsonProperty("operation")
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    @JsonProperty("externalProduct")
    public ExternalProduct getExternalProduct() {
        return externalProduct;
    }

    @JsonProperty("externalProduct")
    public void setExternalProduct(ExternalProduct externalProduct) {
        this.externalProduct = externalProduct;
    }

    @JsonProperty("thirdPartyProvider")
    public ThirdPartyProvider getThirdPartyProvider() {
        return thirdPartyProvider;
    }

    @JsonProperty("thirdPartyProvider")
    public void setThirdPartyProvider(ThirdPartyProvider thirdPartyProvider) {
        this.thirdPartyProvider = thirdPartyProvider;
    }

    @JsonProperty("branch")
    public Branch getBranch() {
        return branch;
    }

    @JsonProperty("branch")
    public void setBranch(Branch branch) {
        this.branch = branch;
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
