package com.masteklabs.fraudanalytics.common.dto;

import java.io.Serializable;

import com.masteklabs.frauddetection.common.CommonConstants;

public class PredictionResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uniqueIdentifier;
	private String prediction;
	private Long accountId;
	private Long transactionTime;
	private Double amount;
	private Long cardNumber;

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public String getPrediction() {
		return prediction;
	}

	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}

	public Long getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Long transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(this.uniqueIdentifier);
		out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.getPrediction());
		out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.getAccountId());
		return out.toString();
	}

}
