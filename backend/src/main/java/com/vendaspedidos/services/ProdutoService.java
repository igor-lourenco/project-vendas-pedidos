package com.vendaspedidos.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.ProdutoDTO;
import com.vendaspedidos.entities.Categoria;
import com.vendaspedidos.entities.Produto;
import com.vendaspedidos.repositories.CategoriaRepository;
import com.vendaspedidos.repositories.ProdutoRepository;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	
	public Page<ProdutoDTO> search1(Integer categoryId,String name, Pageable pageable){
		List<Categoria> cats = (categoryId == 0) ? null : Arrays.asList(categoriaRepository.getOne(categoryId));
		Page<Produto> list = repository.find(cats,name, pageable); // Busca todos os objetos da lista Product
		repository.findProductsWithCategories(list.getContent());
		return list.map(x -> new ProdutoDTO(x, x.getCategorias())); // Converte os objetos da lista Product para lista ProductDto
	}
		
	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long id) {
		Optional<Produto> cat = repository.findById(id);
		Produto entity = cat.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new ProdutoDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public List<ProdutoDTO> findAll(){
		List<Produto> entity = repository.findAll();
		return entity.stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
	}
}
