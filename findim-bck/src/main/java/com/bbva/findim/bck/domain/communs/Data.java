
package com.bbva.findim.bck.domain.communs;

import java.util.HashMap;
import java.util.Map;

import com.bbva.findim.bck.domain.creditApprovals.Qualification;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "qualification"
})
public class Data {

    @JsonProperty("qualification")
    private Qualification qualification;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("qualification")
    public Qualification getQualification() {
        return qualification;
    }

    @JsonProperty("qualification")
    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
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
