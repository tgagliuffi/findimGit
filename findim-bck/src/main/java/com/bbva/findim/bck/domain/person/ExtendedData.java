
package com.bbva.findim.bck.domain.person;

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
    "sex",
    "maritalStatus"
})
public class ExtendedData {

    @JsonProperty("sex")
    private String sex;
    @JsonProperty("maritalStatus")
    private MaritalStatus maritalStatus;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("sex")
    public String getSex() {
        return sex;
    }

    @JsonProperty("sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonProperty("maritalStatus")
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    @JsonProperty("maritalStatus")
    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
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
