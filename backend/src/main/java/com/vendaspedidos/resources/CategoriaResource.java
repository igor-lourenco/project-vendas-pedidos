package com.vendaspedidos.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vendaspedidos.dto.CategoriaDTO;
import com.vendaspedidos.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<CategoriaDTO> entity = service.findAll();
		return ResponseEntity.ok().body(entity);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
		CategoriaDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(dto.getId()).toUri();  //mostra no cabeçalho da resposta o endereço da entidade criada
		return ResponseEntity.created(uri).body(dto); //mostra no corpo da página a entidade criada
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

}
