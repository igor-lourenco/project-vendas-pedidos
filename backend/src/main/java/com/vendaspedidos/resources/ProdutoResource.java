package com.vendaspedidos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vendaspedidos.dto.ProdutoDTO;
import com.vendaspedidos.resources.utils.URL;
import com.vendaspedidos.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
		
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> search(
			@RequestParam(value="nome", defaultValue="")String nome,
			@RequestParam(value="categorias", defaultValue="")String categorias,
			@RequestParam(value="page", defaultValue="0")Integer pageable,
			@RequestParam(value="linesPerPage", defaultValue="12")Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction){
		
		String nomeDecode = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntegerList(categorias);
		Page<ProdutoDTO> page = service.search1(nomeDecode, ids, pageable, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {

		ProdutoDTO dto = service.findById(id);

		return ResponseEntity.ok().body(dto);
	}
	
/*
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		List<ProdutoDTO> entity = service.findAll();
		return ResponseEntity.ok().body(entity);
	}
*/
	
	/*
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntegerList(categorias);
		Page<Produto> list = service.search2(nomeDecoded.trim(), ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}*/

}
