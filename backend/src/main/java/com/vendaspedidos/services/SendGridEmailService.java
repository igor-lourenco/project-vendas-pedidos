package com.vendaspedidos.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.vendaspedidos.services.exception.EmailException;

public class SendGridEmailService extends AbstractEmailService {

	private static Logger log = org.slf4j.LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private SendGrid sendGrid;
	
	public void sendEmail(Mail mail) {
	
		Request request = new Request();
		
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			
			log.info("Enviando e-mail ");
			Response response = sendGrid.api(request);
			
			if(response.getStatusCode() >= 400 && response.getStatusCode() <= 500) {
				log.info("Erro ao enviar e-mail -> " + response.getBody());
				throw new EmailException(response.getBody());
			}
				log.info("E-mail enviado! Status -> " + response.getStatusCode());
			
		} catch (IOException e) {
			throw new EmailException(e.getMessage());
		}
	}
}
