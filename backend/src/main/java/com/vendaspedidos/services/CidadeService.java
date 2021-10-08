package com.vendaspedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendaspedidos.entities.Cidade;
import com.vendaspedidos.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public List<Cidade> findByEstado(Long estadoId) {
		return repo.findCidades(estadoId);
	}
}