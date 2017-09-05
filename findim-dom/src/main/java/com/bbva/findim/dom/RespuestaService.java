package com.bbva.findim.dom;

import java.io.Serializable;

public class RespuestaService implements Serializable
	{

		private static final long serialVersionUID = 1L;
		private String exitoCode;
		private String errorCode;
		private String requestId;
		private String errorDescription;
		private String exitoDescription;
		
		public String getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}
		public String getRequestId() {
			return requestId;
		}
		public void setRequestId(String requestId) {
			this.requestId = requestId;
		}
		public String getErrorDescription() {
			return errorDescription;
		}
		public void setErrorDescription(String errorDescription) {
			this.errorDescription = errorDescription;
		}
		public String getExitoCode() {
			return exitoCode;
		}
		public void setExitoCode(String exitoCode) {
			this.exitoCode = exitoCode;
		}
		public String getExitoDescription() {
			return exitoDescription;
		}
		public void setExitoDescription(String exitoDescription) {
			this.exitoDescription = exitoDescription;
		}
		
		
		
		
	}