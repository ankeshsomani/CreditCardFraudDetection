package com.masteklabs.mysqlredis.repo.impl;

import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;

import com.masteklabs.mysqlredis.repo.CreditCardTransactionRepo;

public class CreditCardTransactionRepoImpl implements  CreditCardTransactionRepo{
	private RedisTemplate<Long, Object> redisTemplate;
	
	public RedisTemplate<Long, Object> getRedisTemplate()
	{
			return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<Long, Object> redisTemplate)
	{
			this.redisTemplate = redisTemplate;
	}
	
	public void save(Long accountId, String trans, double transactionTime) {
		this.redisTemplate.opsForZSet().add(accountId, trans, transactionTime);
		
	}

	public Set<Object> rangeByScore(Long accountId,double minTime, double maxTime) {
		Set<Object>  transactions=this.redisTemplate.opsForZSet().rangeByScore(accountId, minTime, maxTime);
		return transactions;
	}

	public Long countTransactions(Long accountId, double minTime, double maxTime) {
		Long count=this.redisTemplate.opsForZSet().count(accountId, minTime, maxTime);
		return count;
	}

	public Long size(Long accountId) {
		Long count=this.redisTemplate.opsForZSet().size(accountId);
		return count;
	}

}
