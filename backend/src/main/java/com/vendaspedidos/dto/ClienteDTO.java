package com.vendaspedidos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vendaspedidos.entities.Cliente;
import com.vendaspedidos.entities.Endereco;

public class ClienteDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private String tipoCliente;
	
	private List<Endereco> enderecos = new ArrayList<>();
	
	private Set<String> telefones = new HashSet<>();
	
	public ClienteDTO () {
	}

	public ClienteDTO(Long id, String nome, String email, String cpfOuCnpj, String tipoCliente) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente;
	}
	
	public ClienteDTO(Cliente entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		cpfOuCnpj = entity.getCpfOuCnpj();
		tipoCliente = entity.getTipoCliente().getDescricao();
	}
	
	public ClienteDTO(Cliente entity, List<Endereco> enderecos, Set<String> telefones) {
		this(entity);
		enderecos.forEach(end -> this.enderecos.add(end));
		telefones.forEach(tel -> this.telefones.add(tel));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}
}
