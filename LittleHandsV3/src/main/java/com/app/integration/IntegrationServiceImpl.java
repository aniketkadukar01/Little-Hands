package com.app.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class IntegrationServiceImpl implements IntegrationService{
	
	 @Autowired
	    private JavaMailSender javaMailSender;

	    public void sendSignUpConfirmationEmail(String email) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject("Successful Sign-Up Notification");
	        message.setText("Dear user,\n\nYou have successfully signed up for your account PRO-HIRE.\n\nThank you,\nYour Company");

	        javaMailSender.send(message);
	    }
}
