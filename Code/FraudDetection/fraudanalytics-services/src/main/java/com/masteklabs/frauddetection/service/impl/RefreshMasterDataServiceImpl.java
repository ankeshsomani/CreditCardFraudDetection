package com.masteklabs.frauddetection.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.redis.repo.AccountInfoRepo;
import com.masteklabs.fraudanalytics.response.dto.ServiceResponse;
import com.masteklabs.frauddetection.common.DateUtils;
import com.masteklabs.frauddetection.entity.AccountMasterEntity;
import com.masteklabs.frauddetection.entity.CustomerMasterEntity;
import com.masteklabs.frauddetection.service.CustomerMasterService;
import com.masteklabs.frauddetection.service.RefreshMasterDataService;

@Component("refreshMasterDataService")
public class RefreshMasterDataServiceImpl implements RefreshMasterDataService{
	static final Logger log = Logger.getLogger(RefreshMasterDataServiceImpl.class.getName());
	
	@Autowired
	@Qualifier("accountInfoRepo")
	AccountInfoRepo accountInfoRepo;
	
	@Autowired
	@Qualifier("customerMasterService")
	CustomerMasterService customerMasterService;
	
	
	public ServiceResponse refreshMasterData() {
		ServiceResponse serviceResponse=null;
		List<CustomerMasterEntity> customers = customerMasterService.getAllCustomers();
		for (CustomerMasterEntity customer : customers) {
			List<AccountMasterEntity> accounts = customer.getAccounts();
			for (AccountMasterEntity account : accounts) {
				final Map<String, Object> properties = new HashMap<String, Object>();
				properties.put("name", customer.getName());
				properties.put("age", DateUtils.determineAge(customer.getDob()));
				properties.put("gender", customer.getGender());
				properties.put("emailid", customer.getEmailId());
				properties.put("phonenumber", customer.getPhoneNumber());
				properties.put("cardnumber", account.getCardNumber());
				properties.put("cardtype", account.getCardType());
				properties.put("annualincome", customer.getAnnualIncome());
				accountInfoRepo.save(account.getAccountId(), properties);
			}
			System.out.println(customer.toString());
		}
		serviceResponse=new ServiceResponse(true, null);
		return serviceResponse;
	}

}
