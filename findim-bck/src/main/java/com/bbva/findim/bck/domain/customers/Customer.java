
package com.bbva.findim.bck.domain.customers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbva.findim.bck.domain.communs.Term;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "firstName",
    "lastName",
    "surnames",
    "identityDocuments",
    "birthData",
    "personalTitle",
    "maritalStatus",
    "gender",
    "nationalities",
    "residence",
    "contactDetails",
    "economicData",
    "addresses",
    "terms"
})
public class Customer {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("surnames")
    private String surnames;
    @JsonProperty("identityDocuments")
    private List<IdentityDocument> identityDocuments = null;
    @JsonProperty("birthData")
    private BirthData birthData;
    @JsonProperty("personalTitle")
    private PersonalTitle personalTitle;
    @JsonProperty("maritalStatus")
    private MaritalStatus maritalStatus;
    @JsonProperty("gender")
    private Gender gender;
    @JsonProperty("nationalities")
    private List<Nationality> nationalities = null;
    @JsonProperty("residence")
    private Residence residence;
    @JsonProperty("contactDetails")
    private List<ContactDetail> contactDetails = null;
    @JsonProperty("economicData")
    private EconomicData economicData;
    @JsonProperty("addresses")
    private List<Address> addresses = null;
    @JsonProperty("terms")
    private List<Term> terms = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("surnames")
    public String getSurnames() {
        return surnames;
    }

    @JsonProperty("surnames")
    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    @JsonProperty("identityDocuments")
    public List<IdentityDocument> getIdentityDocuments() {
        return identityDocuments;
    }

    @JsonProperty("identityDocuments")
    public void setIdentityDocuments(List<IdentityDocument> identityDocuments) {
        this.identityDocuments = identityDocuments;
    }

    @JsonProperty("birthData")
    public BirthData getBirthData() {
        return birthData;
    }

    @JsonProperty("birthData")
    public void setBirthData(BirthData birthData) {
        this.birthData = birthData;
    }

    @JsonProperty("personalTitle")
    public PersonalTitle getPersonalTitle() {
        return personalTitle;
    }

    @JsonProperty("personalTitle")
    public void setPersonalTitle(PersonalTitle personalTitle) {
        this.personalTitle = personalTitle;
    }

    @JsonProperty("maritalStatus")
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    @JsonProperty("maritalStatus")
    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @JsonProperty("gender")
    public Gender getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @JsonProperty("nationalities")
    public List<Nationality> getNationalities() {
        return nationalities;
    }

    @JsonProperty("nationalities")
    public void setNationalities(List<Nationality> nationalities) {
        this.nationalities = nationalities;
    }

    @JsonProperty("residence")
    public Residence getResidence() {
        return residence;
    }

    @JsonProperty("residence")
    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    @JsonProperty("contactDetails")
    public List<ContactDetail> getContactDetails() {
        return contactDetails;
    }

    @JsonProperty("contactDetails")
    public void setContactDetails(List<ContactDetail> contactDetails) {
        this.contactDetails = contactDetails;
    }

    @JsonProperty("economicData")
    public EconomicData getEconomicData() {
        return economicData;
    }

    @JsonProperty("economicData")
    public void setEconomicData(EconomicData economicData) {
        this.economicData = economicData;
    }

    @JsonProperty("addresses")
    public List<Address> getAddresses() {
        return addresses;
    }

    @JsonProperty("addresses")
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @JsonProperty("terms")
    public List<Term> getTerms() {
        return terms;
    }

    @JsonProperty("terms")
    public void setTerms(List<Term> terms) {
        this.terms = terms;
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
