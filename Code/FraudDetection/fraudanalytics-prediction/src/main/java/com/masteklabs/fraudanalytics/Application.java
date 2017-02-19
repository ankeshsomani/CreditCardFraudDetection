package com.masteklabs.fraudanalytics;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.masteklabs.fraudanalytics.streaming.SparkFraudDetection;
import com.masteklabs.frauddetection.common.CommonConstants;

public class Application {

	private static ApplicationContext springContext;
	private static Properties kafkaProps;
	private static Map<String, String> kafkaParams;
	private static JavaStreamingContext streamingContext;
	static Logger log = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) throws InterruptedException {

		SparkConf sparkConf = new SparkConf().setAppName(CommonConstants.APP_NAME);
		sparkConf.set("spark.driver.allowMultipleContexts", "true");

		springContext = new AnnotationConfigApplicationContext(AppConfig.class);
		
		kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", CommonConstants.KAFKA_BROKER_URL);
		kafkaProps = setKafkaPrpoerties();
		// Get streaming context object for feature derivation job
		streamingContext = new JavaStreamingContext(sparkConf,
				Durations.seconds(CommonConstants.BATCH_FREQUENCY_FEATURE_DERIVATION));

		SparkSession sparkSession = new SparkSession(streamingContext.sparkContext().sc());

		log.warn("Feature Derivation process starts:-");
		// init feature derivation process
		SparkFraudDetection.initFeatureDerivationProcess(springContext,streamingContext, kafkaProps);

		log.warn("prediction process starts:-");
		SparkFraudDetection.initPredictionProcess(springContext,sparkSession, streamingContext, kafkaProps);

		// Start the computation
		streamingContext.start();
		// streamingContextPrediction.start();
		streamingContext.awaitTermination();
		// streamingContextPrediction.awaitTermination();
	}

	private static Properties setKafkaPrpoerties() {
		Properties props = new Properties();
		props.put("bootstrap.servers", CommonConstants.KAFKA_BROKER_URL);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return props;
	}
}
