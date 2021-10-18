package com.vendaspedidos.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vendaspedidos.entities.Categoria;
import com.vendaspedidos.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cats WHERE "
			+ "(COALESCE(:categorias) IS NULL OR cats IN :categorias) AND "
			+ "(LOWER(obj.nome) LIKE LOWER(CONCAT('%', :nome, '%')))")
	Page<Produto> find(List<Categoria> categorias,String nome, Pageable pageable);
	
	@Query("SELECT obj FROM Produto obj JOIN FETCH obj.categorias WHERE obj IN :produtos")
	List<Produto> findProductsWithCategories(List<Produto> produtos);
}
