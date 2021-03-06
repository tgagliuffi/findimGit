package com.bbva.findim.bck.domain.communs;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ErrorService implements Serializable
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@SerializedName("version")
		@Expose
		private Integer version;
		
		@SerializedName("severity")
		@Expose
		private String severity;
		
		@SerializedName("httpStatus")
		@Expose
		private Integer httpStatus;
		
		@SerializedName("errorCode")
		@Expose
		private String errorCode;
		
		@SerializedName("errorMessage")
		@Expose
		private String errorMessage;
		
		@SerializedName("consumerRequestId")
		@Expose
		private String consumerRequestId;
		
		@SerializedName("systemErrorCode")
		@Expose
		private String systemErrorCode;
		
		@SerializedName("systemErrorDescription")
		@Expose
		private String systemErrorDescription;
		
		@SerializedName("system-error-cause")
		@Expose
		private String 	systemErrorCause;
	

		public Integer getVersion() {
		return version;
		}
		
		public void setVersion(Integer version) {
		this.version = version;
		}
		
		public String getSeverity() {
		return severity;
		}

		public void setSeverity(String severity) {
		this.severity = severity;
		}
		
		public Integer getHttpStatus() {
		return httpStatus;
		}
		
		public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
		}

		public String getErrorCode() {
		return errorCode;
		}

		public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
		}

		public String getErrorMessage() {
		return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		}

		public String getConsumerRequestId() {
		return consumerRequestId;
		}

		public void setConsumerRequestId(String consumerRequestId) {
		this.consumerRequestId = consumerRequestId;
		}

		public String getSystemErrorCode() {
		return systemErrorCode;
		}

		public void setSystemErrorCode(String systemErrorCode) {
		this.systemErrorCode = systemErrorCode;
		}

		public String getSystemErrorDescription() {
		return systemErrorDescription;
		}

		public void setSystemErrorDescription(String systemErrorDescription) {
		this.systemErrorDescription = systemErrorDescription;
		}
		
		public String getSystemErrorCause() {
			return systemErrorCause;
		}

		public void setSystemErrorCause(String systemErrorCause) {
			this.systemErrorCause = systemErrorCause;
		}

		@Override
		public String toString() {
			return "ErrorService {version=" + version +
							   ", severity=" + severity + 
							   ", http-status=" + httpStatus + 
							   ", error-code=" + errorCode + 
							   ", error-message=" + errorMessage +
							   ", consumer-request-id=" + consumerRequestId + 
							   ", system-error-code=" + systemErrorCode +
							   ", system-error-description=" + systemErrorDescription +  "}";
		}
		
		public String toString(String cadena){
			cadena=cadena.replaceAll("http-status", "httpStatus");
			cadena=cadena.replaceAll("system-error-code", "systemErrorCode");
			cadena=cadena.replaceAll("error-code", "errorCode");
			cadena=cadena.replaceAll("error-message", "errorMessage");
			cadena=cadena.replaceAll("consumer-request-id", "consumerRequestId");
			cadena=cadena.replaceAll("system-error-description", "systemErrorDescription");
			cadena=cadena.replaceAll("system-error-cause", "systemErrorCause");
			return cadena;
		}

	}