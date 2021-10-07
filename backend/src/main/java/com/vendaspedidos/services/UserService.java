package com.vendaspedidos.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vendaspedidos.security.UserSS;

public class UserService {

	public static UserSS authenticated() { // método para obter o usuario logado
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // retorna o usuario logado
		}
		catch (Exception e) {
			return null;
		}
	}
}
