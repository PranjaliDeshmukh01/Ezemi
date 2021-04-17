package com.ezemi.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.ezemi.service.EmailService;

@Service
public class EmailSeriveImpl implements EmailService {

	@Autowired
	private MailSender mailSender;
	
	@Override
	public void sendEmail(String email, String text, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ezzemi@outlook.com");
		message.setTo(email);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);

	}

}
