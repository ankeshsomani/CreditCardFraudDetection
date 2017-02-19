package com.masteklabs.fraudanalytics.redis.repo;

import java.util.List;

import com.masteklabs.frauddetection.entity.PointofsaleMasterEntity;



public interface PointofsaleMasterRepo {
public void save(PointofsaleMasterEntity pointofsale);
	
	public PointofsaleMasterEntity find(Long posids);
	
	public List<PointofsaleMasterEntity> findAll();
	
	public void	delete(Long posids);
}
