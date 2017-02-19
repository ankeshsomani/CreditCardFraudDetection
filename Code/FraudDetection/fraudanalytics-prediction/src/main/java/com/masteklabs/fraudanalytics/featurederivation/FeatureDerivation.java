package com.masteklabs.fraudanalytics.featurederivation;

import org.apache.log4j.Logger;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.ml.regression.GBTRegressionModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.stereotype.Component;

import com.masteklabs.frauddetection.common.CommonConstants;


@Component("featureDerivation")
public class FeatureDerivation {
	static Logger log = Logger.getLogger(FeatureDerivation.class.getName());

	public static Dataset<Row> detectFraud(Dataset<Row> features) {
		
		
		VectorAssembler assembler = new VectorAssembler()
				.setInputCols(new String[] { "age", "amount", "annualIncome", "avgTransactionAmtLastDay",
						"avgTransactionAmtLastMonth", "avgTransactionAmtLastWeek", "cardType",
						"countTransactionsLastDay", "countTransactionsLastMonth", // "cardNumber",
						"countTransactionsLastWeek", "gender", "locationCategory", "posid" })
				.setOutputCol("features");
		
		Dataset<Row> inputfeatures_feature = assembler.transform(features);
		inputfeatures_feature.show();

		if (features != null) {
			log.warn("before loading the model");
			//LogisticRegressionModel model =
			///LogisticRegressionModel.load(CommonConstants.TRAINING_MODEL_LOCATION);
			log.warn("before loading the model");
			GBTRegressionModel model = GBTRegressionModel.load(CommonConstants.TRAINING_MODEL_LOCATION);
			log.warn("after loading the model");
			features = model.transform(inputfeatures_feature);
			log.warn("after executing the model");
		}
		return features;
	}
	
	

}
