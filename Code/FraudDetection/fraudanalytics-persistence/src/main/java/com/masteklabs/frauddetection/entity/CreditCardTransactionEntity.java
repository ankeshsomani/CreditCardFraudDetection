package com.masteklabs.frauddetection.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import com.masteklabs.frauddetection.common.CommonConstants;
import com.masteklabs.frauddetection.common.DateUtils;



public class CreditCardTransactionEntity implements Serializable{
	
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
	private Long cardNumber;
	private Date transactionDate;
	
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


	
	

	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Date getTransactionDate() throws ParseException {
		return DateUtils.getDateFromString("dd/MM/yyyy HH:mm:ss", this.date);
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
		trans.append(this.getTransactionId());trans.append(CommonConstants.TRANSACTION_DELIMETER);		
		trans.append(this.getAmount());	trans.append(CommonConstants.TRANSACTION_DELIMETER);
		trans.append(this.getPosid());trans.append(CommonConstants.TRANSACTION_DELIMETER);
		trans.append(this.getDate());trans.append(CommonConstants.TRANSACTION_DELIMETER);
		trans.append(this.getDescription());
		return trans.toString();
	}


}
