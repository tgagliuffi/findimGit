
package com.bbva.findim.bck.domain.customers;

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
    "addressType",
    "ownershipType",
    "location"
})
public class Address {

	@JsonProperty("addressId")
    private String addressId;
	
    @JsonProperty("addressType")
    private AddressType addressType;
    @JsonProperty("ownershipType")
    private OwnershipType ownershipType;
    @JsonProperty("location")
    private Location location;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("addressType")
    public AddressType getAddressType() {
        return addressType;
    }

    @JsonProperty("addressType")
    public void setAddressType(AddressType addressType) {
        this.addressType = addressType;
    }

    @JsonProperty("ownershipType")
    public OwnershipType getOwnershipType() {
        return ownershipType;
    }

    @JsonProperty("ownershipType")
    public void setOwnershipType(OwnershipType ownershipType) {
        this.ownershipType = ownershipType;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
    
}
