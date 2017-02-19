package com.masteklabs.frauddetection.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.mysql.repo.AccountMasterRepo;
import com.masteklabs.frauddetection.entity.AccountMasterEntity;
import com.masteklabs.frauddetection.service.AccountMasterService;


@Component("accountMasterService")
public class AccountMasterServiceImpl implements AccountMasterService{
	
	static final Logger log = Logger.getLogger(AccountMasterServiceImpl.class.getName());
	
	@Autowired
	@Qualifier("accountMasterRepo")	
	private AccountMasterRepo accountMasterRepo;

	public List<AccountMasterEntity> getAllAccounts(){
		List<AccountMasterEntity> accounts=accountMasterRepo.findAll();
		return accounts;	
	}
}
