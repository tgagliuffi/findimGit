package com.bbva.findim.bck.domain.gt;

import com.bbva.findim.bck.domain.communs.BaseDomain;

public class GrantingTicketResponse extends BaseDomain  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AuthenticationResponse authenticationResponse;
	public AuthenticationResponse getAuthenticationResponse() { return authenticationResponse; }
	public void setAuthenticationResponse(AuthenticationResponse authenticationResponse) { this.authenticationResponse = authenticationResponse; }

}
