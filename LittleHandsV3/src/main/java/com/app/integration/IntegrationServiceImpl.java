package com.app.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class IntegrationServiceImpl implements IntegrationService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendSignUpConfirmationEmail(String email) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Welcome to Little Hands - Baby Vaccination Service");
		message.setText(
				"Dear Parent,\\n\\nThank you for signing up with Little Hands, your trusted partner in baby vaccination.We are dedicated to providing the best care for your little one and ensuring they receive timely vaccinations for a healthy start.\\n\\nIf you have any questions or need further assistance, please feel free to reach out to us. \\n\\nThank you for choosing Little Hands!\\n\\nBest regards,\\nThe Little Hands Team");

		javaMailSender.send(message);
	}
}
