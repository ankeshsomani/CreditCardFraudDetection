package com.masteklabs.frauddetection.service;

import java.text.ParseException;

import com.masteklabs.frauddetection.entity.SuspectedTransactionEntity;

public interface SuspectedTransactionService {

	
	public int addSuspectedTransaction(SuspectedTransactionEntity suspectedTransaction) throws ParseException;
	
	public int updateSuspectedTransaction(SuspectedTransactionEntity suspectedTransaction);
}
