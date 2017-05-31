package com.masteklabs.mysqlredis.mysql.repo;

import java.util.List;

import com.masteklabs.mysqlredis.bean.CustomerMaster;

public interface CustomerMasterRepo {
	
	public void save(CustomerMaster customer);
	
	public CustomerMaster find(String customerId);
	
	public List<CustomerMaster> findAll();
	
	public void	delete(String customerId);

}
