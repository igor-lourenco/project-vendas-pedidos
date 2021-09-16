package com.vendaspedidos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.PedidoDTO;
import com.vendaspedidos.entities.Pedido;
import com.vendaspedidos.repositories.PedidoRepository;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Transactional(readOnly = true)
	public PedidoDTO findById(Long id) {
		Optional<Pedido> cat = repository.findById(id);
		Pedido entity = cat.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new PedidoDTO(entity, entity.getItens());
	}
	
	@Transactional(readOnly = true)
	public List<PedidoDTO> findAll(){
		List<Pedido> entity = repository.findAll();
		return entity.stream().map(x -> new PedidoDTO(x, x.getItens())).collect(Collectors.toList());
	}
}
