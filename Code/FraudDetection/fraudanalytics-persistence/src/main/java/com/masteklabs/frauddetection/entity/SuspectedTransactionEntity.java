package com.masteklabs.frauddetection.entity;

import java.util.Date;

public class SuspectedTransactionEntity extends CreditCardTransactionEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer fraudStatus;
	
	private Date createdOn;
	private Date modifiedOn;
	//private Double amount;
	//private Long cardNumber; 
	public Integer getFraudStatus() {
		return fraudStatus;
	}
	public void setFraudStatus(Integer fraudStatus) {
		this.fraudStatus = fraudStatus;
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
	
	// public double getAmount() {
		// return super.getAmount();
	// }
	// public void setAmount(double amount) {
		// super.setAmount(amount);
	// }
	// public double getCardNumber() {
		// return super.getCardNumber();
	// }
	// public void setCardNumber( Long cardNumber) {
		// super.setCardNumber(cardNumber);
	// }
	
}
