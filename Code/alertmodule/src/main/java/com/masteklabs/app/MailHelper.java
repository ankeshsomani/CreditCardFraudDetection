package com.masteklabs.app;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailHelper {
        static final Logger log = Logger.getLogger(MailHelper.class.getName());
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
        public static final String FROM_MAIL_ADDR="somanireyansh@gmail.com";
        public static final String FROM_MAIL_ADDR_PWD="ankesh007#";

        public static void sendMail(String recipientMailAddr, String subject, String msgBody) {
                log.warn("in this block 2");
                Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(FROM_MAIL_ADDR, FROM_MAIL_ADDR_PWD);// change
                                                                                                                                                                                        // accordingly
                        }
                });

                // compose message
                try {
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(FROM_MAIL_ADDR));// change
                                                                                                                                                        // accordingly
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientMailAddr));
                        message.setSubject(subject);
                        message.setText(msgBody);

                        // send message
                        Transport.send(message);

                        log.warn("message sent successfully");

                } catch (MessagingException e) {
                        throw new RuntimeException(e);
                }

        }

}
