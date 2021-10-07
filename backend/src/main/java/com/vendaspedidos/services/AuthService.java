package com.vendaspedidos.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vendaspedidos.entities.Cliente;
import com.vendaspedidos.repositories.ClienteRepository;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {

		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ResourceNotFoundException("Email não encontrado");
		}

		String newPass = newPassword(); // cria uma nova senha
		cliente.setSenha(pe.encode(newPass)); // insere a senha criada 

		clienteRepository.save(cliente); //salva
		emailService.sendNewPasswordEmail(cliente, newPass); // envia o e-mail pro cliente
	}

	private String newPassword() { // método pra criar uma nova senha aleatória
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		}
		else { // gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}
}