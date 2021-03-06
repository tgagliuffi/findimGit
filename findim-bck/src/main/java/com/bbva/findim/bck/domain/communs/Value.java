
package com.bbva.findim.bck.domain.communs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bbva.findim.bck.domain.catalogs.Description;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "descriptions"
})
public class Value {

	@JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("value")
    private String value;
    @JsonProperty("isSelected")
    private Boolean isSelected;
    @JsonProperty("descriptions")
    private List<Description> descriptions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    public Value() {}

	public Value(String id) {
		super();
		this.id = id;
	}    

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("name")
	public String getName() {
		return name;
	}

    @JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
    
    /**
     * 
     * @return
     *     The value
     */
    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 
     * @return
     *     The isSelected
     */
    @JsonProperty("isSelected")
    public Boolean getIsSelected() {
        return isSelected;
    }

    /**
     * 
     * @param isSelected
     *     The isSelected
     */
    @JsonProperty("isSelected")
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    /**
     * 
     * @return
     *     The descriptions
     */
    @JsonProperty("descriptions")
    public List<Description> getDescriptions() {
        return descriptions;
    }

    /**
     * 
     * @param descriptions
     *     The descriptions
     */
    @JsonProperty("descriptions")
    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }
}
