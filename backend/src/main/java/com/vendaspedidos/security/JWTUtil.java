package com.vendaspedidos.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil { // classe para gerar o token do usuário

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username) // usuario
				.setExpiration(new Date(System.currentTimeMillis() + expiration)) //tempo de expiração
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()) //assinatura do token
				.compact();
	}
}