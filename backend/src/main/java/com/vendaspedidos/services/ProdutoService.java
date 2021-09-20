package com.vendaspedidos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

	
	public Page<ProdutoDTO> search1(String nome, List<Integer> ids,Integer pageable, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(pageable, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		Page<Produto> entity = repository.search(nome, categorias, pageRequest);
		return entity.map(x -> new ProdutoDTO(x));
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
	/*
	public Page<Produto> search2(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
	}*/
}
