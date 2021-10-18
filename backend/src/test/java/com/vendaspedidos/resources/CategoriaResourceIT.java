package com.vendaspedidos.resources;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoriaResourceIT {
	/*
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper object;

	private Integer existingId;
	private Integer nonExistingId;
	private Integer countTotalCategory;
	private Integer independentId;

	private CategoriaDTO dto = Factory.createCategoriaDTO();

	@BeforeEach
	void setUp() throws Exception {

		existingId = 1;
		nonExistingId = 999;
		countTotalCategory = 8;
		independentId = 9;
	}
	
	@Test
	public void findByIdShouldReturnCategoriaDTOWhenIdExists() throws Exception{
		//método 'findById' deveria retornar uma CategoriaDTO quando id existir
		
		ResultActions result = mockMvc.perform(get("/categorias/{id}", existingId).
				accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());	
		result.andExpect(jsonPath("$.nome").value("Informática"));
	}
	
	@Test
	public void insertShouldReturnCategoriaDTO() throws Exception {
		//método 'insert' deveria retornar uma CategoriaDTO quando id existir
		
		String jsonBody = object.writeValueAsString(dto);
		
		ResultActions result = mockMvc.perform(post("/categorias")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").value(independentId));
		result.andExpect(jsonPath("$.nome").value("Eletrônicos"));
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception{
		//método 'delete' deveria retornar 204 quando id existir
		
		ResultActions result = mockMvc.perform(delete("/categorias/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception{
		
		String jsonBody = object.writeValueAsString(dto);
			
		ResultActions result = mockMvc.perform(put("/categorias/{id}", nonExistingId)
							.content(jsonBody)
							.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));	
		
		result.andExpect(status().isNotFound());
	}

	@Test
	public void updateShouldReturnProductDTOWhenIdExists() throws Exception{
		
		String jsonBody = object.writeValueAsString(dto);
		
		String expectedName = dto.getNome();
	
		ResultActions result = mockMvc.perform(put("/categorias/{id}", existingId)
								.content(jsonBody)
								.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));	
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").value(existingId));
		result.andExpect(jsonPath("$.nome").value(expectedName));
	}
	
	@Test
	public void findAllShouldReturnSortedPageWhenSortbyName() throws Exception {

		ResultActions result = mockMvc
				.perform(get("/categorias?page=0&size=12&sort=nome,asc").accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.totalElements").value(countTotalCategory));
		result.andExpect(jsonPath("$.content").exists());
		result.andExpect(jsonPath("$.content[0].nome").value("Cama, mesa e banho"));
		result.andExpect(jsonPath("$.content[1].nome").value("Cosméticos"));
		result.andExpect(jsonPath("$.content[2].nome").value("Decoração"));
	}
	*/
}
