package com.bbva.findim.bck.domain.gt;

import java.util.List;

import com.bbva.findim.bck.domain.communs.BaseDomain;

public class AuthenticationResponse extends BaseDomain {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private String authenticationState;
//	public String getAuthenticationState() { return authenticationState; }
//	public void setAuthenticationState(String authenticationState) { this.authenticationState = authenticationState; }
//	
//	private List<Object> authenticationData;
//	public List<Object> getAuthenticationData() { return authenticationData; }
//	public void setAuthenticationData(List<Object> authenticationData) { this.authenticationData = authenticationData; }

	 	private List<String> authenticationData;
	    private String authenticationState;

	    public List<String> getAuthenticationData ()
	    {
	        return authenticationData;
	    }

	    public void setAuthenticationData (List<String> authenticationData)
	    {
	        this.authenticationData = authenticationData;
	    }

	    public String getAuthenticationState ()
	    {
	        return authenticationState;
	    }

	    public void setAuthenticationState (String authenticationState)
	    {
	        this.authenticationState = authenticationState;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [authenticationData = "+authenticationData+", authenticationState = "+authenticationState+"]";
	    }
}
