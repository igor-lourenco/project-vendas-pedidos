package com.vendaspedidos.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.ClienteDTO;
import com.vendaspedidos.dto.ClienteNewDTO;
import com.vendaspedidos.entities.Cidade;
import com.vendaspedidos.entities.Cliente;
import com.vendaspedidos.entities.Endereco;
import com.vendaspedidos.entities.enums.TipoCliente;
import com.vendaspedidos.repositories.ClienteRepository;
import com.vendaspedidos.repositories.EnderecoRepository;
import com.vendaspedidos.services.exception.DatabaseException;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Transactional(readOnly = true)
	public ClienteDTO findById(Long id) {
		Optional<Cliente> cat = repository.findById(id);
		Cliente entity = cat.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado -> " + id));
		return new ClienteDTO(entity, entity.getEnderecos(), entity.getTelefones());
	}

	@Transactional(readOnly = true)
	public Page<ClienteDTO> findAll(Integer pageable, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(pageable, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> page = repository.findAll(pageRequest);
		return page.map(x -> new ClienteDTO(x));

	}

	@Transactional
	public Cliente insert(Cliente entity) {
		entity = repository.save(entity);
		enderecoRepository.saveAll(entity.getEnderecos());
		return entity;

	}

	@Transactional
	public ClienteDTO update(Long id, ClienteDTO dto) {
		try {
			Cliente entity = repository.getOne(id);
			entity.setNome(dto.getNome());
			entity.setEmail(dto.getEmail());
			entity = repository.save(entity);
			return new ClienteDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Cliente não encontrado -> "+ id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id não encontrado -> " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possível excluir porque há pedidos relacionados!");
		}
	}

	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(),
				TipoCliente.toEnum(dto.getTipoCliente()));
		Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(),
				dto.getBairro(), dto.getCpfOuCnpj(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2() != null)
			cliente.getTelefones().add(dto.getTelefone2());
		return cliente;
	}
}
