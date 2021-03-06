
package com.bbva.findim.bck.domain.proposals;

import java.util.HashMap;
import java.util.Map;

import com.bbva.findim.bck.domain.communs.Status;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "requestDate",
    "currency",
    "initialFee",
    "initialAmount",
    "tariff",
    "relatedProduct",
    "externalProduct",
    "holder",
    "operation",
    "delivery",
    "status"
})
public class Datum {

    @JsonProperty("id")
    private String id;
    @JsonProperty("requestDate")
    private String requestDate;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("initialFee")
    private InitialFee initialFee;
    @JsonProperty("initialAmount")
    private InitialAmount initialAmount;
    @JsonProperty("tariff")
    private Tariff tariff;
    @JsonProperty("relatedProduct")
    private RelatedProduct relatedProduct;
    @JsonProperty("externalProduct")
    private ExternalProduct externalProduct;
    @JsonProperty("holder")
    private Holder holder;
    @JsonProperty("operation")
    private Operation operation;
    @JsonProperty("delivery")
    private Delivery delivery;
    @JsonProperty("status")
    private Status status;
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

    public Datum withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("requestDate")
    public String getRequestDate() {
        return requestDate;
    }

    @JsonProperty("requestDate")
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Datum withRequestDate(String requestDate) {
        this.requestDate = requestDate;
        return this;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Datum withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    @JsonProperty("initialFee")
    public InitialFee getInitialFee() {
        return initialFee;
    }

    @JsonProperty("initialFee")
    public void setInitialFee(InitialFee initialFee) {
        this.initialFee = initialFee;
    }

    public Datum withInitialFee(InitialFee initialFee) {
        this.initialFee = initialFee;
        return this;
    }

    @JsonProperty("initialAmount")
    public InitialAmount getInitialAmount() {
        return initialAmount;
    }

    @JsonProperty("initialAmount")
    public void setInitialAmount(InitialAmount initialAmount) {
        this.initialAmount = initialAmount;
    }

    public Datum withInitialAmount(InitialAmount initialAmount) {
        this.initialAmount = initialAmount;
        return this;
    }

    @JsonProperty("tariff")
    public Tariff getTariff() {
        return tariff;
    }

    @JsonProperty("tariff")
    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Datum withTariff(Tariff tariff) {
        this.tariff = tariff;
        return this;
    }

    @JsonProperty("relatedProduct")
    public RelatedProduct getRelatedProduct() {
        return relatedProduct;
    }

    @JsonProperty("relatedProduct")
    public void setRelatedProduct(RelatedProduct relatedProduct) {
        this.relatedProduct = relatedProduct;
    }

    public Datum withRelatedProduct(RelatedProduct relatedProduct) {
        this.relatedProduct = relatedProduct;
        return this;
    }

    @JsonProperty("externalProduct")
    public ExternalProduct getExternalProduct() {
        return externalProduct;
    }

    @JsonProperty("externalProduct")
    public void setExternalProduct(ExternalProduct externalProduct) {
        this.externalProduct = externalProduct;
    }

    public Datum withExternalProduct(ExternalProduct externalProduct) {
        this.externalProduct = externalProduct;
        return this;
    }

    @JsonProperty("holder")
    public Holder getHolder() {
        return holder;
    }

    @JsonProperty("holder")
    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public Datum withHolder(Holder holder) {
        this.holder = holder;
        return this;
    }

    @JsonProperty("operation")
    public Operation getOperation() {
        return operation;
    }

    @JsonProperty("operation")
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Datum withOperation(Operation operation) {
        this.operation = operation;
        return this;
    }

    @JsonProperty("delivery")
    public Delivery getDelivery() {
        return delivery;
    }

    @JsonProperty("delivery")
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Datum withDelivery(Delivery delivery) {
        this.delivery = delivery;
        return this;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    public Datum withStatus(Status status) {
        this.status = status;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Datum withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
