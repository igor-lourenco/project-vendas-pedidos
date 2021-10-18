package com.vendaspedidos.repositories;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CategoriaRepositoryTests {
	/*
	private int existingId;
	private int nonExistingId;
	private int countTotalCategoria;

	@Autowired
	private CategoriaRepository repository;

	@BeforeEach
	void setUpBeforeClass() throws Exception {
		existingId = 2;
		nonExistingId = 1000;
		countTotalCategoria = 7;
	}
	
	@Test
	public void findByIdShouldReturnIdWhenIdExistings() {
		//método 'findById' deveria retornar id quando id existir
		
		Optional<Categoria> result = repository.findById(existingId); 
		
		Assertions.assertTrue(result.isPresent());
		Assertions.assertNotNull(result.get().getId());
		Assertions.assertEquals(existingId, result.get().getId());
	}
	
	@Test
	public void findByIdShouldReturnEmptyOptionalhenIdDoesNotExists() {
		//método 'findById' retornar optional vazio quando id não existir
		
		Optional<Categoria> result = repository.findById(nonExistingId);
		
		Assertions.assertTrue(result.isEmpty());
		Assertions.assertFalse(result.isPresent());
	}
	
	@Test
	public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
		//método 'save' deveria persistir com incremento automático quando a ID for nulo
		
		Categoria categoria = Factory.createCategoria();
		categoria.setId(null);
		
		categoria = repository.save(categoria);
		
		Assertions.assertNotNull(categoria.getId());
		Assertions.assertEquals(countTotalCategoria + 1, categoria.getId());
	}
	
	@Test
	public void updateShouldReturnCategoriaUpdate() {
		//método 'update' deveria atualizar produto
		
		Categoria categoria = Factory.createCategoria();
		repository.save(categoria);
		
		categoria.setNome("Informática");
		repository.save(categoria);
		
		Assertions.assertEquals("Informática", categoria.getNome());
	}

	@Test
	public void deleteShouldDeleteObjectWhenIdExists() {
		// método 'delete' deveria deletar o objeto quando o id existir

		repository.deleteById(existingId);
		Optional<Categoria> result = repository.findById(existingId);

		Assertions.assertFalse(result.isPresent());
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExists() {

		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {

			repository.deleteById(nonExistingId);
		});
	}*/
}
