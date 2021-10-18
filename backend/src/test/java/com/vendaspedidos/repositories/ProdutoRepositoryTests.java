package com.vendaspedidos.repositories;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ProdutoRepositoryTests {
/*
	long existingId;
	long nonExistingId;
	long countTotalProduto;

	@Autowired
	private ProdutoRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		countTotalProduto = 11L;
	}
	
	@Test
	public void findByIdShouldReturnIdWhenIdExistings() {
		//método 'findById' deveria retornar id quando id existir
		
		Optional<Produto> result = repository.findById(existingId); 
		
		Assertions.assertTrue(result.isPresent());
		Assertions.assertNotNull(result.get().getId());
		Assertions.assertEquals(existingId, result.get().getId());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalhenIdDoesNotExists() {
		//método 'findById' retornar optional vazio quando id não existir
		
		Optional<Produto> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		//método 'save' deveria persistir com incremento automático quando a ID for nulo
		
		Produto produto = Factory.createProduto();
		produto.setId(null);
		
		produto = repository.save(produto);
		
		Assertions.assertNotNull(produto.getId());
		Assertions.assertEquals(countTotalProduto + 1, produto.getId());
	}
	
	@Test
	public void updateShouldReturnProdutoUpdate() {
		//método 'update' deveria atualizar produto
		
		Produto produto = Factory.createProduto();
		repository.save(produto);
		
		produto.setNome("Mesa");
		repository.save(produto);
		
		Assertions.assertEquals("Mesa", produto.getNome());
	}

	@Test // Método delete Deve Deletar Objeto Quando Id Existir
	public void deleteShouldDeleteObjectWhenIdExists() {

		repository.deleteById(existingId); // deleta o id do banco

		Optional<Produto> result = repository.findById(existingId); // chama o id e tem que retornar um optional vazio
		Assertions.assertFalse(result.isPresent()); // testa se não está presente algum objeto na variavel
	}

	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {
		// método 'delete' deveria lançar uma exceção quando o id não existir

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {

			repository.deleteById(nonExistingId);
		});
	}*/
}
