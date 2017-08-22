
package com.bbva.findim.bck.domain.person;

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
    "id",
    "name",
    "lastName",
    "mothersLastName",
    "birthday",
    "addresses",
    "contactsInformation",
    "extendedData"
})
public class Person {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("mothersLastName")
    private String mothersLastName;
    @JsonProperty("birthday")
    private String birthday;
    @JsonProperty("addresses")
    private List<Address> addresses = null;
    @JsonProperty("contactsInformation")
    private List<ContactsInformation> contactsInformation = null;
    @JsonProperty("extendedData")
    private ExtendedData extendedData;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("mothersLastName")
    public String getMothersLastName() {
        return mothersLastName;
    }

    @JsonProperty("mothersLastName")
    public void setMothersLastName(String mothersLastName) {
        this.mothersLastName = mothersLastName;
    }

    @JsonProperty("birthday")
    public String getBirthday() {
        return birthday;
    }

    @JsonProperty("birthday")
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @JsonProperty("addresses")
    public List<Address> getAddresses() {
        return addresses;
    }

    @JsonProperty("addresses")
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @JsonProperty("contactsInformation")
    public List<ContactsInformation> getContactsInformation() {
        return contactsInformation;
    }

    @JsonProperty("contactsInformation")
    public void setContactsInformation(List<ContactsInformation> contactsInformation) {
        this.contactsInformation = contactsInformation;
    }

    @JsonProperty("extendedData")
    public ExtendedData getExtendedData() {
        return extendedData;
    }

    @JsonProperty("extendedData")
    public void setExtendedData(ExtendedData extendedData) {
        this.extendedData = extendedData;
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
