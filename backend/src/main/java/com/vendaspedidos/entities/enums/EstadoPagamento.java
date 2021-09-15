package com.vendaspedidos.entities.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Canncelado");
	
	private int cod;
	private String descricao;
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return  descricao;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) // se o código for nulo
			return null;
		
		for(EstadoPagamento x : EstadoPagamento.values()) { //percorre os valores possiveis no TipoCliente
			if(cod.equals(x.getCod())) //se o cod vindo como parametro for igual o cod do TipoCliente
			return x; //retorna o cod
		}
		
		throw new IllegalArgumentException("Id inválido -> " + cod); //lança excecao se nao tiver cod válido
	}
}
