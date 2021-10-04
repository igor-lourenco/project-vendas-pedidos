package com.vendaspedidos.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.vendaspedidos.entities.Pedido;

@Service
public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
}
