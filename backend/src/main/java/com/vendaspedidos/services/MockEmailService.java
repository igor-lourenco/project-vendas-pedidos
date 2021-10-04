package com.vendaspedidos.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sendgrid.helpers.mail.Mail;

public class MockEmailService extends AbstractEmailService {

	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(Mail mail) {
		LOG.info("Simulando envio de email...");
		LOG.info(mail.getSubject());
		LOG.info("Email enviado");
		
	}
}
