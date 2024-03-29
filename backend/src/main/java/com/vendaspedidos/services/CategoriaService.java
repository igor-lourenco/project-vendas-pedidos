package com.vendaspedidos.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.CategoriaDTO;
import com.vendaspedidos.entities.Categoria;
import com.vendaspedidos.repositories.CategoriaRepository;
import com.vendaspedidos.services.exception.DatabaseException;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	@Transactional(readOnly = true)
	public CategoriaDTO findById(Integer id) {
		Optional<Categoria> cat = repository.findById(id);
		Categoria entity = cat.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new CategoriaDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<CategoriaDTO> findAll(Pageable pageable){
		Page<Categoria> page = repository.findAll(pageable);
		return  page.map(x -> new CategoriaDTO(x));
	}
	
	@Transactional
	public CategoriaDTO insert(CategoriaDTO dto) {
		Categoria entity = new Categoria();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity = repository.save(entity);
		return new CategoriaDTO(entity);
	}

	@Transactional
	public CategoriaDTO update(Integer id, CategoriaDTO dto) {
		try {
			Categoria entity = repository.getOne(id);
			entity.setNome(dto.getNome());
			entity = repository.save(entity);
			return new CategoriaDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado -> " + id);
		}
	}

	public void delete(Integer id) {
		try {
			repository.deleteById(id);			
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado -> " + id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade no banco");
		}
	}
}
