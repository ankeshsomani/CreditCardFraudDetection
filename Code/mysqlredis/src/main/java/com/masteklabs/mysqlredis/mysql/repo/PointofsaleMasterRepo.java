package com.masteklabs.mysqlredis.mysql.repo;

import java.util.List;

import com.masteklabs.mysqlredis.bean.PointofsaleMaster;

public interface PointofsaleMasterRepo {
public void save(PointofsaleMaster pointofsale);
	
	public PointofsaleMaster find(Long posids);
	
	public List<PointofsaleMaster> findAll();
	
	public void	delete(Long posids);
}
