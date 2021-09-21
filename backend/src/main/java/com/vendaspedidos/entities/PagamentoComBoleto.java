package com.vendaspedidos.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.vendaspedidos.entities.enums.EstadoPagamento;


@Entity
@Table(name = "tb_pagamentoComBoleto")
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento{
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime dataVencimento;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private LocalDateTime dataPagamento;
	
	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, LocalDateTime dataVencimento, LocalDateTime dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
}
