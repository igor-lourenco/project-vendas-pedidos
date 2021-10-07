package com.vendaspedidos.resources;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vendaspedidos.dto.CategoriaDTO;
import com.vendaspedidos.dto.ProdutoDTO;
import com.vendaspedidos.entities.Produto;
import com.vendaspedidos.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findAll(Pageable pageable,
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categoriaId", defaultValue = "0") Integer categoriaId) {

		Page<ProdutoDTO> page = service.search1(categoriaId, nome.trim(), pageable);
		for (ProdutoDTO dto : page) {
			long prodId = dto.getId();
			List<CategoriaDTO> cat = dto.getCategorias();

			for (CategoriaDTO cat2 : cat) {
				Integer catId = cat2.getId();
				cat2.add(linkTo(methodOn(CategoriaResource.class).findById(catId)).withSelfRel());
			}
			dto.add(linkTo(methodOn(ProdutoResource.class).findById(prodId)).withSelfRel());

		}

		return ResponseEntity.ok().body(page);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Produto obj = service.findById(id);
		obj.add(linkTo(methodOn(ProdutoResource.class).findAll(null, null, null)).withRel("Lista de Produtos"));
		return ResponseEntity.ok().body(obj);

	}
}
