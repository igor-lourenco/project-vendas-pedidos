package com.vendaspedidos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.ClienteDTO;
import com.vendaspedidos.entities.Cliente;
import com.vendaspedidos.repositories.ClienteRepository;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		Optional<Cliente> cat = repository.findById(id);
		Cliente entity = cat.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new ClienteDTO(entity, entity.getEnderecos(), entity.getTelefones());
	}
	
	@Transactional(readOnly = true)
	public List<ClienteDTO> findAll(){
		List<Cliente> entity = repository.findAll();
		return entity.stream().map(x -> new ClienteDTO(x, x.getEnderecos(), x.getTelefones())).collect(Collectors.toList());
	}
}
