package com.masteklabs.mysqlredis.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomerMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long customerId;
	private String name;
	private String address;
	private String gender;
	private String emailId;
	private String phoneNumber;
	private Double annualIncome;
	private Date dob;
	private List<AccountMaster> accounts;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(Double annualIncome) {
		this.annualIncome = annualIncome;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
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
	public List<AccountMaster> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountMaster> accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		StringBuilder out=new StringBuilder();
		out.append(this.getCustomerId());out.append(",");
		out.append(this.getAddress());out.append(",");
		out.append(this.getGender());out.append(",");
		out.append(this.getName());out.append(",");
		out.append(this.getAnnualIncome());;out.append(",");
		out.append(convertDateTOString(this.getDob(),"yyyy-MM-dd"));;out.append(",");
		out.append(this.getEmailId());;out.append(",");
		out.append(this.getPhoneNumber());;out.append(",");
		out.append("accounts are as");
		for(AccountMaster account:this.getAccounts()){
			out.append(account.getAccountId());out.append(',');
			out.append(account.getCustomerId());out.append(',');
			out.append(account.getCardType());out.append(',');
			out.append(account.getCardNumber());
		}
		return out.toString();
	}
	
	public String convertDateTOString(Date dt,String dateFormat){
		String DATE_FORMAT_NOW = dateFormat;
		 SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		
		String stringDate = sdf.format(dt );
		return stringDate;
	}
}
