package com.masteklabs.mysqlredis.bean;

import java.io.Serializable;



public class CreditCardTransaction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String transactionId;
	private String date;
	private Long accountId;
	private String description;
	private double amount;
	private Long posid;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}


	
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getPosid() {
		return posid;
	}
	public void setPosid(Long posid) {
		this.posid = posid;
	}
	@Override
	public String toString() {

		StringBuilder trans=new StringBuilder();
		trans.append(this.getTransactionId());trans.append(',');		
		trans.append(this.getAmount());	trans.append(',');
		trans.append(this.getPosid());trans.append(',');
		trans.append(this.getDate());trans.append(',');
		trans.append(this.getDescription());
		return trans.toString();
	}


}
