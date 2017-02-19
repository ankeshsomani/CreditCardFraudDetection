package com.masteklabs.fraudanalytics.request.dto;

public class ProcessCustomerResponseRequest {
	
	
	private String transactionId;
	private Long accountId;
	private Integer customerResponse;
	
	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getCustomerResponse() {
		return customerResponse;
	}
	public void setCustomerResponse(Integer customerResponse) {
		this.customerResponse = customerResponse;
	}

	
	
}
