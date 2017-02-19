package com.masteklabs.frauddetection;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;

import com.masteklabs.fraudanalytics.mysql.repo.AccountMasterRepo;
import com.masteklabs.fraudanalytics.mysql.repo.CustomerMasterRepo;
import com.masteklabs.fraudanalytics.mysql.repo.SuspectedTransactionRepo;
import com.masteklabs.fraudanalytics.mysql.repo.impl.AccountMasterRepoImpl;
import com.masteklabs.fraudanalytics.mysql.repo.impl.CustomerMasterRepoImpl;
import com.masteklabs.fraudanalytics.mysql.repo.impl.SuspectedTransactionRepoImpl;
import com.masteklabs.fraudanalytics.redis.repo.AccountInfoRepo;
import com.masteklabs.fraudanalytics.redis.repo.CreditCardTransactionRepo;
import com.masteklabs.frauddetection.common.CommonConstants;
import com.masteklabs.frauddetection.redis.repo.impl.AccountInfoRepoImpl;
import com.masteklabs.frauddetection.redis.repo.impl.CreditCardTransactionRepoImpl;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
public class Application {
	
	static final Logger log = Logger.getLogger(Application.class.getName());
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory connFactory = new JedisConnectionFactory();
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(CommonConstants.REDIS_POOL_CONNECTIONS);

		connFactory.setHostName(CommonConstants.REDIS_HOST);
		connFactory.setPort(CommonConstants.REDIS_PORT);
		connFactory.setPassword(CommonConstants.REDIS_PASSWORD);
		connFactory.setUsePool(true);
		connFactory.setPoolConfig(poolConfig);
		return connFactory;
	}

	@Bean(name = "redisTransactionTemplateString")
	RedisTemplate<String, Object> redisTransactionTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setConnectionFactory(jedisConnectionFactory());
		// template.setDefaultSerializer( new RedisSerializer() );
		return template;
	}

	@Bean(name = "redisTransactionTemplateLong")
	RedisTemplate<Long, Object> redisTransactionTemplateNew() {
		final RedisTemplate<Long, Object> template = new RedisTemplate<Long, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setValueSerializer(new StringRedisSerializer());
		return template;
	}

	@Bean(name = "dataSource")
	ComboPooledDataSource dataSource() throws SQLException, NamingException, PropertyVetoException {
		final ComboPooledDataSource dataSource = new ComboPooledDataSource();

		dataSource.setDriverClass(CommonConstants.MYSQL_JDBC_DRIVER);
		dataSource.setJdbcUrl(CommonConstants.MYSQL_JDBC_URL);
		dataSource.setUser(CommonConstants.MYSQL_USERNAME);
		dataSource.setPassword(CommonConstants.MYSQL_PASSWORD);
		dataSource.setInitialPoolSize(CommonConstants.MYSQL_INITIAL_POOL_SIZE);
		dataSource.setMaxPoolSize(CommonConstants.MYSQL_MAX_POOL_SIZE);
		return dataSource;

	}
	@Bean(name="jdbcTemplate")
	 JdbcTemplate jdbcTemplate() throws SQLException, NamingException, PropertyVetoException{
		 final JdbcTemplate jdbcTemplate=new JdbcTemplate();
		 jdbcTemplate.setDataSource(dataSource());
		 return jdbcTemplate;
		 
	 }
	
	@Bean(name="customerMasterRepo")
	CustomerMasterRepo customerMasterRepo(){
		CustomerMasterRepo customerMasterRepo=new  CustomerMasterRepoImpl();
		return customerMasterRepo;
	}
	@Bean(name="accountInfoRepo")
	AccountInfoRepo accountInfoRepo(){
		AccountInfoRepo accountInfoRepo=new  AccountInfoRepoImpl();
		return accountInfoRepo;
	}
	@Bean(name="accountMasterRepo")
	AccountMasterRepo accountMasterRepo(){
		AccountMasterRepo accountMasterRepo=new  AccountMasterRepoImpl();
		return accountMasterRepo;
	}
	
	@Bean(name="suspectedTransactionRepo")
	SuspectedTransactionRepo suspectedTransactionRepo(){
		SuspectedTransactionRepo suspectedTransactionRepo=new  SuspectedTransactionRepoImpl();
		return suspectedTransactionRepo;
	}
	@Bean(name="creditCardTransactionRepo")
	CreditCardTransactionRepo creditCardTransactionRepo(){
		CreditCardTransactionRepo creditCardTransactionRepo=new  CreditCardTransactionRepoImpl();
		return creditCardTransactionRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}



}