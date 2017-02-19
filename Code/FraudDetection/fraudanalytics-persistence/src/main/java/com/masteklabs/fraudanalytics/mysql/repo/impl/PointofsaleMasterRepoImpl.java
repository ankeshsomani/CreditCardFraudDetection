package com.masteklabs.fraudanalytics.mysql.repo.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.redis.repo.PointofsaleMasterRepo;
import com.masteklabs.frauddetection.entity.PointofsaleMasterEntity;


@Component("pointOfSaleRepo")
public class PointofsaleMasterRepoImpl implements PointofsaleMasterRepo{

	public void save(PointofsaleMasterEntity pointofsale) {
		// TODO Auto-generated method stub
		
	}

	public PointofsaleMasterEntity find(Long posids) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PointofsaleMasterEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Long posids) {
		// TODO Auto-generated method stub
		
	}




}
