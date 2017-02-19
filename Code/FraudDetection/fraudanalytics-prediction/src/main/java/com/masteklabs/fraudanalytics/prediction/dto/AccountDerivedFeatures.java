package com.masteklabs.fraudanalytics.prediction.dto;

import java.io.Serializable;

import com.masteklabs.frauddetection.common.CommonConstants;



public class AccountDerivedFeatures implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
	private Long transactionInLastHour;
	private Long transactionInLastWeek;
	private Long transactionInLastDay;
	private Long transactionInLastMonth;
	
	private Double avgAmountSpendInLastHour;
	private Double avgAmountSpendInLastDay;
	private Double avgAmountSpendInLastWeek;
	private Double avgAmountSpendInLastMonth;
	
	


	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public Long getTransactionInLastHour() {
		return transactionInLastHour;
	}
	public void setTransactionInLastHour(Long transactionInLastHour) {
		this.transactionInLastHour = transactionInLastHour;
	}

	public Long getTransactionInLastWeek() {
		return transactionInLastWeek;
	}
	public void setTransactionInLastWeek(Long transactionInLastWeek) {
		this.transactionInLastWeek = transactionInLastWeek;
	}
	public Long getTransactionInLastDay() {
		return transactionInLastDay;
	}
	public void setTransactionInLastDay(Long transactionInLastDay) {
		this.transactionInLastDay = transactionInLastDay;
	}
	public Long getTransactionInLastMonth() {
		return transactionInLastMonth;
	}
	public void setTransactionInLastMonth(Long transactionInLastMonth) {
		this.transactionInLastMonth = transactionInLastMonth;
	}
	public Double getAvgAmountSpendInLastHour() {
		return avgAmountSpendInLastHour;
	}
	public void setAvgAmountSpendInLastHour(Double avgAmountSpendInLastHour) {
		this.avgAmountSpendInLastHour = avgAmountSpendInLastHour;
	}
	public Double getAvgAmountSpendInLastDay() {
		return avgAmountSpendInLastDay;
	}
	public void setAvgAmountSpendInLastDay(Double avgAmountSpendInLastDay) {
		this.avgAmountSpendInLastDay = avgAmountSpendInLastDay;
	}
	public Double getAvgAmountSpendInLastWeek() {
		return avgAmountSpendInLastWeek;
	}
	public void setAvgAmountSpendInLastWeek(Double avgAmountSpendInLastWeek) {
		this.avgAmountSpendInLastWeek = avgAmountSpendInLastWeek;
	}
	public Double getAvgAmountSpendInLastMonth() {
		return avgAmountSpendInLastMonth;
	}
	public void setAvgAmountSpendInLastMonth(Double avgAmountSpendInLastMonth) {
		this.avgAmountSpendInLastMonth = avgAmountSpendInLastMonth;
	}
	@Override
	public String toString() {
		StringBuilder out=new StringBuilder();
		
		out.append(this.transactionInLastDay);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.avgAmountSpendInLastDay);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.transactionInLastWeek);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.avgAmountSpendInLastWeek);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.transactionInLastMonth);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.avgAmountSpendInLastMonth);
		return out.toString();
	}

}
