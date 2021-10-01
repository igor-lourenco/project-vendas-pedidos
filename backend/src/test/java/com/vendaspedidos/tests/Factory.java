package com.vendaspedidos.tests;

import com.vendaspedidos.dto.CategoriaDTO;
import com.vendaspedidos.dto.ProdutoDTO;
import com.vendaspedidos.entities.Categoria;
import com.vendaspedidos.entities.Produto;

public class Factory {

	public static Produto createProduto() {	
		Produto produto = new Produto(1L, "Cadeira", 100.0);
		produto.getCategorias().add(new Categoria(3, "Cama, mesa e banho"));		
		return produto;
	}
	
	public static ProdutoDTO createProdutoDTO() {
		Produto produto = createProduto();
		return new ProdutoDTO(produto, produto.getCategorias());
	}
	
	public static Categoria createCategoria() {
		Categoria categoria = new Categoria(9, "Eletrônicos");
		categoria.getProdutos().add(createProduto());	
		return categoria;
	}
	
	public static CategoriaDTO createCategoriaDTO() {
		Categoria categoria = createCategoria();
		return new CategoriaDTO(categoria);
	}
}
