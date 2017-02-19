package com.masteklabs.frauddetection.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.redis.repo.AccountInfoRepo;
import com.masteklabs.frauddetection.entity.AccountInfoEntity;
import com.masteklabs.frauddetection.service.AccountInfoService;

@Component("accountInfoService")
public class AccountInfoServiceImpl implements AccountInfoService {

	@Autowired
	@Qualifier("accountInfoRepo")
	private AccountInfoRepo accountInfoRepo;
	
	@Override
	public void saveRecord(Long accountId, Map props) {
		accountInfoRepo.save(accountId, props);
		
	}

	@Override
	public AccountInfoEntity findRecord(Long id) {
		return accountInfoRepo.find(id);
	}

}
