package com.masteklabs.frauddetection.common;

import java.util.regex.Pattern;

public class CommonConstants {

	public static class TimeZoneConstants {
		
		/** The Constant UTCTIMEZONE. */
		public static final String UTCTIMEZONE = "UTC";
		
		/** The Constant LONDONTIMEZONE. */
		public static final String LONDONTIMEZONE ="Europe/London";
		
		private TimeZoneConstants(){
			
		}
	}
	public static final Pattern COMMA_REGEX = Pattern.compile(",");
	public static final long BATCH_FREQUENCY_FEATURE_DERIVATION = 20;
	public static final String COMMA = ",";
	public static final String TRANSACTION_DELIMETER = ",";
	public static final String PREDICTED_RESULTS_TOPIC = "testing3";
	public static final String ENRICHED_EVENTS_TOPIC = "testing2";
	public static final String RAW_EVENTS_TOPIC = "testing1";
	public static final String KAFKA_BROKER_URL = "localhost:9092";
	public static final long BATCH_FREQUENCY_PREDICTION = 20;
	public static final String FRAUD = "fraud";
	public static final String NOT_FRAUD = "NotFraud";
	public static final String APP_NAME = "SparkFraudDetection";
	public static final String HDFS_STORAGE_ENRICHED_DATA = "hdfs://localhost:54310/FraudDetection/EnrichedData/";
	public static final String TRAINING_MODEL_LOCATION="/data/masteklabs/TrainingModel/spk";
	//public static final String HDFS_STORAGE_PREDICTION_INPUT2 = "hdfs://localhost:54310/FraudDetection/PredictionInput2";
	public static final String REDIS_HOST = "localhost";
	public static final String REDIS_PASSWORD = "masteklabs";
	public static final String MYSQL_JDBC_DRIVER="com.mysql.jdbc.Driver";
	public static final String MYSQL_JDBC_URL="jdbc:mysql://localhost/fraudanalytics";
	public static final int MYSQL_INITIAL_POOL_SIZE=1;
	public static final int MYSQL_MAX_POOL_SIZE=10;
	public static final String MYSQL_USERNAME="root";
	public static final String MYSQL_PASSWORD="mastek";
	public static final int REDIS_PORT = 6379;
	public static final int REDIS_POOL_CONNECTIONS = 128;
	public static final String TRANS_PREFIX = "TRANS_";
	public static final Long MILLI_SECONDS_IN_HOUR = (long) 3600000;
	public static final Long MILLI_SECONDS_IN_DAY = (long) MILLI_SECONDS_IN_HOUR * 24;
	public static final Long MILLI_SECONDS_IN_WEEK = (long) MILLI_SECONDS_IN_DAY * 7;
	public static final Long MILLI_SECONDS_IN_MONTH = 18144000000l;
}
