package com.masteklabs.frauddetection.redis.repo.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.redis.repo.CreditCardTransactionRepo;



@Component("creditCardTransactionRepo")
public class CreditCardTransactionRepoImpl implements  CreditCardTransactionRepo{
	
	@Autowired
	@Qualifier("redisTransactionTemplateString")
	private RedisTemplate<String, Object> redisTransactionTemplateString;
	
	
	

	
	public void save(String accountId, String trans, double transactionTime) {
		this.redisTransactionTemplateString.opsForZSet().add(accountId, trans, transactionTime);
		
	}

	public Set<Object> rangeByScore(String accountId,double minTime, double maxTime) {
		Set<Object>  transactions=this.redisTransactionTemplateString.opsForZSet().rangeByScore(accountId, minTime, maxTime);
		return transactions;
	}

	public Long countTransactions(String accountId, double minTime, double maxTime) {
		Long count=this.redisTransactionTemplateString.opsForZSet().count(accountId, minTime, maxTime);
		return count;
	}

	public Long size(String accountId) {
		Long count=this.redisTransactionTemplateString.opsForZSet().size(accountId);
		return count;
	}
	
	
	public Long removeByScore(String accountId, Long minTime, Long maxTime) {
		Long count = this.redisTransactionTemplateString.opsForZSet().removeRangeByScore(accountId, minTime, minTime);
		return count;
	}
}
