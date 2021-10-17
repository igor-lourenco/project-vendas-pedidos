package com.vendaspedidos.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vendaspedidos.dto.ClienteDTO;
import com.vendaspedidos.dto.ClienteNewDTO;
import com.vendaspedidos.entities.Cliente;
import com.vendaspedidos.services.ClienteService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;

	@ApiOperation(value="Busca todos clientes paginados")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> findAll(
			@RequestParam(value="page", defaultValue="0")Integer pageable,
			@RequestParam(value="linesPerPage", defaultValue="12")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction){
		
		Page<ClienteDTO> page = service.findAll(pageable, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(page);
	}
	
	@ApiOperation(value="Busca cliente por id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id) {
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Busca cliente por e-mail")
	@GetMapping(value="/email")
	public ResponseEntity<Cliente> find(@RequestParam(value="value") String email) {
		Cliente obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@ApiOperation(value="Adiciona novo cliente")
	@PostMapping
	public ResponseEntity<ClienteNewDTO> insert(@Valid @RequestBody ClienteNewDTO dto){
		Cliente entity = service.fromDTO(dto);
		entity = service.insert(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
				buildAndExpand(entity.getId()).toUri();  //mostra no cabeçalho da resposta o endereço da entidade criada
		return ResponseEntity.created(uri).body(dto); //mostra no corpo da página a entidade criada
	}
	
	@ApiOperation(value="Atualiza cliente")
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@ApiOperation(value="Deleta cliente")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
