package com.masteklabs.fraudanalytics.response.dto;

public class ServiceResponse {

	private boolean success;
	private String errorMessage;
	
	public ServiceResponse(boolean success,String errorMessage){
		this.success=success;
		this.errorMessage=errorMessage;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
