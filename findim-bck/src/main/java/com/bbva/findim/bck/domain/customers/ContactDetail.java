
package com.bbva.findim.bck.domain.customers;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "contact",
//    "contactType",
//    "country"
//})
@JsonIgnoreProperties(ignoreUnknown=true)
public class ContactDetail {

//    @JsonProperty("contact")
    private String contact;
//    @JsonProperty("contactType")
    private ContactType contactType;
//    @JsonProperty("country")
    private Country country;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

//    @JsonProperty("contact")
    public String getContact() {
        return contact;
    }

//    @JsonProperty("contact")
    public void setContact(String contact) {
        this.contact = contact;
    }

//    @JsonProperty("contactType")
    public ContactType getContactType() {
        return contactType;
    }

//    @JsonProperty("contactType")
    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

//    @JsonProperty("country")
    public Country getCountry() {
        return country;
    }

//    @JsonProperty("country")
    public void setCountry(Country country) {
        this.country = country;
    }

//    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

//    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
