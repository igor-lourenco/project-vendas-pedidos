package com.vendaspedidos.resources;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendaspedidos.dto.CategoriaDTO;
import com.vendaspedidos.services.CategoriaService;
import com.vendaspedidos.services.exception.DatabaseException;
import com.vendaspedidos.services.exception.ResourceNotFoundException;
import com.vendaspedidos.tests.Factory;

@WebMvcTest(CategoriaResource.class)
public class CategoriaResourceTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoriaService service;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private Integer existingId;
	private Integer nonExistingId;
	private Integer dependentId;
	private CategoriaDTO categoriaDTO;
	private PageImpl<CategoriaDTO> page;
	
	@BeforeEach
	void setUp() throws Exception {
		
		categoriaDTO = Factory.createCategoriaDTO();
		page = new PageImpl<>(List.of(categoriaDTO));
		existingId = 1;
		nonExistingId = 2;
		dependentId = 3;
		
		when(service.findAll(any())).thenReturn(page);
		
		when(service.insert(any())).thenReturn(categoriaDTO);
		
		when(service.findById(existingId)).thenReturn(categoriaDTO);
		when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
		
		when(service.update(eq(existingId), any())).thenReturn(categoriaDTO);
		when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
		
		doNothing().when(service).delete(existingId);
		doThrow(ResourceNotFoundException.class).when(service).delete(nonExistingId);
		doThrow(DatabaseException.class).when(service).delete(dependentId);
	}
	
	@Test
	public void deleteShouldReturnNotFoundWhenIdExists() throws Exception{
		//método 'update' deveria retornar 404 quando id existir
		
		ResultActions result = mockMvc.perform(delete("/categorias/{id}", nonExistingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception{
		//método 'delete' deveria retornar 204 quando id existir
		
		ResultActions result = mockMvc.perform(delete("/categorias/{id}", existingId)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNoContent());
	}
	
	@Test
	public void insertShouldReturnCategoriaDTO() throws Exception {
		//método 'insert' deveria retornar uma CategoriaDTO quando id existir
		
		String jsonBody = objectMapper.writeValueAsString(categoriaDTO);
		
		ResultActions result = mockMvc.perform(post("/categorias")
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
	}
	
	@Test
	public void updateShoudReturnCategoriaDTOWhenIdExists()throws Exception {
		//método 'update' deveria retornar uma CategoriaDTO quando id existir
		
		String jsonBody = objectMapper.writeValueAsString(categoriaDTO);
		
		ResultActions result = mockMvc.perform(put("/categorias/{id}", existingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
	}
	
	@Test
	public void updateShoudReturnNotFoundWhenIdDoesNotExists() throws Exception{
		//método 'update' deveria retornar 404 quando id não existir
		
	String jsonBody = objectMapper.writeValueAsString(categoriaDTO);
		
		ResultActions result = mockMvc.perform(put("/categorias/{id}", nonExistingId)
				.content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findByIdShoudReturnNotFoundWhenIdDoesNotExists() throws Exception{
		//método 'findById' deveria retornar 404 quando id não existir
		
		ResultActions result = mockMvc.perform(get("/categorias/{id}", nonExistingId).
				accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isNotFound());
	}
	
	@Test
	public void findByIdShouldReturnCategoriaDTOWhenIdExists() throws Exception{
		//método 'findById' deveria retornar uma CategoriaDTO quando id existir
		
		ResultActions result = mockMvc.perform(get("/categorias/{id}", existingId).
				accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());	
		result.andExpect(jsonPath("$.id").exists());
		result.andExpect(jsonPath("$.nome").exists());
	}
	
	@Test
	public void findAllShouyldReturnPage() throws Exception {
		//método 'findAll' deveria retornar um página
		
		ResultActions result = mockMvc.perform(get("/categorias")
				.accept(MediaType.APPLICATION_JSON));
		
		result.andExpect(status().isOk());
	}
	
}
