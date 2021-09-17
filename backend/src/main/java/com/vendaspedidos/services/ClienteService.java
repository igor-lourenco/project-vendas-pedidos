package com.vendaspedidos.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.ClienteDTO;
import com.vendaspedidos.entities.Cliente;
import com.vendaspedidos.repositories.ClienteRepository;
import com.vendaspedidos.services.exception.DatabaseException;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		Optional<Cliente> cat = repository.findById(id);
		Cliente entity = cat.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new ClienteDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAll(Integer pageable, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(pageable, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> page = repository.findAll(pageRequest);
		return  page.map(x -> new ClienteDTO(x));
		
	}
	
	@Transactional(readOnly = true)
	public ClienteDTO insert(ClienteDTO dto) {
		Cliente entity = new Cliente();
		entity.setId(dto.getId());
		entity.setNome(dto.getNome());
		entity = repository.save(entity);
		return new ClienteDTO(entity);
	}

	@Transactional
	public ClienteDTO update(Long id, ClienteDTO dto) {
		try {
			Cliente entity = repository.getOne(id);
			entity.setNome(dto.getNome());
			entity.setEmail(dto.getEmail());
			entity = repository.save(entity);
			return new ClienteDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado -> " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);			
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado -> " + id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade no banco");
		}
	}

}
