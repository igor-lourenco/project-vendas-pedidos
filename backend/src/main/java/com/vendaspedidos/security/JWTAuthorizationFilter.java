package com.vendaspedidos.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter  extends BasicAuthenticationFilter { // método que filtra a autorização

	private JWTUtil jwtUtil;

	private UserDetailsService userDetailsService;

	//construtor da classe
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	@Override //método que intercepta a requisição e ve se o usuario esta autorizado
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

		String header = request.getHeader("Authorization"); // pega o valor que ta no campo 'authorization' do cabeçalho da requisição
		if (header != null && header.startsWith("Bearer ")) { // se for != nulo e se valor do header comeca com 'Bearer'
			UsernamePasswordAuthenticationToken auth = getAuthentication(header.substring(7)); // pega token tirando o 'bearer ' e verifica se o token é valido
			if (auth != null) { // se for diferente de nulo
				SecurityContextHolder.getContext().setAuthentication(auth); // função pra liberar o acesso do filtro
			}
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) { // método que pega o token e retorna um UsernamePasswordAuthenticationToken
		
		if (jwtUtil.tokenValido(token)) { // se o token for válido
			String username = jwtUtil.getUsername(token); // pega o usuario que esta no token
			UserDetails user = userDetailsService.loadUserByUsername(username); // busca no Banco o usuario 
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); // retorna 
		}
		return null;
	}
}
