package com.vendaspedidos.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emails")
public class EmailResource {
/*
	@Autowired
	private EmailService service;

	@PostMapping
	public ResponseEntity<Void> send(@RequestBody EmailDTO dto){
		service.SendEmail(dto);
		return ResponseEntity.noContent().build();
	}
	*/
}
