package com.masteklabs.frauddetection.entity;

import java.io.Serializable;

import com.masteklabs.frauddetection.common.CommonConstants;

public class AccountInfoEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accountId;
	private String name;
	private Double age;
	private String gender;
	private Long cardNumber;
	private String cardType;
	private Double annualIncome;
	private String emailId;
	private String phoneNumber;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public Double getAge() {
		return age;
	}
	public void setAge(Double age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(Double annualIncome) {
		this.annualIncome = annualIncome;
	}
	@Override
	public String toString() {
		StringBuilder out=new StringBuilder();
		out.append(this.accountId);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.name);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.age);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.gender);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.cardNumber);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.cardType);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.annualIncome);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.emailId);out.append(CommonConstants.TRANSACTION_DELIMETER);
		out.append(this.phoneNumber);
		return out.toString();
	}
	

}
