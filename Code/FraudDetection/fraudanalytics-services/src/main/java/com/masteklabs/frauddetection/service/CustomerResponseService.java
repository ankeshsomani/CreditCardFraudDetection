package com.masteklabs.frauddetection.service;

import java.text.ParseException;

import com.masteklabs.fraudanalytics.request.dto.ProcessCustomerResponseRequest;

public interface CustomerResponseService {

	public void saveTransaction(String accId,String transaction,Long score);
	
	public Long processCustomerResponse(ProcessCustomerResponseRequest processCustomerResponseRequest) throws ParseException;
}