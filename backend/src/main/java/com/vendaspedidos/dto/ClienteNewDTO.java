package com.vendaspedidos.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.vendaspedidos.services.validation.ClienteInsert;

@ClienteInsert //annotation customizada
public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Campo requerido")
	@Size(min=3, max=50, message="O tamanho deve ser entre 3 e 50 caracteres")
	private String nome;
	
	@NotBlank(message = "Campo requerido")
	@Email(message="E-mail inválido")
	private String email;
	
	@NotBlank(message = "Campo requerido")
	private String cpfOuCnpj;
	private Integer tipoCliente;
	
	@NotBlank(message = "Campo requerido")
	private String logradouro;
	
	@NotBlank(message = "Campo requerido")
	private String numero;
	
	private String complemento;
	private String bairro;
	
	@NotBlank(message = "Campo requerido")
	private String cep;

	@NotBlank(message = "Campo requerido")
	private String telefone1;
	private String telefone2;
	
	private Integer cidadeId;
	
	public ClienteNewDTO() {
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

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
}
