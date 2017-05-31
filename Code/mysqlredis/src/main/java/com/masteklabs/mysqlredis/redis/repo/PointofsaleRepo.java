package com.masteklabs.mysqlredis.redis.repo;

import java.util.Map;

import com.masteklabs.mysqlredis.bean.Pointofsale;

public interface PointofsaleRepo {

	public void save(String posid,Map props);
	
	public Pointofsale find(String posid);
	
}
