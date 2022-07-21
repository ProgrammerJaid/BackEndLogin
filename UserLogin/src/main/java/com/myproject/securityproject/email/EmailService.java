package com.myproject.securityproject.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailSender {

	private JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	// private final static Logger LOGGER =
	// org.slf4j.LoggerFactory.getLogger(EmailService.class);

	@Override
	@Async
	public void send(String to, String email) {
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, "UTF-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email to login.");
			helper.setFrom("skzaid9641@gmail.com");
			mailSender.send(msg);

		} catch (MessagingException e) {
			// LOGGER.error("failed to send email",e);
			throw new IllegalStateException("Email not sent.");
		}
	}

}
