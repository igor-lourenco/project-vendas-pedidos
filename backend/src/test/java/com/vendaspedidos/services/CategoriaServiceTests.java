package com.vendaspedidos.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vendaspedidos.dto.CategoriaDTO;
import com.vendaspedidos.entities.Categoria;
import com.vendaspedidos.repositories.CategoriaRepository;
import com.vendaspedidos.services.exception.DatabaseException;
import com.vendaspedidos.services.exception.ResourceNotFoundException;
import com.vendaspedidos.tests.Factory;

@ExtendWith(SpringExtension.class)
public class CategoriaServiceTests {
	
	private int existingId;
	private int nonExistingId;
	//private int countTotalProduto;
	private int dependentId;
	private PageImpl<Categoria> page;
	private Categoria categoria;

	@InjectMocks
	private CategoriaService service;

	@Mock
	private CategoriaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1;
		nonExistingId = 1000;
		//countTotalProduto = 7;
		dependentId = 4;
		categoria = Factory.createCategoria();
		page = new PageImpl<>(List.of(categoria));
		
		Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);
		
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(categoria);
		
		Mockito.when(repository.getOne(existingId)).thenReturn(categoria);
		Mockito.when(repository.getOne(nonExistingId)).thenThrow(EntityNotFoundException.class);
		
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(categoria));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		
		Mockito.doNothing().when(repository).deleteById(existingId); //deve fazer nada quando id existir
		Mockito.doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId); //deve lançar exceção quando id não existir
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}
	
	@Test
	public void updateShouldReturnCategoriaDTOWhenIdExists() {
		//método 'update' deveria retorna uma CategoriaDTO quando id existir 
		
		CategoriaDTO dto = Factory.createCategoriaDTO();
		
		CategoriaDTO result = service.update(existingId, dto);
		
		Assertions.assertNotNull(result);
	}
	
	
	@Test
	public void updateShouldReturnThrowWhenIdDoesNotId() {
		// método 'update' deveria retorna uma exceção quando o id não existir
		
		CategoriaDTO dto = Factory.createCategoriaDTO();
		
		Assertions.assertThrows(ResourceNotFoundException.class, () -> {
			service.update(nonExistingId, dto);
		});
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
		
		CategoriaDTO dto = service.findById(existingId);
		
		Assertions.assertNotNull(dto);
	}
	
	@Test
	public void findAllPagedShouldReturnPage() {
		
		Pageable page = PageRequest.of(0, 10);
		
		Page<CategoriaDTO> cat = service.findAll(page);
		
		Assertions.assertNotNull(cat);
		Mockito.verify(repository).findAll(page);
	}
	
	
	 @Test
	public void deleteShouldDataIntegrityViolationExceptionWhenIdDoesNotExists() {
		//método 'delete'  deveria lançar exceção  quando o id tiver integridade com outro banco de dados
		
		Assertions.assertThrows(DatabaseException.class, () ->{
			service.delete(dependentId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(dependentId); 
		//verifica quantas vezes o método foi chamado
	}
	
	@Test
	public void deleteShouldResourceNotFoundExceptionWhenIdDoesNotExists() {
		//método 'delete'  deveria lançar exceção  quando o id não existir
		
		Assertions.assertThrows(ResourceNotFoundException.class, () ->{
			service.delete(nonExistingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId); 
		//verifica quantas vezes o método foi chamado
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		//método 'delete' não deveria fazer nada quando o id existir
		
		Assertions.assertDoesNotThrow(() ->{
			service.delete(existingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId); 
		//verifica quantas vezes o método foi chamado
	}
	
}
