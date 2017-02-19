package com.masteklabs.frauddetection.service;

import java.util.Map;

import com.masteklabs.frauddetection.entity.AccountInfoEntity;

public interface AccountInfoService {
	public void saveRecord(Long accountId,Map props);
	
	public AccountInfoEntity findRecord(Long id);
}
