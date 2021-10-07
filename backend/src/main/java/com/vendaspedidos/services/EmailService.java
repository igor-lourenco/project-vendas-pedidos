package com.vendaspedidos.services;

import org.springframework.stereotype.Service;

import com.sendgrid.helpers.mail.Mail;
import com.vendaspedidos.entities.Cliente;
import com.vendaspedidos.entities.Pedido;

@Service
public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(Mail mail);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
