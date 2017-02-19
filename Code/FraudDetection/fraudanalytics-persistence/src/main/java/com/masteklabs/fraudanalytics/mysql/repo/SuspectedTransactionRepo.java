package com.masteklabs.fraudanalytics.mysql.repo;

import java.util.List;

import com.masteklabs.frauddetection.entity.SuspectedTransactionEntity;

public interface SuspectedTransactionRepo {
	public int save(SuspectedTransactionEntity suspectedTransactionEntity) ;

	public SuspectedTransactionEntity find(String transactionId,Long accountId);

	public int updateFraudStatus(String transactionId,Long accountId,Integer fraudStatus);

	public List<SuspectedTransactionEntity> findAll();

	public void delete(String transactionId);
}
