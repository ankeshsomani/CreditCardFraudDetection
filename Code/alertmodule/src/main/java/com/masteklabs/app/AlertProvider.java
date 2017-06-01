package com.masteklabs.app;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

public class AlertProvider {
        static final Logger log = Logger.getLogger(AlertProvider.class.getName());
        public static final Pattern COMMA_REGEX = Pattern.compile(",");
        public static final String PREDICTED_EVENTS_TOPIC="testing3";
        public static final int POLLING_DURATION=100;
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
             try{
                     consumer.subscribe(Arrays.asList(PREDICTED_EVENTS_TOPIC));
                     while (true) {
                         ConsumerRecords<String, String> records = consumer.poll(POLLING_DURATION);
                         for (ConsumerRecord<String, String> record : records)
                             processRecord(record);
                     }
             }
             finally{
                 consumer.close();
             }

        }

        private static void processRecord(ConsumerRecord<String, String> record){

                String message=createMessage(record);
                String subject="ALERT:Suspicious Transaction";
                String recipientMailAddr=retrieveRecipientAddress(record.value());
                String prediction=COMMA_REGEX.split(record.value())[1];
                System.out.println("printing ");

                System.out.println(prediction);

//              if(prediction.equals("0.0")){
//                      recipientMailAddr="ankesh.somani@mastek.com";
//              }
//              else{
//                      recipientMailAddr="abhishek.murkute@mastek.com";
//              }
//              System.out.println("sending mail to "+recipientMailAddr);
 if(prediction.equals("1.0")){

                MailHelper.sendMail(retrieveRecipientAddress(record.value()), subject, message);
}
        }

        private static String retrieveRecipientAddress(String message) {
                return COMMA_REGEX.split(message)[2];
        }

        private static String createMessage(ConsumerRecord<String, String> record) {
                String key=record.key();
                if(key == null){
                        System.out.println("assigning value");
                        key=record.value();
                }
                StringBuilder out=new StringBuilder();
                String transRefNumber=COMMA_REGEX.split(key)[0];
                out.append("Hello, We have received a suspicious transaction from your account.The transaction reference number is "+transRefNumber+".You will require OTP 123456 to approve the transaction");
                return out.toString();
        }

}
