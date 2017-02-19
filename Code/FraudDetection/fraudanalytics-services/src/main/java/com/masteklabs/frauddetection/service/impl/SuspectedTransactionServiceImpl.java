package com.masteklabs.frauddetection.service.impl;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.mysql.repo.SuspectedTransactionRepo;
import com.masteklabs.frauddetection.entity.SuspectedTransactionEntity;
import com.masteklabs.frauddetection.service.SuspectedTransactionService;

@Component("suspectedTransactionService")
public class SuspectedTransactionServiceImpl implements SuspectedTransactionService{
	
	@Autowired
	@Qualifier("suspectedTransactionRepo")
	private SuspectedTransactionRepo suspectedTransactionRepo;

	@Override
	public int addSuspectedTransaction(SuspectedTransactionEntity suspectedTransaction) throws ParseException {
		
		return suspectedTransactionRepo.save(suspectedTransaction);
	}

	@Override
	public int updateSuspectedTransaction(SuspectedTransactionEntity suspectedTransaction) {
		
		return suspectedTransactionRepo.updateFraudStatus(suspectedTransaction.getTransactionId(),
				suspectedTransaction.getAccountId(), suspectedTransaction.getFraudStatus());
	}

}
