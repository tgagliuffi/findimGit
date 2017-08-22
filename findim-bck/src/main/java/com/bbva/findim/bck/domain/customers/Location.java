
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
    "addressName",
    "country",
    "additionalInformation",
    "geographicGroups"
})
public class Location {

    @JsonProperty("addressName")
    private String addressName;
    @JsonProperty("country")
    private Country country;
    @JsonProperty("additionalInformation")
    private String additionalInformation;
    @JsonProperty("geographicGroups")
    private List<GeographicGroup> geographicGroups = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("addressName")
    public String getAddressName() {
        return addressName;
    }

    @JsonProperty("addressName")
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @JsonProperty("country")
    public Country getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(Country country) {
        this.country = country;
    }

    @JsonProperty("additionalInformation")
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    @JsonProperty("additionalInformation")
    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @JsonProperty("geographicGroups")
    public List<GeographicGroup> getGeographicGroups() {
        return geographicGroups;
    }

    @JsonProperty("geographicGroups")
    public void setGeographicGroups(List<GeographicGroup> geographicGroups) {
        this.geographicGroups = geographicGroups;
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
