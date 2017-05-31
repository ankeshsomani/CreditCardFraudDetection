package com.masteklabs.mysqlredis.service;

import java.util.List;

import com.masteklabs.mysqlredis.bean.AccountMaster;
import com.masteklabs.mysqlredis.mysql.repo.AccountMasterRepo;

public class AccountMasterService {
	
	private AccountMasterRepo accountMasterRepo;
	
	
	public AccountMasterRepo getAccountMasterRepo() {
		return accountMasterRepo;
	}


	public void setAccountMasterRepo(AccountMasterRepo accountMasterRepo) {
		this.accountMasterRepo = accountMasterRepo;
	}


	public List<AccountMaster> getAllAccounts(){
		List<AccountMaster> accounts=accountMasterRepo.findAll();
		return accounts;	
	}
}
