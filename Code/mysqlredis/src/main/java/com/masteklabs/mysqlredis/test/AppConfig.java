package com.masteklabs.mysqlredis.test;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;

import com.masteklabs.mysqlredis.mysql.repo.AccountMasterRepo;
import com.masteklabs.mysqlredis.mysql.repo.CustomerMasterRepo;
import com.masteklabs.mysqlredis.mysql.repo.impl.AccountMasterRepoImpl;
import com.masteklabs.mysqlredis.mysql.repo.impl.CustomerMasterRepoImpl;
import com.masteklabs.mysqlredis.redis.repo.AccountInfoRepo;
import com.masteklabs.mysqlredis.redis.repo.CreditCardTransactionRepo;
import com.masteklabs.mysqlredis.redis.repo.impl.AccountInfoRepoImpl;
import com.masteklabs.mysqlredis.redis.repo.impl.CreditCardTransactionRepoImpl;
import com.masteklabs.mysqlredis.service.AccountMasterService;
import com.masteklabs.mysqlredis.service.CustomerMasterService;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class AppConfig {
	@Bean
	 JedisConnectionFactory jedisConnectionFactory() {
	 JedisConnectionFactory connFactory=new JedisConnectionFactory();
	JedisPoolConfig poolConfig = new JedisPoolConfig();
   poolConfig.setMaxTotal(10);
	
	 connFactory.setHostName("localhost");
	 connFactory.setPort(6379);
	 connFactory.setPassword("masteklabs");
	 connFactory.setUsePool(true);
	 connFactory.setPoolConfig(poolConfig);
	  return connFactory;
	 }

	 @Bean
	 RedisTemplate< String, Object > redisTransactionTemplate() {
	  final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
	  template.setConnectionFactory( jedisConnectionFactory() );
	 template.setKeySerializer( new StringRedisSerializer() );
	  template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  template.setConnectionFactory(jedisConnectionFactory());
	 //template.setDefaultSerializer( new RedisSerializer() );
	  return template;
	 }
	 @Bean
	 RedisTemplate< Long, Object > redisTransactionTemplateNew() {
	  final RedisTemplate< Long, Object > template =  new RedisTemplate< Long, Object >();
	  template.setConnectionFactory( jedisConnectionFactory() );
	 template.setKeySerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  template.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
	  template.setValueSerializer( new StringRedisSerializer() );
	 // template.setConnectionFactory(jedisConnectionFactory());
	 //template.setDefaultSerializer( new RedisSerializer() );
	  return template;
	 }
	 

	 @Bean(name="creditCardTransactionRepo")
	 CreditCardTransactionRepo creditCardTransactionRepo(){
		 final CreditCardTransactionRepoImpl creditCardTransactionRepoImpl=new CreditCardTransactionRepoImpl();				 
		 creditCardTransactionRepoImpl.setRedisTemplate(redisTransactionTemplateNew());	
		 return creditCardTransactionRepoImpl;
		 
	 }
	 
	 @Bean(name="accountInfoRepo")
	 AccountInfoRepo accountInfoRepo(){
		 final AccountInfoRepoImpl accountInfoRepoImpl=new AccountInfoRepoImpl();		
		 accountInfoRepoImpl.setRedisTemplate(redisTransactionTemplateNew());
		 return accountInfoRepoImpl;
		 
	 }
	 @Bean(name="dataSource")
	 ComboPooledDataSource dataSource() throws SQLException, NamingException, PropertyVetoException{
		 final ComboPooledDataSource dataSource=new ComboPooledDataSource();		
		
	        dataSource.setDriverClass("com.mysql.jdbc.Driver");
	        dataSource.setJdbcUrl("jdbc:mysql://localhost/fraudanalytics");
	        dataSource.setUser("root");
	        dataSource.setPassword("");
	        dataSource.setInitialPoolSize(1);
	        dataSource.setMaxPoolSize(10);
	       
		 return dataSource;
		 
	 }
	 @Bean(name="jdbcTemplate")
	 JdbcTemplate jdbcTemplate() throws SQLException, NamingException, PropertyVetoException{
		 final JdbcTemplate jdbcTemplate=new JdbcTemplate();
		 jdbcTemplate.setDataSource(dataSource());
		 return jdbcTemplate;
		 
	 }
	 @Bean(name="customerMasterRepo")
	 CustomerMasterRepo customerMasterRepo() throws SQLException, NamingException, PropertyVetoException{
		 final CustomerMasterRepoImpl custRepo=new CustomerMasterRepoImpl();
		 custRepo.setJdbcTemplate(jdbcTemplate());
		 return custRepo;
		 
	 }
	 @Bean(name="accountMasterRepo")
	 AccountMasterRepo accountMasterRepo() throws SQLException, NamingException, PropertyVetoException{
		 final AccountMasterRepoImpl accountRepo=new AccountMasterRepoImpl();	
		 accountRepo.setJdbcTemplate(jdbcTemplate());
		 return accountRepo;
		 
	 }
	 @Bean(name="customerMasterService")
	 CustomerMasterService customerMasterService() throws SQLException, NamingException, PropertyVetoException{
		 final CustomerMasterService custMasterService=new CustomerMasterService();	
		 custMasterService.setCustomerMasterRepo(customerMasterRepo());
		 return custMasterService;
		 
	 }
	 @Bean(name="accountMasterService")
	 AccountMasterService accountMasterService() throws SQLException, NamingException, PropertyVetoException{
		 final AccountMasterService accMasterService=new AccountMasterService();	
		 accMasterService.setAccountMasterRepo(accountMasterRepo());
		 return accMasterService;
		 
	 }
}
