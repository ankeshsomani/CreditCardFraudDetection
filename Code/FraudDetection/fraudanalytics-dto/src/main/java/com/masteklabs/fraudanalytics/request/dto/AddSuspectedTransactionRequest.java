package com.masteklabs.fraudanalytics.request.dto;

import java.util.Date;

public class AddSuspectedTransactionRequest {

	private Long accountid;
	
	private String transactionid;
	private Long cardnumber;
	private Double amount;
	private Date createdOn;
	private Date modifiedOn;
	private Integer fraudStatus;
	private String transactionDate;
	public Long getAccountid() {
		return accountid;
	}
	public void setAccountid(Long accountid) {
		this.accountid = accountid;
	}
	public String getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(String transactionid) {
		this.transactionid = transactionid;
	}
	public Long getCardnumber() {
		return cardnumber;
	}
	public void setCardnumber(Long cardnumber) {
		this.cardnumber = cardnumber;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Integer getFraudStatus() {
		return fraudStatus;
	}
	public void setFraudStatus(Integer fraudStatus) {
		this.fraudStatus = fraudStatus;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
}
