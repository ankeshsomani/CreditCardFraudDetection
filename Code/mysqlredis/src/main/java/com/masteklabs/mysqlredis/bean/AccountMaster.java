package com.masteklabs.mysqlredis.bean;

import java.io.Serializable;
import java.util.Date;

public class AccountMaster implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
	private String cardType;
	private Long cardNumber;
	private Long customerId;
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@Override
	public String toString() {
		StringBuilder out=new StringBuilder();
		out.append(this.accountId);out.append(',');
		out.append(this.customerId);out.append(',');
		out.append(this.cardType);out.append(',');
		out.append(this.cardNumber);
		return out.toString();
	}


}
