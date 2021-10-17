package com.vendaspedidos.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vendaspedidos.dto.PedidoDTO;
import com.vendaspedidos.entities.Pedido;
import com.vendaspedidos.services.PedidoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@ApiOperation(value="Busca todos os pedidos paginados")
	@GetMapping
	public ResponseEntity<Page<Pedido>> findPage(Pageable pageable){
		Page<Pedido> page = service.findPage(pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@ApiOperation(value="Busca pedido por id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
		PedidoDTO dto = service.findById(id);	
		return ResponseEntity.ok().body(dto);
	}
	
	@ApiOperation(value="Adiciona novo pedido")
	@PostMapping
	public ResponseEntity<Pedido> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
