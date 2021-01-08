package com.incture.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailClass {

	public void sendOtp(String otp, String email) {
		final String username = "tester186463@gmail.com";
		final String password = "qwert.1234";
		String toEmail = email;
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		if (!username.contains("gmail.com"))
			properties.put("mail.smtp.host", "smtp.office365.com"); // if sender uses
																	// microsoft
																	// mail
		else
			properties.put("mail.smtp.host", "smtp.gmail.com"); // if sender
																// using
																// gmail.com
		properties.put("mail.smtp.port", "587");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		// Start our mail message
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(username));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("OTP for Email Verification");
			msg.setText("Otp for your email verification is " + otp);

			Transport.send(msg);
			System.out.println("Sent message");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		}

}
