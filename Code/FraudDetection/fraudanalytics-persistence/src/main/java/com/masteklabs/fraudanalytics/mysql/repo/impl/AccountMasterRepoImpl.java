package com.masteklabs.fraudanalytics.mysql.repo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.mysql.repo.AccountMasterRepo;
import com.masteklabs.frauddetection.entity.AccountMasterEntity;


@Component("accountMasterRepo")
public class AccountMasterRepoImpl implements AccountMasterRepo {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	

	
	public void save(AccountMasterEntity accountMaster) {
		// TODO Auto-generated method stub
		
	}

	public AccountMasterEntity find(String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AccountMasterEntity> findAll() {
		String sql="SELECT * FROM ACCOUNT";
		
		List<AccountMasterEntity> accounts=new ArrayList<AccountMasterEntity>();
		List<Map<String, Object>> accRows=jdbcTemplate.queryForList(sql);
		for(Map<String,Object> accRow : accRows){
			AccountMasterEntity account = new AccountMasterEntity();
			account.setAccountId(Long.valueOf(accRow.get("accountid").toString()));
			account.setCardNumber(Long.valueOf(accRow.get("cardnumber").toString()));
			account.setCardType(String.valueOf(accRow.get("cardtype")));
			
			account.setCustomerId(Long.valueOf(accRow.get("customerid").toString()));		
			accounts.add(account);
		}
		return accounts;
	}

	public void delete(String accountId) {
		// TODO Auto-generated method stub
		
	}

	
	
}
