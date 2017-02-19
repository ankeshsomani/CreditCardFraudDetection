package com.masteklabs.app;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHelper {
	static Properties props;
	static {
		System.out.println("in this block 1");
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
	}

	public static void sendMail(String recipientMailAddr, String subject, String msgBody) {
		System.out.println("in this block 2");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("frommailid", "password");// change
																							// accordingly
			}
		});

		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("frommailid"));// change
																			// accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientMailAddr));
			message.setSubject(subject);
			message.setText(msgBody);

			// send message
			Transport.send(message);

			System.out.println("message sent successfully");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {
		String recipientMailAddr = "tomailid";// change
																// accordingly
		sendMail(recipientMailAddr, "elloi", "This is a test message3");

	}
}
