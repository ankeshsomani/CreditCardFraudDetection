package com.masteklabs.mysqlredis.redis.repo.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.masteklabs.mysqlredis.bean.Person;
import com.masteklabs.mysqlredis.redis.repo.PersonRepo;


public class PersonRepoImpl implements  PersonRepo
{
		
		//private RedisTemplate<String, Person> redisTemplate;
		
		@Autowired 
		private RedisTemplate< String, Object > redisTemplate;
		
		private static String PERSON_KEY = "Person";

		public RedisTemplate<String, Object> getRedisTemplate()
		{
				return redisTemplate;
		}

		public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate)
		{
				this.redisTemplate = redisTemplate;
		}

	
		public void save(Person person)
		{
				this.redisTemplate.opsForHash().put(PERSON_KEY, person.getId(), person);
		}

	
		public Person find(String id)
		{
				return (Person)this.redisTemplate.opsForHash().get(PERSON_KEY, id);
		}

	
		public Map<Object,Object> findAll()
		{
				return this.redisTemplate.opsForHash().entries(PERSON_KEY);
		}

		public void delete(String id)
		{
				this.redisTemplate.opsForHash().delete(PERSON_KEY,id);
				
		}

}
