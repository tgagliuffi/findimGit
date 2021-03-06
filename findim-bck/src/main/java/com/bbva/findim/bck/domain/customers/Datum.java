package com.bbva.findim.bck.domain.customers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbva.findim.bck.domain.communs.Bank;
import com.bbva.findim.bck.domain.communs.Term;
import com.bbva.findim.bck.domain.loanInput.Indicators;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "customerId",
//    "firstName",
//    "lastName",
//    "surnames",
//    "birthData",
//    "nationalities",
//    "identityDocuments",
//    "membershipDate",
//    "personalTitle",
//    "maritalStatus",
//    "gender",
//    "bank",
//    "residence"
//})
@JsonPropertyOrder({
    "customerId",
    "firstName",
    "lastName",
    "surnames",
    "membershipDate",
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
    "indicators",
    "terms",
    "bank"
})
public class Datum {

    @JsonProperty("customerId")
    private String customerId;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("surnames")
    private String surnames;
    @JsonProperty("birthData")
    private BirthData birthData;
    @JsonProperty("nationalities")
    private List<Nationality> nationalities = null;
    @JsonProperty("identityDocuments")
    private List<IdentityDocument> identityDocuments = null;
    @JsonProperty("membershipDate")
    private String membershipDate;
    @JsonProperty("personalTitle")
    private PersonalTitle personalTitle;
    @JsonProperty("maritalStatus")
    private MaritalStatus maritalStatus;
    @JsonProperty("gender")
    private Gender gender;
    @JsonProperty("bank")
    private Bank bank;
    @JsonProperty("residence")
    private Residence residence;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    
 
    @JsonProperty("contactDetails")
    private List<ContactDetail> contactDetails;
    @JsonProperty("economicData")
    private EconomicData economicData;
    @JsonProperty("addresses")
    private List<Address> addresses;
    @JsonProperty("indicators")
    private List<Indicators> indicators;
    @JsonProperty("terms")
    private List<Term> terms;
 


    @JsonProperty("customerId")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customerId")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

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

    @JsonProperty("birthData")
    public BirthData getBirthData() {
        return birthData;
    }

    @JsonProperty("birthData")
    public void setBirthData(BirthData birthData) {
        this.birthData = birthData;
    }

    @JsonProperty("nationalities")
    public List<Nationality> getNationalities() {
        return nationalities;
    }

    @JsonProperty("nationalities")
    public void setNationalities(List<Nationality> nationalities) {
        this.nationalities = nationalities;
    }

    @JsonProperty("identityDocuments")
    public List<IdentityDocument> getIdentityDocuments() {
        return identityDocuments;
    }

    @JsonProperty("identityDocuments")
    public void setIdentityDocuments(List<IdentityDocument> identityDocuments) {
        this.identityDocuments = identityDocuments;
    }

    @JsonProperty("membershipDate")
    public String getMembershipDate() {
        return membershipDate;
    }

    @JsonProperty("membershipDate")
    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
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

    @JsonProperty("bank")
    public Bank getBank() {
        return bank;
    }

    @JsonProperty("bank")
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @JsonProperty("residence")
    public Residence getResidence() {
        return residence;
    }

    @JsonProperty("residence")
    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	public List<ContactDetail> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(List<ContactDetail> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public EconomicData getEconomicData() {
		return economicData;
	}

	public void setEconomicData(EconomicData economicData) {
		this.economicData = economicData;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Indicators> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<Indicators> indicators) {
		this.indicators = indicators;
	}

	public List<Term> getTerms() {
		return terms;
	}

	public void setTerms(List<Term> terms) {
		this.terms = terms;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

    
}
