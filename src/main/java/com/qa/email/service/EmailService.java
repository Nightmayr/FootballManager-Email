package com.qa.email.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	@Value("${gmail.host}")
	private String smtpHost;
	
	@Value("${gmail.port}")
	private String smtpPort;
	
	@Value("${sender.email}")
	private String senderEmail;
	
	@Value("${sender.password}")
	private String senderPassword;
	
	@Value("${recipient.email}")
	private String recieveEmail;
	
	@Value("${email.settitle}")
	private String emailTitle;
	
	@Value("${email.setcontent}")
	private String emailContent;
	
	
	
	public void sendmail() throws AddressException, MessagingException, IOException {
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", smtpHost);
		   props.put("mail.smtp.port", smtpPort);
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(senderEmail, senderPassword);
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress(senderEmail, false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recieveEmail));
		   msg.setSubject(emailTitle);
		   msg.setContent(emailContent, "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent(emailContent, "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

//		   attachPart.attachFile("/var/tmp/image19.png");
//		   multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);
		   Transport.send(msg);   
		}
}
