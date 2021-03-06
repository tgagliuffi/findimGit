
package com.bbva.findim.bck.domain.proposals;

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
    "nextPage",
    "lastPage",
    "firstPage"
})
public class Pagination {

    @JsonProperty("nextPage")
    private String nextPage;
    @JsonProperty("lastPage")
    private String lastPage;
    @JsonProperty("firstPage")
    private String firstPage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("nextPage")
    public String getNextPage() {
        return nextPage;
    }

    @JsonProperty("nextPage")
    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public Pagination withNextPage(String nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    @JsonProperty("lastPage")
    public String getLastPage() {
        return lastPage;
    }

    @JsonProperty("lastPage")
    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    public Pagination withLastPage(String lastPage) {
        this.lastPage = lastPage;
        return this;
    }

    @JsonProperty("firstPage")
    public String getFirstPage() {
        return firstPage;
    }

    @JsonProperty("firstPage")
    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    public Pagination withFirstPage(String firstPage) {
        this.firstPage = firstPage;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Pagination withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
