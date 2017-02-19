package com.masteklabs.fraudanalytics.streaming;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.common.dto.PredictionInput;
import com.masteklabs.fraudanalytics.common.dto.PredictionResult;
import com.masteklabs.fraudanalytics.featurederivation.FeatureDerivation;
import com.masteklabs.fraudanalytics.featurederivation.FeatureDerivationUtils;
import com.masteklabs.fraudanalytics.prediction.dto.AccountDerivedFeatures;
import com.masteklabs.fraudanalytics.prediction.dto.EnrichedData;
import com.masteklabs.fraudanalytics.redis.repo.AccountInfoRepo;
import com.masteklabs.fraudanalytics.redis.repo.CreditCardTransactionRepo;
import com.masteklabs.frauddetection.common.CommonConstants;
import com.masteklabs.frauddetection.common.DateUtils;
import com.masteklabs.frauddetection.entity.AccountInfoEntity;
import com.masteklabs.frauddetection.entity.CreditCardTransactionEntity;

import kafka.serializer.StringDecoder;
import scala.Tuple2;

@Component("sparkFraudDetection")
public class SparkFraudDetection implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static CreditCardTransactionRepo creditCardTransactionRepo;
	
	private static AccountInfoRepo accountInfoRepo;
	
	private static Map<String, String> kafkaParams;
	static Logger log = Logger.getLogger(SparkFraudDetection.class.getName());



	public  static void initPredictionProcess(final ApplicationContext appContext,final SparkSession sparkSession,
			JavaStreamingContext streamingContextPrediction,final Properties kafkaProps) {
		kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", CommonConstants.KAFKA_BROKER_URL);
		creditCardTransactionRepo=(CreditCardTransactionRepo) appContext.getBean("creditCardTransactionRepo");
		accountInfoRepo=(AccountInfoRepo) appContext.getBean("accountInfoRepo");
		
		Set<String> topicsSet = new HashSet<>(
				Arrays.asList(CommonConstants.ENRICHED_EVENTS_TOPIC.split(CommonConstants.COMMA)));
		// Create direct kafka input stream with brokers and topics
		JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(streamingContextPrediction,
				String.class, String.class, StringDecoder.class, StringDecoder.class, kafkaParams, topicsSet);

		// Get the messages RDD and create a RDD of EnrichedData
		JavaDStream<EnrichedData> enrichedData = messages.map(new Function<Tuple2<String, String>, EnrichedData>() {

			private static final long serialVersionUID = 1L;

			@Override
			public EnrichedData call(Tuple2<String, String> tuple2) {
				return FeatureDerivationUtils.createAggregateInput(tuple2._2());

			}
		});

		// Save the Enriched Data in HDFS
		enrichedData.foreachRDD(new VoidFunction<JavaRDD<EnrichedData>>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void call(JavaRDD<EnrichedData> enrichedData) throws Exception {
				log.warn("checking enriched data size.It is :-"+enrichedData.count());
				if (!enrichedData.isEmpty()) {
					log.warn("Before Writing to hadoop records :-"+enrichedData.count());
					String currTime = "" + DateUtils.getCurrentTimeStamp();
					// Save the data in hadoop
					enrichedData.saveAsTextFile(CommonConstants.HDFS_STORAGE_ENRICHED_DATA + currTime);
					log.warn("After Writing to hadoop");
					//log.warn("Before Writing to File records :-"+enrichedData.count());
					//enrichedData.saveAsTextFile("/data/masteklabs/out/batch_"+currTime);
					//log.warn("Before Writing to File");
				}

			}
		});

		// Convert to PredictionInputRDD
		JavaDStream<PredictionInput> predictionInputStream = enrichedData
				.map(new Function<EnrichedData, PredictionInput>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public PredictionInput call(EnrichedData enrichedData) throws Exception {
						PredictionInput predInput = FeatureDerivationUtils.convertToPredictionInput(enrichedData);
						return predInput;
					}

				});

		JavaPairDStream<String, PredictionResult> predictionResultStream = predictionInputStream
				.transformToPair(new Function<JavaRDD<PredictionInput>, JavaPairRDD<String, PredictionResult>>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public JavaPairRDD<String, PredictionResult> call(JavaRDD<PredictionInput> predictionInput)
							throws Exception {

						Dataset<Row> rowDataSet = sparkSession.createDataFrame(predictionInput, PredictionInput.class);
						log.warn("Before showing records " + rowDataSet.count());
						rowDataSet.show();
						log.warn("After showing");
						Dataset<Row> rowDataPredictation = FeatureDerivation.detectFraud(rowDataSet);
						log.warn("Before showing predicted records " + rowDataSet.count());
						rowDataPredictation.show();
						log.warn("After showing predicted records " + rowDataSet.count());
						JavaRDD<Row> predictRdd = rowDataPredictation.toJavaRDD();
						log.warn("predictRdd count is " + predictRdd.count());
						JavaPairRDD<String, PredictionResult> out = predictRdd
								.mapToPair(new PairFunction<Row, String, PredictionResult>() {

									/**
									 * 
									 */
									private static final long serialVersionUID = 1L;

									@Override
									public Tuple2<String, PredictionResult> call(Row t) throws Exception {
										PredictionResult result = new PredictionResult();
										log.warn("inside transformation");
										result.setUniqueIdentifier(t.get(15).toString());
										result.setTransactionTime(new Long(t.get(16).toString()));
										result.setPrediction(t.get(18).toString());
										result.setAccountId(new Long(t.get(0).toString()));
										Tuple2<String, PredictionResult> tuple = new Tuple2<String, PredictionResult>(
												t.get(17).toString(), result);
										return tuple;
									}

								});
						return out;

					}

				});

		log.warn("count of predictionResultStream is " + predictionResultStream.count());
		log.warn("printing its results ");
		predictionResultStream.print();
		predictionResultStream.foreachRDD(new VoidFunction<JavaPairRDD<String, PredictionResult>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void call(JavaPairRDD<String, PredictionResult> predictionResult) throws Exception {
				
				
				log.warn("before writing to testing3");
				KafkaHelper.writeObjectRddPairToKafkaQueue(appContext,kafkaProps,predictionResult,
						CommonConstants.PREDICTED_RESULTS_TOPIC);
			}

		});
		log.warn("printing enrichedData start");
		enrichedData.print();
		log.warn("printing enrichedData end");

	}

	public  static void initFeatureDerivationProcess(final ApplicationContext appContext,JavaStreamingContext streamingContextFeatureDerivation,final Properties kafkaProps) {

		String topics = CommonConstants.RAW_EVENTS_TOPIC;
		Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(CommonConstants.COMMA)));
		creditCardTransactionRepo=(CreditCardTransactionRepo) appContext.getBean("creditCardTransactionRepo");
		accountInfoRepo=(AccountInfoRepo) appContext.getBean("accountInfoRepo");
		kafkaParams = new HashMap<>();
		kafkaParams.put("metadata.broker.list", CommonConstants.KAFKA_BROKER_URL);
		// Create direct kafka input stream with brokers and topics
		JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(streamingContextFeatureDerivation,
				String.class, String.class, StringDecoder.class, StringDecoder.class, kafkaParams, topicsSet);

				
		// Get the messages RDD and create a RDD of CreditCardTransaction
		JavaDStream<CreditCardTransactionEntity> transactions = messages
				.map(new Function<Tuple2<String, String>, CreditCardTransactionEntity>() {

					

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public CreditCardTransactionEntity call(Tuple2<String, String> tuple2) throws ParseException {
						return createAndInsertCardTransaction(tuple2._2());

					}
				});

		// Get the transactions RDD and create a RDD of derived features
		JavaDStream<EnrichedData> enrichedData = transactions.map(new  Function<CreditCardTransactionEntity, EnrichedData>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public EnrichedData call(CreditCardTransactionEntity transaction) throws Exception {
				return deriveAccountModelFeatures(transaction);
			}

		});

		

		enrichedData.foreachRDD(new VoidFunction<JavaRDD<EnrichedData>>() {

			

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void call(JavaRDD<EnrichedData> enrichedData) throws Exception {
				KafkaHelper.writeObjectRddToKafkaQueue(kafkaProps, enrichedData, CommonConstants.ENRICHED_EVENTS_TOPIC);

			}

		});

		log.warn("enrichedData is:-");
		enrichedData.print();
	}
	public static CreditCardTransactionEntity createAndInsertCardTransaction(String paramInput) throws ParseException {
		// Form the credit card transaction object
		CreditCardTransactionEntity cardTrans = new CreditCardTransactionEntity();
		
		String s[] = CommonConstants.COMMA_REGEX.split(paramInput);
		cardTrans.setAccountId(new Long(s[0]));
		cardTrans.setTransactionId(s[1]);
		cardTrans.setPosid(new Long(s[2]));
		cardTrans.setDate(s[3]);
		cardTrans.setDescription(s[4]);
		if (s[5] != null && !(s[5].equals(""))) {
			cardTrans.setAmount(new Double(s[5]));
		}
		
		StringBuilder trans = new StringBuilder();
		trans.append(cardTrans.getTransactionId());
		trans.append(CommonConstants.TRANSACTION_DELIMETER);
		trans.append(cardTrans.getAmount());
		String accId = CommonConstants.TRANS_PREFIX + cardTrans.getAccountId();
		creditCardTransactionRepo.save(accId, trans.toString(), cardTrans.getTransactionDate().getTime());
		return cardTrans;
	}
	public  static EnrichedData deriveAccountModelFeatures(
			CreditCardTransactionEntity transaction) {
		EnrichedData aggregateInput = new EnrichedData();
		AccountDerivedFeatures accDerivedFeatures = new AccountDerivedFeatures();
		
		AccountInfoEntity accInfo = accountInfoRepo.find(transaction.getAccountId());

		accDerivedFeatures.setAccountId(transaction.getAccountId());
		Long transactionsInLastHour = retreiveTransactionsDoneInGivenTime(transaction.getAccountId(),
				CommonConstants.MILLI_SECONDS_IN_HOUR);
		Long transactionsInLastDay = retreiveTransactionsDoneInGivenTime(transaction.getAccountId(),
				CommonConstants.MILLI_SECONDS_IN_DAY);
		Long transactionsInLastWeek = retreiveTransactionsDoneInGivenTime(transaction.getAccountId(),
				CommonConstants.MILLI_SECONDS_IN_WEEK);
		Long transactionsInLastMonth = retreiveTransactionsDoneInGivenTime(transaction.getAccountId(),
				CommonConstants.MILLI_SECONDS_IN_MONTH);

		
		Double amtSpendInLastHour = retreiveAmountSpendInGivenTime(transaction.getAccountId(),
				CommonConstants.MILLI_SECONDS_IN_HOUR);
		Double amtSpendInLastDay = retreiveAmountSpendInGivenTime(transaction.getAccountId(), 
				CommonConstants.MILLI_SECONDS_IN_DAY);
		Double amtSpendInLastWeek = retreiveAmountSpendInGivenTime(transaction.getAccountId(),
				CommonConstants.MILLI_SECONDS_IN_WEEK);
		Double amtSpendInLastMonth = retreiveAmountSpendInGivenTime(transaction.getAccountId(),
				CommonConstants.MILLI_SECONDS_IN_MONTH);
		
		log.warn("amtSpendInLastDay for transaction "+transaction.getTransactionId() +"is:- "+ amtSpendInLastDay);
		log.warn("amtSpendInLastWeek for transaction "+transaction.getTransactionId() +"is:- "+ amtSpendInLastWeek);
		log.warn("amtSpendInLastMonth for transaction "+transaction.getTransactionId() +"is:- "+ amtSpendInLastMonth);
		
		log.warn("transactionsInLastDay for transaction "+transaction.getTransactionId() +"is:- "+ transactionsInLastDay);		
		log.warn("transactionsInLastWeek for transaction "+transaction.getTransactionId() +"is:- "+ transactionsInLastWeek);
		log.warn("transactionsInLastMonth for transaction "+transaction.getTransactionId() +"is:- "+ transactionsInLastMonth);
		
		Double avgAmtSpendInLastHour = (transactionsInLastHour == 0) ? 0 : (amtSpendInLastHour / transactionsInLastHour);
		Double avgAmtSpendInLastDay = (transactionsInLastDay == 0) ? 0 : (amtSpendInLastDay / transactionsInLastDay);
		Double avgAmtSpendInLastWeek = (transactionsInLastWeek == 0) ? 0 : (amtSpendInLastWeek / transactionsInLastWeek);
		Double avgAmtSpendInLastMonth = (transactionsInLastMonth == 0) ? 0: (amtSpendInLastMonth / transactionsInLastMonth);

		accDerivedFeatures.setAvgAmountSpendInLastHour(avgAmtSpendInLastHour);
		accDerivedFeatures.setTransactionInLastHour(transactionsInLastHour);
		accDerivedFeatures.setAvgAmountSpendInLastDay(avgAmtSpendInLastDay);

		accDerivedFeatures.setTransactionInLastDay(transactionsInLastDay);

		accDerivedFeatures.setAvgAmountSpendInLastWeek(avgAmtSpendInLastWeek);

		accDerivedFeatures.setTransactionInLastWeek(transactionsInLastWeek);

		accDerivedFeatures.setAvgAmountSpendInLastMonth(avgAmtSpendInLastMonth);

		accDerivedFeatures.setTransactionInLastMonth(transactionsInLastMonth);

		aggregateInput.setAccountInfo(accInfo);
		aggregateInput.setAccountModelFeatures(accDerivedFeatures);
		aggregateInput.setCreditCardTransaction(transaction);
		return aggregateInput;
	}

	private static Double retreiveAmountSpendInGivenTime(Long accountId, Long timeDifference) {

		log.warn("start of retreiveAmountSpendInGivenTime foraccountId " + accountId + " timeDifference "
				+ timeDifference);

		Long currentTime = DateUtils.getCurrentTimeStamp();
		Double sum = (double) 0;
		
		Set<Object> transactions = creditCardTransactionRepo.rangeByScore(CommonConstants.TRANS_PREFIX + accountId,
				(currentTime - timeDifference), currentTime);
		for (Object transaction : transactions) {
			String attr[] = ((String) transaction).split(CommonConstants.TRANSACTION_DELIMETER);
			sum = sum + new Double(attr[1]);
		}
		log.warn("result is " + sum + " for ::" + CommonConstants.TRANS_PREFIX + accountId + " ::"
				+ (currentTime - timeDifference) + "::" + currentTime);
		return sum;
	}

	private static Long retreiveTransactionsDoneInGivenTime(Long accountId, Long timeDifference) {
		log.warn("start of retreiveTransactionsDoneInGivenTime foraccountId " + accountId + " timeDifference "
				+ timeDifference);
		
		Long currentTime = DateUtils.getCurrentTimeStamp();
		Long Count = creditCardTransactionRepo.countTransactions(CommonConstants.TRANS_PREFIX + accountId,
				(currentTime - timeDifference), currentTime);
		log.warn("result is " + Count + " for ::" + CommonConstants.TRANS_PREFIX + accountId + " ::"
				+ (currentTime - timeDifference) + "::" + currentTime);
		return Count;
	}

}
