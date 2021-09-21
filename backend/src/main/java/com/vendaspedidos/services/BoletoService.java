package com.vendaspedidos.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.vendaspedidos.entities.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preenchimentoComBoleto(PagamentoComBoleto boleto, LocalDateTime instante) {
		
		LocalDateTime vencimento = instante.plusDays(7);
		boleto.setDataVencimento(vencimento);
	}
}
