package com.vendaspedidos.entities.enums;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"),
	PESSOA_JURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return  descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) // se o código for nulo
			return null;
		
		for(TipoCliente x : TipoCliente.values()) { //percorre os valores possiveis no TipoCliente
			if(cod.equals(x.getCod())) //se o cod vindo como parametro for igual o cod do TipoCliente
			return x; //retorna o cod
		}
		
		throw new IllegalArgumentException("Id inválido -> " + cod); //lança excecao se nao tiver cod válido
	}
}
