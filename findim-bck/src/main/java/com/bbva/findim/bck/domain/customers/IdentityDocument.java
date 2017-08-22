
package com.bbva.findim.bck.domain.customers;

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
    "documentType",
    "country",
    "status",
    "expirationDate",
    "documentNumber"
})
public class IdentityDocument {

    @JsonProperty("documentType")
    private DocumentType documentType;
    @JsonProperty("country")
    private Country country;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("expirationDate")
    private String expirationDate;
    @JsonProperty("documentNumber")
    private String documentNumber;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("documentType")
    public DocumentType getDocumentType() {
        return documentType;
    }

    @JsonProperty("documentType")
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    @JsonProperty("country")
    public Country getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(Country country) {
        this.country = country;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("expirationDate")
    public String getExpirationDate() {
        return expirationDate;
    }

    @JsonProperty("expirationDate")
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @JsonProperty("documentNumber")
    public String getDocumentNumber() {
        return documentNumber;
    }

    @JsonProperty("documentNumber")
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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
