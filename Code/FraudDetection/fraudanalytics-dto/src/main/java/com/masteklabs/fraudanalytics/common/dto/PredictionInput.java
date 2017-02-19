package com.masteklabs.fraudanalytics.common.dto;

import java.io.Serializable;

public class PredictionInput implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transactionId;
	private Long accountId;
	private Double age;
	private Double gender;
	private Double cardNumber;
	private Double cardType;
	private Double locationCategory;
	private Double amount;
	private Double posid;
	private Double avgTransactionAmtLastDay;
	private Long  transactionTime;
	private Double avgTransactionAmtLastWeek;
	private Double avgTransactionAmtLastMonth;
	public Long getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Long transactionTime) {
		this.transactionTime = transactionTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Double countTransactionsLastDay;
	private Double countTransactionsLastWeek;
	private Double countTransactionsLastMonth;
	private Double annualIncome;
	public Double getAge() {
		return age;
	}
	public void setAge(Double age) {
		this.age = age;
	}
	public Double getGender() {
		return gender;
	}
	public void setGender(Double gender) {
		this.gender = gender;
	}
	public Double getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Double cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Double getCardType() {
		return cardType;
	}
	public void setCardType(Double cardType) {
		this.cardType = cardType;
	}
	public Double getLocationCategory() {
		return locationCategory;
	}
	public void setLocationCategory(Double locationCategory) {
		this.locationCategory = locationCategory;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getPosid() {
		return posid;
	}
	public void setPosid(Double posid) {
		this.posid = posid;
	}
	public Double getAvgTransactionAmtLastDay() {
		return avgTransactionAmtLastDay;
	}
	public void setAvgTransactionAmtLastDay(Double avgTransactionAmtLastDay) {
		this.avgTransactionAmtLastDay = avgTransactionAmtLastDay;
	}
	public Double getAvgTransactionAmtLastWeek() {
		return avgTransactionAmtLastWeek;
	}
	public void setAvgTransactionAmtLastWeek(Double avgTransactionAmtLastWeek) {
		this.avgTransactionAmtLastWeek = avgTransactionAmtLastWeek;
	}
	public Double getAvgTransactionAmtLastMonth() {
		return avgTransactionAmtLastMonth;
	}
	public void setAvgTransactionAmtLastMonth(Double avgTransactionAmtLastMonth) {
		this.avgTransactionAmtLastMonth = avgTransactionAmtLastMonth;
	}
	public Double getCountTransactionsLastDay() {
		return countTransactionsLastDay;
	}
	public void setCountTransactionsLastDay(Double countTransactionsLastDay) {
		this.countTransactionsLastDay = countTransactionsLastDay;
	}
	public Double getCountTransactionsLastWeek() {
		return countTransactionsLastWeek;
	}
	public void setCountTransactionsLastWeek(Double countTransactionsLastWeek) {
		this.countTransactionsLastWeek = countTransactionsLastWeek;
	}
	public Double getCountTransactionsLastMonth() {
		return countTransactionsLastMonth;
	}
	public void setCountTransactionsLastMonth(Double countTransactionsLastMonth) {
		this.countTransactionsLastMonth = countTransactionsLastMonth;
	}
	public Double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(Double annualIncome) {
		this.annualIncome = annualIncome;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	
	

}
