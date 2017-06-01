package com.masteklabs.app;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import org.apache.log4j.Logger;

public class SMSHelper {
    static final Logger log = Logger.getLogger(SMSHelper.class.getName());
	static String ACCESS_KEY = "AKIAJDI7SYU2FGLNWNOQ", SECRET_KEY = "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";
	static String TOPIC_ARN = "arn:aws:sns:us-west-2:679279306327:feedback-feedback-dev";

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		String message = "Demo SMS message body";
		String phoneNumber = "+9769649218";
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<String, MessageAttributeValue>();
		sendSMSMessage( message, phoneNumber, smsAttributes);
	}

	private static void sendSMSMessage( String message, String phoneNumber) {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation");
		try{
		AmazonSNSClient snsClient = new AmazonSNSClient(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
		snsClient.withRegion(Region.getRegion(Regions.US_WEST_2));
		PublishRequest publishRequest = new PublishRequest();
		publishRequest.withMessage(message).withPhoneNumber(phoneNumber);//.withMessageAttributes(smsAttributes);
		PublishResult result = snsClient.publish(publishRequest);
		//System.out.println(result); // Prints the message ID.
		log.warn("message sent successfully to "+phoneNumber);
		} catch (Exception e) {
                        throw new RuntimeException(e);
                }
	}

}