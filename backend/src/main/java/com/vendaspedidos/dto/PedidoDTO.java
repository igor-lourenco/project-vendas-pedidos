package com.vendaspedidos.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.vendaspedidos.entities.Cliente;
import com.vendaspedidos.entities.Endereco;
import com.vendaspedidos.entities.ItemPedido;
import com.vendaspedidos.entities.Pagamento;
import com.vendaspedidos.entities.Pedido;

public class PedidoDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Instant instante;
	private Pagamento pagamento;
	private Cliente cliente;
	private Endereco enderecoDeEntrega;
	private Set<ItemPedido> itens = new HashSet<>();
	
	public PedidoDTO() {
	}

	public PedidoDTO(Long id, Instant instante, Pagamento pagamento, Cliente cliente, Endereco enderecoDeEntrega) {
		this.id = id;
		this.instante = instante;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	
	public PedidoDTO(Pedido entity) {
		id = entity.getId();
		instante = entity.getInstante();
		pagamento = entity.getPagamento();
		cliente = entity.getCliente();
		enderecoDeEntrega = entity.getEnderecoDeEntrega();
	}
	
	public PedidoDTO(Pedido entity, Set<ItemPedido> itens) {
		this(entity);
		itens.forEach(item -> this.itens.add(item));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getInstante() {
		return instante;
	}

	public void setInstante(Instant instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}
}
