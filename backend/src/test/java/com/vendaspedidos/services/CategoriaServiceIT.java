package com.vendaspedidos.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.CategoriaDTO;
import com.vendaspedidos.repositories.CategoriaRepository;
import com.vendaspedidos.services.exception.ResourceNotFoundException;
import com.vendaspedidos.tests.Factory;

@SpringBootTest
@Transactional
public class CategoriaServiceIT {

	@Autowired
	private CategoriaService service;
	
	@Autowired
	private CategoriaRepository repository;
	
	private Integer existingId;
	private Integer nonExistingId;
	private Integer countTotalCategory;
	private Integer independentId;
	
	private PageRequest page;
	private Page<CategoriaDTO> result;
	private CategoriaDTO dto = Factory.createCategoriaDTO();
	
	@BeforeEach
	void setUp() throws Exception{
	
		existingId = 1;
		nonExistingId = 999;
		countTotalCategory = 8;
		independentId = 8;
	}
	
	@Test
	public void findByIdShouldReturnThrowWhenIdDoesNotId() {
		// método 'findById' deveria retorna uma exceção quando o id não existir
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.findById(nonExistingId);
		});
	}
	
	@Test
	public void findByIdShouldReturnIdWhenIdExists() {
		//método 'findById' deveria retorna uma CategoriaDTO quando id existir 
		
		 dto = service.findById(existingId);
		
		Assertions.assertNotNull(dto);
		Assertions.assertEquals(dto.getNome(), "Informática");
	}
	
	@Test
	public void updateShouldSaveNewCategoria() {
		//método 'update' deveria atualizar uma categoria  quando o id existir
		
		dto = service.update(existingId, dto);
		
		Assertions.assertNotNull(dto);
		Assertions.assertEquals("Eletrônicos", dto.getNome());
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		//método 'insert' deveria lançar uma exceção quando id não existir
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, dto);
		});
	}
	
	@Test
	public void insertShouldSaveNewCategoria() {
		//método 'insert' deveria salvar uma categoria
		
		dto = service.insert(dto);
		
		Assertions.assertNotNull(dto);
		Assertions.assertEquals(dto.getId(), countTotalCategory + 1);
		Assertions.assertEquals("Eletrônicos", dto.getNome());
	}
	
	@Test
	public void findAllPagedShouldReturnOrderedPageWhenSortByName() {
		//método 'findAllPaged' deveria retornar uma página ordenada pelo nome
		
		page = PageRequest.of(0, 10, Sort.by("nome"));
		result = service.findAll(page);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals("Cama, mesa e banho", result.getContent().get(0).getNome());
		Assertions.assertEquals("Cosméticos", result.getContent().get(1).getNome());
		Assertions.assertEquals("Decoração", result.getContent().get(2).getNome());	
	}
	
	@Test
	public void findAllPagedShouldReturnEmptyPageDoesNotExists() {
		//método 'findAllPaged' deveria retornar uma página vazia quando não existir
		
		page = PageRequest.of(50, 10);
		result = service.findAll(page);
		
		Assertions.assertTrue(result.isEmpty());		
	}
	
	@Test
	public void findAllPagedShouldReturnPaged() {
		//método 'findAllPaged' deveria retornar uma página com 10 elementos
		
		page = PageRequest.of(0, 10);
		result = service.findAll(page);
		
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(0, result.getNumber());
		Assertions.assertEquals(10, result.getSize());
		Assertions.assertEquals(Long.valueOf(countTotalCategory), result.getTotalElements());		
	}	
	
	@Test
	public void deleteShouldDeleteResourceWhenIdExists() {
		//método 'delete' deveria deletar o id quando o id existir
		
		service.delete(independentId);
		
		Assertions.assertEquals(countTotalCategory - 1, repository.count());	
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {
		// método 'delete' deveria lançar exceção quando o id não existir
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.delete(nonExistingId);
		});	
	}
}
