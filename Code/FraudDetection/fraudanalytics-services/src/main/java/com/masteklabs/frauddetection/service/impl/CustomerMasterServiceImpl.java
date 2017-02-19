package com.masteklabs.frauddetection.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.mysql.repo.CustomerMasterRepo;
import com.masteklabs.frauddetection.entity.CustomerMasterEntity;
import com.masteklabs.frauddetection.service.CustomerMasterService;


@Component("customerMasterService")
public class CustomerMasterServiceImpl implements CustomerMasterService{
	
	static final Logger log = Logger.getLogger(CustomerMasterServiceImpl.class.getName());
	
	@Autowired
	@Qualifier("customerMasterRepo")
	private CustomerMasterRepo customerMasterRepo;
	

	

	public List<CustomerMasterEntity> getAllCustomers(){
		List<CustomerMasterEntity> customers=customerMasterRepo.findAll();		
		return customers;	
	}
	

	
	

}
