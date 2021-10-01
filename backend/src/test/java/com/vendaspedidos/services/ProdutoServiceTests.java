package com.vendaspedidos.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vendaspedidos.repositories.ProdutoRepository;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {
	
	private long existingId;
	private long nonExistingId;
	//private long countTotalProduto;
	private long dependentId;

	@InjectMocks
	private ProdutoService service;

	@Mock
	private ProdutoRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		existingId = 1;
		nonExistingId = 1000;
		//countTotalProduto = 7;
		dependentId = 4;
		
		Mockito.doNothing().when(repository).deleteById(existingId); //deve fazer nada quando id existir
		Mockito.doThrow(ResourceNotFoundException.class).when(repository).deleteById(nonExistingId); //deve lançar exceção quando id não existir
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
	}
	
	
	/*
	 @Test
	public void deleteShouldDataIntegrityViolationExceptionWhenIdDoesNotExists() {
		//método 'delete'  deveria lançar exceção  quando o id tiver integridade com outro banco de dados
		
		Assertions.assertThrows(DataIntegrityViolationException.class, () ->{
			service.deleteById(dependentId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId); 
		//verifica quantas vezes o método foi chamado
	}
	
	@Test
	public void deleteShouldResourceNotFoundExceptionWhenIdDoesNotExists() {
		//método 'delete'  deveria lançar exceção  quando o id não existir
		
		Assertions.assertThrows(ResourceNotFoundException.class, () ->{
			service.deleteById(nonExistingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(nonExistingId); 
		//verifica quantas vezes o método foi chamado
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		//método 'delete' não deveria fazer nada quando o id existir
		
		Assertions.assertDoesNotThrow(() ->{
			service.deleteById(existingId);
		});
		
		Mockito.verify(repository, Mockito.times(1)).deleteById(existingId); 
		//verifica quantas vezes o método foi chamado
	}
	*/
}
