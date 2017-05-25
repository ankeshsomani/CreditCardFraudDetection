package com.masteklabs.mysqlredis.repo;

import java.util.Set;

public interface CreditCardTransactionRepo {
	
	public void save(Long accountId,String transaction, double transactionTime);
	
	public Set<Object> rangeByScore(Long accountId,double minTime,double maxTime);
	
	public Long countTransactions(Long accountId, double minTime,double maxTime);
	
	//public Map<Object, Object> findAll();
	
	public Long	size(Long accountId);

}
