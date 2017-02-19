package com.masteklabs.frauddetection.service.impl;


import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.mysql.repo.SuspectedTransactionRepo;
import com.masteklabs.fraudanalytics.redis.repo.CreditCardTransactionRepo;
import com.masteklabs.fraudanalytics.request.dto.ProcessCustomerResponseRequest;
import com.masteklabs.frauddetection.common.CommonConstants;
import com.masteklabs.frauddetection.entity.SuspectedTransactionEntity;
import com.masteklabs.frauddetection.service.CustomerResponseService;

@Component("customerResponseService")
public class CustomerResponseServiceImpl implements CustomerResponseService{
	static final Logger log = Logger.getLogger(CustomerResponseServiceImpl.class.getName());
	
	@Autowired
	@Qualifier("creditCardTransactionRepo")
	private CreditCardTransactionRepo creditCardTransactionRepo;
	
	@Autowired
	@Qualifier("suspectedTransactionRepo")
	private SuspectedTransactionRepo suspectedTransactionRepo;

	public void saveTransaction(String accId, String transaction, Long score) {
		log.info("in saveTransaction of TransactionServiceImpl");
		creditCardTransactionRepo.save(accId, transaction, score);
		log.info("saved record");
		
	}

	public Long processCustomerResponse(ProcessCustomerResponseRequest processCustomerResponseRequest) throws ParseException {
		log.info("in processCustomerResponse of TransactionServiceImpl");
		SuspectedTransactionEntity trans=suspectedTransactionRepo.find(processCustomerResponseRequest.getTransactionId(), processCustomerResponseRequest.getAccountId());
		Long score=trans.getTransactionDate().getTime();
		Long count=0l;
		if(processCustomerResponseRequest.getCustomerResponse()==1){
			System.out.println("in deletion code");
			System.out.println("score is "+score);
			count=creditCardTransactionRepo.removeByScore(CommonConstants.TRANS_PREFIX+processCustomerResponseRequest.getAccountId(), score, score);
		
		}
		suspectedTransactionRepo.updateFraudStatus(processCustomerResponseRequest.getTransactionId(), processCustomerResponseRequest.getAccountId()
				, processCustomerResponseRequest.getCustomerResponse());
		
		log.info("Deleted record count is "+count);
		return count;
	}

}
