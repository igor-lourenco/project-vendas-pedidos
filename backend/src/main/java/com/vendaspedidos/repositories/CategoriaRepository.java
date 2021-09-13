package com.vendaspedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendaspedidos.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
