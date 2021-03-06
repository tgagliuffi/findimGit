
package com.bbva.findim.bck.domain.proposals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "identityDocuments"
})
public class Holder {

    private List<IdentityDocument> identityDocuments = null;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
//    @JsonIgnore
	 private String name;
	 private String lastName;
	 private String motherLastName;

    public List<IdentityDocument> getIdentityDocuments() {
        return identityDocuments;
    }

    public void setIdentityDocuments(List<IdentityDocument> identityDocuments) {
        this.identityDocuments = identityDocuments;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMotherLastName() {
		return motherLastName;
	}
	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}
    
    

}

//
//package com.bbva.cnxpaasaso.domain.proposals;
//
//import java.util.HashMap;
//import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "name",
//    "lastName",
//    "motherLastName"
//})
//public class Holder {
//
//    @JsonProperty("name")
//    private String name;
//    @JsonProperty("lastName")
//    private String lastName;
//    @JsonProperty("motherLastName")
//    private String motherLastName;
//    @JsonIgnore
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
//
//    @JsonProperty("name")
//    public String getName() {
//        return name;
//    }
//
//    @JsonProperty("name")
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Holder withName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    @JsonProperty("lastName")
//    public String getLastName() {
//        return lastName;
//    }
//
//    @JsonProperty("lastName")
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public Holder withLastName(String lastName) {
//        this.lastName = lastName;
//        return this;
//    }
//
//    @JsonProperty("motherLastName")
//    public String getMotherLastName() {
//        return motherLastName;
//    }
//
//    @JsonProperty("motherLastName")
//    public void setMotherLastName(String motherLastName) {
//        this.motherLastName = motherLastName;
//    }
//
//    public Holder withMotherLastName(String motherLastName) {
//        this.motherLastName = motherLastName;
//        return this;
//    }
//
//    @JsonAnyGetter
//    public Map<String, Object> getAdditionalProperties() {
//        return this.additionalProperties;
//    }
//
//    @JsonAnySetter
//    public void setAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//    }
//
//    public Holder withAdditionalProperty(String name, Object value) {
//        this.additionalProperties.put(name, value);
//        return this;
//    }
//
//}
