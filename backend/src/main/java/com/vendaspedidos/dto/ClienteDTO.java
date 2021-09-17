package com.vendaspedidos.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.vendaspedidos.entities.Cliente;
import com.vendaspedidos.entities.Endereco;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Campo requerido")
	@Size(min=3, max=50, message="O tamanho deve ser entre 3 e 50 caracteres")
	private String nome;
	
	@NotBlank(message = "Campo requerido")
	@Email(message="E-mail inválido")
	private String email;
	private String cpfOuCnpj;
	private Integer tipoCliente;
	
	private List<Endereco> enderecos = new ArrayList<>();
	private Set<String> telefones = new HashSet<>();

	ClienteDTO() {
	}

	public ClienteDTO(Long id,String nome,  String email, String cpfOuCnpj,Integer tipoCliente) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente;
	}

	public ClienteDTO(Cliente entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
		this.cpfOuCnpj = entity.getCpfOuCnpj();
		tipoCliente = entity.getTipoCliente().getCod();
	}

	public ClienteDTO(Cliente entity, List<Endereco> enderecos, List<String> telefones) {
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}	
}
