package com.masteklabs.fraudanalytics.mysql.repo;

import java.util.List;

import com.masteklabs.frauddetection.entity.CustomerMasterEntity;



public interface CustomerMasterRepo {
	
	public void save(CustomerMasterEntity customer);
	
	public CustomerMasterEntity find(String customerId);
	
	public List<CustomerMasterEntity> findAll();
	
	public void	delete(String customerId);

}
