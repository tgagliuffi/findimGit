package com.bbva.findim.bck.domain.creditApprovals;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditApprovalsResult {
	
	@JsonProperty("data")
    private List<CreditApprovals> data;

	public List<CreditApprovals> getData() {
		return data;
	}

	public void setData(List<CreditApprovals> data) {
		this.data = data;
	}
	
	
	

}
