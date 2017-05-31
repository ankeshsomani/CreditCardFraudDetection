package com.masteklabs.mysqlredis.redis.repo.impl;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;

import com.masteklabs.mysqlredis.bean.Pointofsale;
import com.masteklabs.mysqlredis.redis.repo.PointofsaleRepo;

public class PointofsaleRepoImpl implements PointofsaleRepo{
	private RedisTemplate<String, Object> redisTemplate;
	


	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate)
	{
			this.redisTemplate = redisTemplate;
	}
	public void save(String posid, Map props) {
		this.redisTemplate.opsForHash().putAll(posid, props);
		
	}

	public Pointofsale find(String posid) {
		Pointofsale pointofsale=new Pointofsale();
		Map<Object, Object> values=this.redisTemplate.opsForHash().entries(posid);
	
		pointofsale.setPosid(posid);
		pointofsale.setDescription(values.get("name").toString());
		pointofsale.setOnline(new Integer(values.get("age").toString()));
		pointofsale.setCategoryid(new Integer(values.get("categoryid").toString()));
		return pointofsale;
		
	}

}
