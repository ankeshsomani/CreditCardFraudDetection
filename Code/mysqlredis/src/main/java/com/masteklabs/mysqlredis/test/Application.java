package com.masteklabs.mysqlredis.test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.masteklabs.mysqlredis.bean.AccountMaster;
import com.masteklabs.mysqlredis.bean.CustomerMaster;
import com.masteklabs.mysqlredis.redis.repo.AccountInfoRepo;
import com.masteklabs.mysqlredis.service.CustomerMasterService;


public class Application {
	static final Logger log = Logger.getLogger(Application.class.getName());
	public static void refreshRedisFromMySQL(ApplicationContext context) throws SQLException {
		CustomerMasterService custMasterService = (CustomerMasterService) context.getBean("customerMasterService");
		AccountInfoRepo accInfoRepo = (AccountInfoRepo) context.getBean("accountInfoRepo");
		List<CustomerMaster> customers = custMasterService.getAllCustomers();
		for (CustomerMaster customer : customers) {
			List<AccountMaster> accounts = customer.getAccounts();
			for (AccountMaster account : accounts) {
				final Map<String, Object> properties = new HashMap<String, Object>();
				properties.put("name", customer.getName());			
				properties.put("age", determineAge(customer.getDob()));
				properties.put("gender", customer.getGender());
				properties.put("emailid", customer.getEmailId());
				properties.put("phonenumber", customer.getPhoneNumber());
				properties.put("cardnumber", account.getCardNumber());
				properties.put("cardtype", account.getCardType());
				properties.put("annualincome", customer.getAnnualIncome());
				accInfoRepo.save(account.getAccountId(), properties);
			}
			System.out.println(customer.toString());
		}
	}
	
	private static Double determineAge(Date dob) {
		int age = 0;
		Calendar born = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		if (dob != null) {
			now.setTime(new Date());
			born.setTime(dob);
			if (born.after(now)) {
				throw new IllegalArgumentException("Can't be born in the future");
			}
			age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
		}
		return Double.valueOf(age);
	}

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		try {
			log.warn("Before refreshing");
			refreshRedisFromMySQL(context);
			log.warn("After refreshing");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
