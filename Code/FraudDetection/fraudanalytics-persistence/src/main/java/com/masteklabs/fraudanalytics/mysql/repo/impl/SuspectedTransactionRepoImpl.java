package com.masteklabs.fraudanalytics.mysql.repo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.mysql.repo.SuspectedTransactionRepo;
import com.masteklabs.frauddetection.entity.SuspectedTransactionEntity;

@Component("suspectedTransactionRepo")
public class SuspectedTransactionRepoImpl implements SuspectedTransactionRepo {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public int save(SuspectedTransactionEntity suspectedTransactionEntity)  {
		StringBuilder sql = new StringBuilder("INSERT into suspected_transactions "
				+ "(accountid,transactionid,cardnumber,amount,created_on,modified_on,fraud_status,transactiondate) values(");
		sql.append(suspectedTransactionEntity.getAccountId());
		sql.append(",");
		sql.append("'");
		sql.append(suspectedTransactionEntity.getTransactionId());
		sql.append("'");
		sql.append(",");
		sql.append(suspectedTransactionEntity.getCardNumber());
		sql.append(",");
		sql.append(suspectedTransactionEntity.getAmount());
		sql.append(",SYSDATE(),SYSDATE()");	
		sql.append(",");
		sql.append(suspectedTransactionEntity.getFraudStatus());
		sql.append(",");
		sql.append("'");
		sql.append(suspectedTransactionEntity.getDate());
		sql.append("'");
		sql.append(")");
		System.out.println(sql.toString());
		return jdbcTemplate.update(sql.toString());
	}

	public SuspectedTransactionEntity find(String transactionId, Long accountId) {
		StringBuilder sql = new StringBuilder(
				"SELECT * FROM suspected_transactions WHERE transactionid = ?  AND accountid= ?");

		SuspectedTransactionEntity suspectedTransactionEntity = (SuspectedTransactionEntity) jdbcTemplate
				.queryForObject(sql.toString(), new Object[] { transactionId, accountId },
						new SuspectedTransactionEntityRowMapper());

		return suspectedTransactionEntity;
	}

	public List<SuspectedTransactionEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(String transactionId) {
		// TODO Auto-generated method stub

	}

	public int updateFraudStatus(String transactionId,Long accountId,Integer fraudStatus) {
		StringBuilder sql = new StringBuilder("UPDATE suspected_transactions " + "SET modified_on=SYSDATE() , fraud_status=");
		sql.append(fraudStatus);
		sql.append(" WHERE transactionid = ");
		sql.append("'");
		sql.append(transactionId);
		sql.append("'");
		sql.append(" AND accountid = ");
		sql.append(accountId);
		
		return jdbcTemplate.update(sql.toString());
	}



}
