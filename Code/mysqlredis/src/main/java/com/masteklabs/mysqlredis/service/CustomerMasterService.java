package com.masteklabs.mysqlredis.service;

import java.util.List;

import com.masteklabs.mysqlredis.bean.CustomerMaster;
import com.masteklabs.mysqlredis.mysql.repo.CustomerMasterRepo;


public class CustomerMasterService {
	
	private CustomerMasterRepo customerMasterRepo;
	
	public CustomerMasterRepo getCustomerMasterRepo() {
		return customerMasterRepo;
	}

	public void setCustomerMasterRepo(CustomerMasterRepo customerMasterRepo) {
		this.customerMasterRepo = customerMasterRepo;
	}

	public List<CustomerMaster> getAllCustomers(){
		List<CustomerMaster> customers=customerMasterRepo.findAll();		
		return customers;	
	}
	

	
	

}
