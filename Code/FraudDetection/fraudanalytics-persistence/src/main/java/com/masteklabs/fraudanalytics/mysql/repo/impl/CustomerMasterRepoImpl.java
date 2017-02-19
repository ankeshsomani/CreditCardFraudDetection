package com.masteklabs.fraudanalytics.mysql.repo.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.mysql.repo.CustomerMasterRepo;
import com.masteklabs.frauddetection.entity.AccountMasterEntity;
import com.masteklabs.frauddetection.entity.CustomerMasterEntity;


@Component("customerMasterRepo")
public class CustomerMasterRepoImpl implements CustomerMasterRepo{
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	



	public void save(CustomerMasterEntity customer) {
		// TODO Auto-generated method stub
		
	}

	public CustomerMasterEntity find(String customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CustomerMasterEntity> findAll() {
		String sql="select c.customerid as customerid,c.name as name,c.dob as dob,c.annualincome as annualincome ,c.emailid as emailid,c.phonenumber as phonenumber,	"
				+ "c.gender as gender,c.address as address,a.accountid as accountid ,a.cardtype as cardtype,a.cardnumber as cardnumber from customer c,"
				+ "account a where a.customerid=c.customerid order by c.customerid ,a.accountid ;";
		List<CustomerMasterEntity> customers=new ArrayList<CustomerMasterEntity>();
		List<Map<String, Object>> custRows=jdbcTemplate.queryForList(sql);
		long temp=0;
		long prev=0;
		CustomerMasterEntity custMaster=null;
		for(Map<String,Object> custRow : custRows){
			CustomerMasterEntity customer = custMaster;
			long customerid=Long.valueOf(custRow.get("customerid").toString());
			
			prev=temp;
			temp=customerid;			
			if(prev !=temp){
				customer = new CustomerMasterEntity();
				List<AccountMasterEntity> accounts=new ArrayList<AccountMasterEntity>();
				customer.setCustomerId(customerid);
				customer.setName(custRow.get("name").toString());
				customer.setGender(custRow.get("gender").toString());
				customer.setEmailId(custRow.get("emailid").toString());
				customer.setPhoneNumber(custRow.get("phonenumber").toString());
				customer.setAddress(custRow.get("address").toString());
				customer.setAnnualIncome(Double.valueOf(custRow.get("annualincome").toString()));
				java.sql.Date dob=(java.sql.Date) custRow.get("dob");
				customer.setDob(new Date(dob.getTime()));
				custMaster=customer;
				customer.setAccounts(accounts);
				customers.add(customer);
			}
			AccountMasterEntity accMaster=new AccountMasterEntity();
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
