package com.masteklabs.app;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class AlertProvider {
	
	
	public static void main(String[] args) {
		Properties props = new Properties();
	     props.put("bootstrap.servers", "localhost:9092");
	     props.put("group.id", "test");
	     props.put("enable.auto.commit", "true");
	     props.put("auto.commit.interval.ms", "1000");
	     props.put("session.timeout.ms", "30000");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
	     consumer.subscribe(Arrays.asList("testing3"));
	     while (true) {
	         ConsumerRecords<String, String> records = consumer.poll(100);
	         for (ConsumerRecord<String, String> record : records)
	             processRecord(record);
	     }
		
	}
	
	private static void processRecord(ConsumerRecord<String, String> record){
		
		String message=createMessage(record);
		String subject="ALERT:Suspicious Transaction";
		String recipientMailAddr=retrieveRecipientAddress();
		
		MailHelper.sendMail(recipientMailAddr, subject, message);
		
		
		
	}

	private static String retrieveRecipientAddress() {
		// TODO Auto-generated method stub
		return "tomailid";
	}

	private static String createMessage(ConsumerRecord<String, String> record) {
		String key=record.key();
		if(key == null){
			System.out.println("assigning value");
			key=record.value();
		}
		StringBuilder out=new StringBuilder();
		out.append("Hello, We have received a suspicious transaction from your account.The transaction reference number is "+key+".You will require OTP 123456 to approve the transaction");
		return out.toString();
	}

}
