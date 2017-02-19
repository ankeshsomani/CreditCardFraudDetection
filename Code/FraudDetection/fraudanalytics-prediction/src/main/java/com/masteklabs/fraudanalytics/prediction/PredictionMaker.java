package com.masteklabs.fraudanalytics.prediction;

import com.masteklabs.fraudanalytics.common.dto.PredictionResult;
import com.masteklabs.fraudanalytics.prediction.dto.EnrichedData;

public interface PredictionMaker {
	
	public PredictionResult predict(EnrichedData enrichedData);

}
