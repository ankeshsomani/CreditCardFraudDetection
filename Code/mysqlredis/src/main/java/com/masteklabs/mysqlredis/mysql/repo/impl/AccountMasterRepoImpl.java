package com.masteklabs.mysqlredis.mysql.repo.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.masteklabs.mysqlredis.bean.AccountMaster;
import com.masteklabs.mysqlredis.mysql.repo.AccountMasterRepo;

public class AccountMasterRepoImpl implements AccountMasterRepo {
	private JdbcTemplate jdbcTemplate;
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public void save(AccountMaster accountMaster) {
		// TODO Auto-generated method stub
		
	}

	public AccountMaster find(String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<AccountMaster> findAll() {
		String sql="SELECT * FROM ACCOUNT";
		List<AccountMaster> accounts=new ArrayList<AccountMaster>();
		List<Map<String, Object>> accRows=jdbcTemplate.queryForList(sql);
		for(Map<String,Object> accRow : accRows){
			AccountMaster account = new AccountMaster();
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
