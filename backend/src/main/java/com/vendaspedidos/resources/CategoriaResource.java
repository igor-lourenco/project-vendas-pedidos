package com.vendaspedidos.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<Page<CategoriaDTO>> findAll(Pageable pageable) {

		Page<CategoriaDTO> page = service.findAll(pageable);

		for (CategoriaDTO dto : page) {
			int catId = dto.getId();
			dto.add(linkTo(methodOn(CategoriaResource.class).findById(catId)).withSelfRel());
		}
		return ResponseEntity.ok().body(page);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id) {
		CategoriaDTO dto = service.findById(id);
		dto.add(linkTo(methodOn(CategoriaResource.class).findAll(null)).withRel("Lista de Categorias"));
		return ResponseEntity.ok().body(dto);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CategoriaDTO> insert(@Valid @RequestBody CategoriaDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto); // mostra no corpo da página a entidade criada
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> update(@PathVariable Integer id, @RequestBody CategoriaDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<CategoriaDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
