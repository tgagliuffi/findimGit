
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
    "percentage",
    "relatedProductType"
})
public class RelatedProduct {

    @JsonProperty("percentage")
    private Double percentage;
    @JsonProperty("relatedProductType")
    private RelatedProductType relatedProductType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("percentage")
    public Double getPercentage() {
        return percentage;
    }

    @JsonProperty("percentage")
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public RelatedProduct withPercentage(Double percentage) {
        this.percentage = percentage;
        return this;
    }

    @JsonProperty("relatedProductType")
    public RelatedProductType getRelatedProductType() {
        return relatedProductType;
    }

    @JsonProperty("relatedProductType")
    public void setRelatedProductType(RelatedProductType relatedProductType) {
        this.relatedProductType = relatedProductType;
    }

    public RelatedProduct withRelatedProductType(RelatedProductType relatedProductType) {
        this.relatedProductType = relatedProductType;
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

    public RelatedProduct withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
