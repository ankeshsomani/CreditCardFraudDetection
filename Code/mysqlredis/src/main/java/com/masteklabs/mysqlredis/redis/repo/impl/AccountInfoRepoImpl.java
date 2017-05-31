package com.masteklabs.mysqlredis.redis.repo.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.masteklabs.mysqlredis.bean.AccountInfo;
import com.masteklabs.mysqlredis.redis.repo.AccountInfoRepo;

public class AccountInfoRepoImpl implements AccountInfoRepo{
	
	@Autowired 
	private RedisTemplate< Long, Object > redisTemplate;
	
	
	public RedisTemplate<Long, Object> getRedisTemplate()
	{
			return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<Long, Object> redisTemplate)
	{
			this.redisTemplate = redisTemplate;
	}
	
	public void save(Long accountId,Map props) {
		
		this.redisTemplate.opsForHash().putAll(accountId, props);
	//	this.redisTemplate.expire(accountId, 1, TimeUnit.DAYS);
		
	}

	public AccountInfo find(Long accountId) {
		AccountInfo accInfo=new AccountInfo();
		Map<Object, Object> values=this.redisTemplate.opsForHash().entries(accountId);
	
		accInfo.setAccountId(accountId);
		accInfo.setName(values.get("name").toString());
		System.out.println("****");
		System.out.println(values.get("age").toString());
		if(values.get("age").toString() !=null){
			accInfo.setAge(new Double(values.get("age").toString()));
		}
		if(values.get("annualincome").toString() !=null){
			accInfo.setAnnualIncome(new Double(values.get("annualincome").toString()));
		}
		if(values.get("cardnumber").toString() !=null){
			accInfo.setCardNumber(new Long(values.get("cardnumber").toString()));
		}
		accInfo.setCardType(values.get("cardtype").toString());
		accInfo.setGender(values.get("gender").toString());
		return accInfo;
		
	}







}
