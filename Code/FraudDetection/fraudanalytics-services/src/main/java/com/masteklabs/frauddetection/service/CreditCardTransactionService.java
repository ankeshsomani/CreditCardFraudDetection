package com.masteklabs.frauddetection.service;

import java.util.Set;

public interface CreditCardTransactionService {
	
	public void saveRecord(String accountId,String transaction, Long transactionTime);
	
	public Set<Object> getRecordsInScoreRange(String accountId,Long minTime,Long maxTime);
	
	public Long getCountOfTransactions(String accountId, Long minTime,Long maxTime);
	
	public Long	getSize(String accountId);
	
	public Long removeRecordsInScoreRange(String accountId, Long minTime, Long maxTime);
}
