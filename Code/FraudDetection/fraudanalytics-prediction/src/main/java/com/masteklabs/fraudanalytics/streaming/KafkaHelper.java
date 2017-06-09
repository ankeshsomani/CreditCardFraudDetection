package com.masteklabs.fraudanalytics.streaming;

import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.masteklabs.fraudanalytics.common.dto.PredictionResult;
import com.masteklabs.fraudanalytics.mysql.repo.SuspectedTransactionRepo;
import com.masteklabs.fraudanalytics.redis.repo.AccountInfoRepo;
import com.masteklabs.fraudanalytics.redis.repo.CreditCardTransactionRepo;
import com.masteklabs.frauddetection.common.CommonConstants;
import com.masteklabs.frauddetection.common.CommonUtils;
import com.masteklabs.frauddetection.entity.AccountInfoEntity;
import com.masteklabs.frauddetection.entity.SuspectedTransactionEntity;

import scala.Tuple2;

@Component("kafkaHelper")
public class KafkaHelper {
	static Logger log = Logger.getLogger(KafkaHelper.class.getName());

	@Autowired
	@Qualifier("accountInfoRepo")
	private  AccountInfoRepo accountInfoRepo;
	
	public  static <T> void writeObjectRddToKafkaQueue(Properties kafkaProps, JavaRDD<T> objectRdd, String queueName) {
		if (objectRdd != null) {
			KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);
			List<T> result = objectRdd.collect();

			for (int i = 0; i < result.size(); i++) {
				Object object = result.get(i);
				ProducerRecord<String, String> record = new ProducerRecord<>(queueName, object.toString());
				producer.send(record);

			}
			producer.close();
		}

	}

	public  static <T> void writeObjectRddPairToKafkaQueue(ApplicationContext appContext,Properties kafkaProps, JavaPairRDD<String, PredictionResult> predictionResult, String queueName) throws ParseException {
		if (predictionResult != null) {
			KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);
			log.debug("count of records for testing3" + predictionResult.count());
			List<Tuple2<String, PredictionResult>> result = predictionResult.collect();

			for (int i = 0; i < result.size(); i++) {
				log.debug("writing record in testing3.Record number:-" + (i + 1));
				Tuple2<String, PredictionResult> tuple = result.get(i);
				PredictionResult predResult = tuple._2();
				StringBuilder out=new StringBuilder();
				Long accId=predResult.getAccountId();
				
				
				AccountInfoRepo accountInfoRepo=(AccountInfoRepo) appContext.getBean("accountInfoRepo");
				AccountInfoEntity accInfo=accountInfoRepo.find(accId);
				out.append(predResult.getUniqueIdentifier());out.append(CommonConstants.TRANSACTION_DELIMETER);
				out.append(predResult.getPrediction());out.append(CommonConstants.TRANSACTION_DELIMETER);	
				out.append(accInfo.getEmailId());out.append(CommonConstants.TRANSACTION_DELIMETER);
				out.append(accInfo.getPhoneNumber());
				log.warn("Before deleting message");
				//TODO:TO remove later 
				int predictedVal=new Double(predResult.getPrediction()).intValue();
				if(predictedVal==1){
					SuspectedTransactionEntity suspectedTransactionEntity= new SuspectedTransactionEntity();
					suspectedTransactionEntity.setAccountId(predResult.getAccountId());
					suspectedTransactionEntity.setDate(CommonUtils.getStringFromDateTime("dd/MM/yyyy HH:mm:ss",predResult.getTransactionTime()));
					suspectedTransactionEntity.setTransactionId(predResult.getUniqueIdentifier());
					suspectedTransactionEntity.setFraudStatus(0);
					suspectedTransactionEntity.setAmount(predResult.getAmount());
					suspectedTransactionEntity.setCardNumber(predResult.setCardNumber());
					SuspectedTransactionRepo suspectedTransRepo=(SuspectedTransactionRepo)appContext.getBean("suspectedTransactionRepo");
					suspectedTransRepo.save(suspectedTransactionEntity);
				}
				ProducerRecord<String, String> record = new ProducerRecord<>(queueName, out.toString());
				producer.send(record);

			}
			producer.close();
		}

	}

	private static void deleteTransactionFromRedis(ApplicationContext springContext, PredictionResult predResult) {
		CreditCardTransactionRepo creditCardTransactionRepo = (CreditCardTransactionRepo) springContext
				.getBean("transRepository");
		Long remRecords=creditCardTransactionRepo.removeByScore(CommonConstants.TRANS_PREFIX+predResult.getAccountId(), predResult.getTransactionTime(), predResult.getTransactionTime());
		log.warn("removed "+ remRecords+" fraud records for trans:-"+predResult.getUniqueIdentifier());
		
	}

}
