package com.vendaspedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Transactional(readOnly = true)
	public Cliente findByEmail(String email);

}
