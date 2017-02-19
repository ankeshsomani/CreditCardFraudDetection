package com.masteklabs.fraudanalytics.featurederivation;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.common.dto.PredictionInput;
import com.masteklabs.fraudanalytics.prediction.dto.AccountDerivedFeatures;
import com.masteklabs.fraudanalytics.prediction.dto.EnrichedData;
import com.masteklabs.fraudanalytics.redis.repo.AccountInfoRepo;
import com.masteklabs.fraudanalytics.redis.repo.CreditCardTransactionRepo;
import com.masteklabs.frauddetection.common.CommonConstants;
import com.masteklabs.frauddetection.common.DateUtils;
import com.masteklabs.frauddetection.entity.AccountInfoEntity;
import com.masteklabs.frauddetection.entity.CreditCardTransactionEntity;


@Component("featureDerivationUtils")
public class FeatureDerivationUtils implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(FeatureDerivationUtils.class.getName());
	
	

	public static Integer determineGenderCode(String gender) {
		int code = 999;
		if (gender != null && !("".equals(gender))) {
			if (gender.equalsIgnoreCase("M")) {
				code = 1;
			} else if (gender.equalsIgnoreCase("F")) {
				code = 0;
			}

		}
		return code;
	}

	public static Integer determineCardType(Long cardNumber) {
		int cardType = 0;
		if (cardNumber % 2 == 0) {
			cardType = 1;
		}
		return cardType;
	}

	public static EnrichedData createAggregateInput(String paramInput) {

		EnrichedData enrichedData = new EnrichedData();
		String s[] = CommonConstants.COMMA_REGEX.split(paramInput);
		AccountInfoEntity accInfo = new AccountInfoEntity();
		AccountDerivedFeatures accDerivedFeatures = new AccountDerivedFeatures();
		System.out.println("printing values");
		System.out.println(s);
		CreditCardTransactionEntity trans = new CreditCardTransactionEntity();
		accInfo.setAccountId(Long.valueOf(s[0]));
		accInfo.setName(s[1]);
		accInfo.setAge(Double.valueOf(s[2]));
		accInfo.setGender(s[3]);
		accInfo.setCardNumber(new Long(s[4]));
		accInfo.setCardType(s[5]);
		accInfo.setAnnualIncome(Double.valueOf(s[6]));
		accInfo.setEmailId(s[7]);
		accInfo.setPhoneNumber(s[8]);

		trans.setTransactionId(s[9]);
		trans.setAmount(Double.valueOf(s[10]));
		trans.setPosid(new Long(s[11]));
		trans.setDate(s[12]);
		trans.setDescription(s[13]);

		accDerivedFeatures.setTransactionInLastDay(Long.valueOf(s[14]));
		accDerivedFeatures.setAvgAmountSpendInLastDay(Double.valueOf(s[15]));
		accDerivedFeatures.setTransactionInLastWeek(Long.valueOf(s[16]));
		accDerivedFeatures.setAvgAmountSpendInLastWeek(Double.valueOf(s[17]));
		accDerivedFeatures.setTransactionInLastMonth(Long.valueOf(s[18]));
		accDerivedFeatures.setAvgAmountSpendInLastMonth(Double.valueOf(s[19]));

		enrichedData.setAccountInfo(accInfo);
		enrichedData.setAccountModelFeatures(accDerivedFeatures);
		enrichedData.setCreditCardTransaction(trans);

		return enrichedData;
	}

	
	public static PredictionInput convertToPredictionInput(EnrichedData enrichedData) throws ParseException {
		PredictionInput predictionInput = null;
		if (enrichedData != null) {
			predictionInput = new PredictionInput();
			if (enrichedData.getAccountInfo() != null) {
				predictionInput.setAge(new Double(enrichedData.getAccountInfo().getAge()));
				if (enrichedData.getAccountInfo().getGender() != null) {
					predictionInput
							.setGender(new Double(determineGenderCode(enrichedData.getAccountInfo().getGender())));
				}
				predictionInput.setAnnualIncome(enrichedData.getAccountInfo().getAnnualIncome());
				predictionInput.setCardNumber(new Double(enrichedData.getAccountInfo().getCardNumber()));
				predictionInput
						.setCardType(new Double(determineCardType(enrichedData.getAccountInfo().getCardNumber())));
				predictionInput.setAccountId(enrichedData.getAccountInfo().getAccountId());
			}
			if (enrichedData.getCreditCardTransaction() != null) {
				predictionInput.setTransactionId(enrichedData.getCreditCardTransaction().getTransactionId());
				predictionInput.setPosid(new Double(enrichedData.getCreditCardTransaction().getPosid()));
				predictionInput.setLocationCategory(
						new Double(determineLocationCategory(enrichedData.getCreditCardTransaction().getPosid())));
				predictionInput.setAmount(enrichedData.getCreditCardTransaction().getAmount());
				predictionInput.setTransactionTime(enrichedData.getCreditCardTransaction().getTransactionDate().getTime());
			}
			if (enrichedData.getAccountModelFeatures() != null) {
				predictionInput.setAvgTransactionAmtLastDay(
						enrichedData.getAccountModelFeatures().getAvgAmountSpendInLastDay());
				predictionInput.setAvgTransactionAmtLastMonth(
						enrichedData.getAccountModelFeatures().getAvgAmountSpendInLastMonth());
				predictionInput.setAvgTransactionAmtLastWeek(
						enrichedData.getAccountModelFeatures().getAvgAmountSpendInLastWeek());
				predictionInput.setCountTransactionsLastDay(
						new Double(enrichedData.getAccountModelFeatures().getTransactionInLastDay()));
				predictionInput.setCountTransactionsLastMonth(
						new Double(enrichedData.getAccountModelFeatures().getTransactionInLastMonth()));
				predictionInput.setCountTransactionsLastWeek(
						new Double(enrichedData.getAccountModelFeatures().getTransactionInLastWeek()));
			}
		}
		return predictionInput;
	}
	
	private static Integer determineLocationCategory(Long posId) {
		int catType = 0;
		if(posId>=1234 && posId<=1237){
			catType=0;
		}
		else if(posId>=1238 && posId<=1245){
			catType=1;
		}
		else if (posId % 2 == 0) {
			catType = 1;
		}
		return catType;
	}

}
