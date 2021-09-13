package com.vendaspedidos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.CategoriaDTO;
import com.vendaspedidos.entities.Categoria;
import com.vendaspedidos.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	//@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> cat = repository.findById(id);
		Categoria entity = cat.orElseThrow(() -> new RuntimeException());
		return new CategoriaDTO(entity);
	}
	
	public List<CategoriaDTO> findAll(){
		List<Categoria> entity = repository.findAll();
		return entity.stream().map(x -> new CategoriaDTO(x)).collect(Collectors.toList());
	}
}
