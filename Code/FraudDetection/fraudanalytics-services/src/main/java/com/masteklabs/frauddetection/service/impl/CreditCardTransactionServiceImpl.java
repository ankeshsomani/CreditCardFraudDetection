package com.masteklabs.frauddetection.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.redis.repo.CreditCardTransactionRepo;
import com.masteklabs.frauddetection.service.CreditCardTransactionService;

@Component("creditCardTransactionService")
public class CreditCardTransactionServiceImpl implements CreditCardTransactionService{

	@Autowired
	@Qualifier("creditCardTransactionRepo")
	private CreditCardTransactionRepo creditCardTransactionRepo;
	
	@Override
	public void saveRecord(String accountId, String transaction, Long transactionTime) {
		creditCardTransactionRepo.save(accountId, transaction, transactionTime);
		
	}

	@Override
	public Set<Object> getRecordsInScoreRange(String accountId, Long minTime, Long maxTime) {
		return creditCardTransactionRepo.rangeByScore(accountId, minTime, maxTime);
		
	}

	@Override
	public Long getCountOfTransactions(String accountId, Long minTime, Long maxTime) {
		return creditCardTransactionRepo.countTransactions(accountId, minTime, maxTime);
	}

	@Override
	public Long getSize(String accountId) {
		return creditCardTransactionRepo.size(accountId);
	}

	@Override
	public Long removeRecordsInScoreRange(String accountId, Long minTime, Long maxTime) {
		return creditCardTransactionRepo.removeByScore(accountId, minTime, maxTime);
	}

}
