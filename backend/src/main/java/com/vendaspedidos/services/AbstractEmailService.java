package com.vendaspedidos.services;

import org.springframework.beans.factory.annotation.Value;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.vendaspedidos.dto.EmailDTO;
import com.vendaspedidos.entities.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		Mail mail = prepareSimpleMailMessageFromPedido(obj);

		sendEmail(mail);
	}

	protected Mail prepareSimpleMailMessageFromPedido(Pedido obj) {
		EmailDTO dto = new EmailDTO();
		dto.setFromEmail(sender);
		dto.setFromName("Igor | Lourenço");
		dto.setReplyTo(sender);
		Email from = new Email(dto.getFromEmail(), dto.getFromName());

		dto.setTo(obj.getCliente().getEmail());
		Email to = new Email(dto.getTo());

		dto.setSubject("Pedido confirmado! Código: " + obj.getId());
		dto.setBody(obj.toString());
		dto.setContentType("text/plain");
		
		Content content = new Content(dto.getContentType(), dto.getSubject() + dto.getBody());
		return new Mail(from, dto.getSubject(), to, content);
	}
}
