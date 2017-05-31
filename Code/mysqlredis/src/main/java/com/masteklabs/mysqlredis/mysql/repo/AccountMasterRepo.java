package com.masteklabs.mysqlredis.mysql.repo;

import java.util.List;

import com.masteklabs.mysqlredis.bean.AccountMaster;

public interface AccountMasterRepo {
public void save(AccountMaster accountMaster);
	
	public AccountMaster find(String accountId);
	
	public List<AccountMaster> findAll();
	
	public void	delete(String accountId);
}
