package com.vendaspedidos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.vendaspedidos.entities.Categoria;
import com.vendaspedidos.entities.Produto;

public class ProdutoDTO extends RepresentationModel<ProdutoDTO> implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private Double preco;
	private List<CategoriaDTO> categorias = new ArrayList<>();

	public ProdutoDTO() {
	}

	public ProdutoDTO(Long id, String nome, Double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}
	

	public ProdutoDTO(Produto entity) {
		id = entity.getId();
		nome = entity.getNome();
		preco = entity.getPreco();
	}
	
	public ProdutoDTO(Produto entity, Set<Categoria> categorias) {
		this(entity);
		categorias.forEach(cat -> this.categorias.add(new CategoriaDTO(cat)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<CategoriaDTO> getCategorias() {
		return categorias;
	}
}
