package com.masteklabs.fraudanalytics.redis.repo;

import java.util.Map;

import com.masteklabs.frauddetection.entity.AccountInfoEntity;

public interface AccountInfoRepo {
	public void save(Long accountId,Map props);
	
	public AccountInfoEntity find(Long id);
	
	
	
}
