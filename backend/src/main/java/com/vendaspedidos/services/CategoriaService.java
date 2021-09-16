package com.vendaspedidos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.CategoriaDTO;
import com.vendaspedidos.entities.Categoria;
import com.vendaspedidos.repositories.CategoriaRepository;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Transactional(readOnly = true)
	public CategoriaDTO findById(Long id) {
		Optional<Categoria> cat = repository.findById(id);
		Categoria entity = cat.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new CategoriaDTO(entity, entity.getProdutos());
	}
	
	@Transactional(readOnly = true)
	public List<CategoriaDTO> findAll(){
		List<Categoria> entity = repository.findAll();
		return entity.stream().map(x -> new CategoriaDTO(x, x.getProdutos())).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = new Categoria();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity = repository.save(entity);
		return new CategoriaDTO(entity);
	}
}
