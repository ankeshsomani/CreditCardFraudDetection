package com.masteklabs.fraudanalytics.redis.repo;

import java.util.Set;

public interface CreditCardTransactionRepo {
	
	public void save(String accountId,String transaction, double transactionTime);
	
	public Set<Object> rangeByScore(String accountId,double minTime,double maxTime);
	
	public Long countTransactions(String accountId, double minTime,double maxTime);
	
	//public Map<Object, Object> findAll();
	
	public Long	size(String accountId);
	
	public Long removeByScore(String accountId, Long minTime, Long maxTime);
}
