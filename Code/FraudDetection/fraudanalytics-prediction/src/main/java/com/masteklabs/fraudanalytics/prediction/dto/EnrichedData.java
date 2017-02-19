package com.masteklabs.fraudanalytics.prediction.dto;

import java.io.Serializable;

import com.masteklabs.frauddetection.common.CommonConstants;
import com.masteklabs.frauddetection.entity.AccountInfoEntity;
import com.masteklabs.frauddetection.entity.CreditCardTransactionEntity;



	
public class EnrichedData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountInfoEntity accountInfo;
	private AccountDerivedFeatures accountModelFeatures;
	private CreditCardTransactionEntity creditCardTransaction;
	public AccountDerivedFeatures getAccountModelFeatures() {
		return accountModelFeatures;
	}
	public void setAccountModelFeatures(AccountDerivedFeatures accountModelFeatures) {
		this.accountModelFeatures = accountModelFeatures;
	}
	public CreditCardTransactionEntity getCreditCardTransaction() {
		return creditCardTransaction;
	}
	public void setCreditCardTransaction(CreditCardTransactionEntity creditCardTransaction) {
		this.creditCardTransaction = creditCardTransaction;
	}
	
	public AccountInfoEntity getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(AccountInfoEntity accountInfo) {
		this.accountInfo = accountInfo;
	}
	@Override
	public String toString() {
		StringBuilder out=new StringBuilder();
		//out.append("Prdiction");
		if(this.accountInfo !=null){
			out.append(this.accountInfo.toString());out.append(CommonConstants.TRANSACTION_DELIMETER);
		}
		if(this.creditCardTransaction !=null){
		out.append(this.creditCardTransaction.toString());out.append(CommonConstants.TRANSACTION_DELIMETER);
		}
		if(this.accountModelFeatures !=null){
		out.append(this.accountModelFeatures.toString());
		}
		return out.toString();
	}
	

}
