package com.vendaspedidos.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
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
	
	public boolean tokenValido(String token) { // método pra verificar se o token é válido
		Claims claims = getClaims(token); // armazena as reivindicações do token (usuario e o tempo de expiração)
		if (claims != null) {
			String username = claims.getSubject(); // pega o usuario do token
			Date expirationDate = claims.getExpiration(); // pega o tempo de expiração do token 
			Date now = new Date(System.currentTimeMillis()); // data atual do sistema
			
			if (username != null && expirationDate != null && now.before(expirationDate)) { 
				// se o usuario não for nulo, se a data de expiração não for nulo, e se a data atual for menor que a data do token
				return true;
			}
		}
		return false;
	}

	public String getUsername(String token) { // método que pega o usuario do token
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}
		return null;
	}

	private Claims getClaims(String token) { // função que recupera as reivindicações de um token
		//token = token.substring(7);
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
	}
}
