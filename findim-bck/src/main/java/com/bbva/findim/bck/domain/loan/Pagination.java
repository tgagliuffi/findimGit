
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
    "firstPage",
    "nextPage",
    "lastPage"
})
public class Pagination {

    @JsonProperty("firstPage")
    private String firstPage;
    @JsonProperty("nextPage")
    private String nextPage;
    @JsonProperty("lastPage")
    private String lastPage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("firstPage")
    public String getFirstPage() {
        return firstPage;
    }

    @JsonProperty("firstPage")
    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    @JsonProperty("nextPage")
    public String getNextPage() {
        return nextPage;
    }

    @JsonProperty("nextPage")
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    @JsonProperty("lastPage")
    public String getLastPage() {
        return lastPage;
    }

    @JsonProperty("lastPage")
    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
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
