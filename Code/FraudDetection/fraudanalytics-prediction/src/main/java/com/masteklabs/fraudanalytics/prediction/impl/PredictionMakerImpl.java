package com.masteklabs.fraudanalytics.prediction.impl;

import com.masteklabs.fraudanalytics.common.dto.PredictionResult;
import com.masteklabs.fraudanalytics.prediction.PredictionMaker;
import com.masteklabs.fraudanalytics.prediction.dto.EnrichedData;
import com.masteklabs.frauddetection.common.CommonConstants;

public class PredictionMakerImpl implements PredictionMaker{

	public PredictionResult predict(EnrichedData enrichedData) {
		PredictionResult result=new PredictionResult();
		if(enrichedData !=null && enrichedData.getCreditCardTransaction() !=null){
			result.setUniqueIdentifier(enrichedData.getCreditCardTransaction().getTransactionId());
			if(enrichedData.getCreditCardTransaction().getAmount() %2==0){
				result.setPrediction(CommonConstants.FRAUD);
			}
			else{
				result.setPrediction(CommonConstants.NOT_FRAUD);
			}
		}
		else{
			result.setUniqueIdentifier("dummy");
			result.setPrediction(CommonConstants.NOT_FRAUD);
		}

		return result;
	}



}
