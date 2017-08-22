
package com.bbva.findim.bck.domain.tariff;

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
    "lastPage",
    "firstPage"
})
public class Pagination {

    @JsonProperty("lastPage")
    private String lastPage;
    @JsonProperty("firstPage")
    private String firstPage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("lastPage")
    public String getLastPage() {
        return lastPage;
    }

    @JsonProperty("lastPage")
    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    @JsonProperty("firstPage")
    public String getFirstPage() {
        return firstPage;
    }

    @JsonProperty("firstPage")
    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
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
