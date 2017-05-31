package com.masteklabs.mysqlredis.redis.repo;

import java.util.Map;

import com.masteklabs.mysqlredis.bean.Person;


public interface PersonRepo 
{
		public void save(Person person);
		
		public Person find(String id);
		
		public Map<Object, Object> findAll();
		
		public void	delete(String id);
}
