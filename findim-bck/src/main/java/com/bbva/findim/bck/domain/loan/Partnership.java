
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
    "employeeCode",
    "managerCode"
})
public class Partnership {

    @JsonProperty("employeeCode")
    private String employeeCode;
    @JsonProperty("managerCode")
    private String managerCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("employeeCode")
    public String getEmployeeCode() {
        return employeeCode;
    }

    @JsonProperty("employeeCode")
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    @JsonProperty("managerCode")
    public String getManagerCode() {
        return managerCode;
    }

    @JsonProperty("managerCode")
    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
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
