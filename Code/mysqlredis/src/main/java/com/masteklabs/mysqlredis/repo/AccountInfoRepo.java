package com.masteklabs.mysqlredis.repo;

import java.util.Map;

import com.masteklabs.mysqlredis.bean.AccountInfo;

public interface AccountInfoRepo {
	public void save(Long accountId,Map props);
	
	public AccountInfo find(Long id);
	
	
}
