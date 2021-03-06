
package com.bbva.findim.bck.domain.loan;

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
    "capitalWaitingPeriod",
    "interestWaitingPeriod"
})
public class WaitingPeriod {

    @JsonProperty("capitalWaitingPeriod")
    private CapitalWaitingPeriod capitalWaitingPeriod;
    @JsonProperty("interestWaitingPeriod")
    private InterestWaitingPeriod interestWaitingPeriod;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("capitalWaitingPeriod")
    public CapitalWaitingPeriod getCapitalWaitingPeriod() {
        return capitalWaitingPeriod;
    }

    @JsonProperty("capitalWaitingPeriod")
    public void setCapitalWaitingPeriod(CapitalWaitingPeriod capitalWaitingPeriod) {
        this.capitalWaitingPeriod = capitalWaitingPeriod;
    }

    @JsonProperty("interestWaitingPeriod")
    public InterestWaitingPeriod getInterestWaitingPeriod() {
        return interestWaitingPeriod;
    }

    @JsonProperty("interestWaitingPeriod")
    public void setInterestWaitingPeriod(InterestWaitingPeriod interestWaitingPeriod) {
        this.interestWaitingPeriod = interestWaitingPeriod;
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
