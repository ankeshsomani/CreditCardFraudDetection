package com.masteklabs.frauddetection.redis.repo.impl;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.redis.repo.AccountInfoRepo;
import com.masteklabs.frauddetection.entity.AccountInfoEntity;

@Component("accountInfoRepo")
public class AccountInfoRepoImpl implements AccountInfoRepo{
	
	@Autowired 
	@Qualifier("redisTransactionTemplateLong")
	private RedisTemplate< Long, Object > redisTemplate;
	
	

	
	public void save(Long accountId,Map props) {
		
		this.redisTemplate.opsForHash().putAll(accountId, props);
	//	this.redisTemplate.expire(accountId, 1, TimeUnit.DAYS);
		
	}

	public AccountInfoEntity find(Long accountId) {
		AccountInfoEntity accInfo=new AccountInfoEntity();
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
		accInfo.setEmailId(values.get("emailid").toString());
		accInfo.setPhoneNumber(values.get("phonenumber").toString());
		return accInfo;
		
	}







}
