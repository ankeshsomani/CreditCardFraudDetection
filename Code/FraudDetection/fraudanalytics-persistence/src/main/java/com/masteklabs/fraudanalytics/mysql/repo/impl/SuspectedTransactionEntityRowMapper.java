package com.masteklabs.fraudanalytics.mysql.repo.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.masteklabs.frauddetection.entity.SuspectedTransactionEntity;

public class SuspectedTransactionEntityRowMapper implements RowMapper<SuspectedTransactionEntity> {

	public SuspectedTransactionEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		SuspectedTransactionEntity suspectedTrans=new SuspectedTransactionEntity();
		suspectedTrans.setAccountId(new Long(rs.getInt(1)));
		suspectedTrans.setTransactionId(rs.getString(2));
		suspectedTrans.setAmount(rs.getDouble(3)); 
		suspectedTrans.setCardNumber(new Long(rs.getInt(4)));
		suspectedTrans.setCreatedOn(rs.getDate(5));
		suspectedTrans.setModifiedOn(rs.getDate(6));
		suspectedTrans.setFraudStatus(rs.getInt(7));
		suspectedTrans.setDate(rs.getString(8));
		return suspectedTrans;
	}

}
