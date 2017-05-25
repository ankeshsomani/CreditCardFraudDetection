package com.masteklabs.mysqlredis.test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.masteklabs.mysqlredis.bean.AccountMaster;
import com.masteklabs.mysqlredis.bean.CreditCardTransaction;
import com.masteklabs.mysqlredis.bean.CustomerMaster;
import com.masteklabs.mysqlredis.repo.AccountInfoRepo;
import com.masteklabs.mysqlredis.repo.CreditCardTransactionRepo;
import com.masteklabs.mysqlredis.service.CustomerMasterService;


public class Application {

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
				properties.put("cardnumber", account.getCardNumber());
				properties.put("cardtype", account.getCardType());
				properties.put("annualincome", customer.getAnnualIncome());
				accInfoRepo.save(account.getAccountId(), properties);
			}
			System.out.println(customer.toString());
		}
	}
	protected static CreditCardTransaction createAndInsertCardTransaction(ApplicationContext context) {
		CreditCardTransactionRepo creditCardTransactionRepo = (CreditCardTransactionRepo) context.getBean("creditCardTransactionRepo");
		// Form the credit card transaction object
		CreditCardTransaction cardTrans = new CreditCardTransaction();
		  final Pattern COMMA_REGEX = Pattern.compile(",");
		String paramInput="1141,T7332,1245,01012016,food at sai,100";
		String s[] = COMMA_REGEX.split(paramInput);
		cardTrans.setAccountId(new Long(s[0]));
		cardTrans.setTransactionId(s[1]);
		cardTrans.setPosid(new Long(s[2]));
		cardTrans.setDate(s[3]);
		cardTrans.setDescription(s[4]);
		if (s[5] != null && !(s[5].equals(""))) {
			cardTrans.setAmount(new Double(s[5]));
		}
		
		StringBuilder trans = new StringBuilder();
		trans.append(cardTrans.getTransactionId());
		trans.append(",");
		trans.append(cardTrans.getAmount());
		System.out.println(cardTrans.getAccountId()+"");
		System.out.println(trans.toString());
		creditCardTransactionRepo.save(cardTrans.getAccountId(), trans.toString(), System.currentTimeMillis());
		return cardTrans;
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
			//refreshRedisFromMySQL(context);
			createAndInsertCardTransaction(context);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
