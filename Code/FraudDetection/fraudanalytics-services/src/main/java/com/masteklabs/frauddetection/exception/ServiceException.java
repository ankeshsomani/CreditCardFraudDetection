package com.masteklabs.frauddetection.exception;

public class ServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	
	private Exception exception;
	
	public ServiceException(String message,Exception e){
		this.message=message;
		this.exception=e;
	}
	public ServiceException(String message){
		this.message=message;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public Exception getException() {
		return exception;
	}
	public void setException(Exception e) {
		this.exception = e;
	}
	

}
