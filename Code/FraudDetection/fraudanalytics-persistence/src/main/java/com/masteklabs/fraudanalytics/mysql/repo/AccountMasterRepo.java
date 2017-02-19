package com.masteklabs.fraudanalytics.mysql.repo;

import java.util.List;

import com.masteklabs.frauddetection.entity.AccountMasterEntity;


public interface AccountMasterRepo {
public void save(AccountMasterEntity accountMaster);
	
	public AccountMasterEntity find(String accountId);
	
	public List<AccountMasterEntity> findAll();
	
	public void	delete(String accountId);
}
