package com.masteklabs.mysqlredis.repo.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.masteklabs.mysqlredis.bean.AccountMaster;
import com.masteklabs.mysqlredis.bean.CustomerMaster;
import com.masteklabs.mysqlredis.repo.CustomerMasterRepo;

public class CustomerMasterRepoImpl implements CustomerMasterRepo{
	
	private JdbcTemplate jdbcTemplate;
	
	

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void save(CustomerMaster customer) {
		// TODO Auto-generated method stub
		
	}

	public CustomerMaster find(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CustomerMaster> findAll() {
		String sql="select c.customerid as customerid,c.name as name,c.dob as dob,c.annualincome as annualincome ,"
				+ "c.gender as gender,c.address as address,a.accountid as accountid ,a.cardtype as cardtype,a.cardnumber as cardnumber from customer c,"
				+ "account a where a.customerid=c.customerid order by c.customerid ,a.accountid ;";
		List<CustomerMaster> customers=new ArrayList<CustomerMaster>();
		List<Map<String, Object>> custRows=jdbcTemplate.queryForList(sql);
		long temp=0;
		long prev=0;
		CustomerMaster custMaster=null;
		for(Map<String,Object> custRow : custRows){
			CustomerMaster customer = custMaster;
			long customerid=Long.valueOf(custRow.get("customerid").toString());
			
			prev=temp;
			temp=customerid;			
			if(prev !=temp){
				customer = new CustomerMaster();
				List<AccountMaster> accounts=new ArrayList<AccountMaster>();
				customer.setCustomerId(customerid);
				customer.setName(custRow.get("name").toString());
				customer.setGender(custRow.get("gender").toString());
				customer.setAddress(custRow.get("address").toString());
				customer.setAnnualIncome(Double.valueOf(custRow.get("annualincome").toString()));
				java.sql.Date dob=(java.sql.Date) custRow.get("dob");
				customer.setDob(new Date(dob.getTime()));
				custMaster=customer;
				customer.setAccounts(accounts);
				customers.add(customer);
			}
			AccountMaster accMaster=new AccountMaster();
			accMaster.setAccountId(Long.valueOf(custRow.get("accountid").toString()));
			accMaster.setCardNumber(Long.valueOf(custRow.get("cardnumber").toString()));
			accMaster.setCardType(String.valueOf(custRow.get("cardtype")));
			
			accMaster.setCustomerId(customerid);	
			custMaster.getAccounts().add(accMaster);
			
		}
		return customers;
	}

	public void delete(String customerId) {
		// TODO Auto-generated method stub
		
	}

}
