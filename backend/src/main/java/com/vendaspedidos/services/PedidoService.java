package com.vendaspedidos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vendaspedidos.dto.PedidoDTO;
import com.vendaspedidos.entities.ItemPedido;
import com.vendaspedidos.entities.PagamentoComBoleto;
import com.vendaspedidos.entities.Pedido;
import com.vendaspedidos.entities.enums.EstadoPagamento;
import com.vendaspedidos.repositories.ItemPedidoRepository;
import com.vendaspedidos.repositories.PagamentoRepository;
import com.vendaspedidos.repositories.PedidoRepository;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	@Transactional(readOnly = true)
	public PedidoDTO findById(Long id) {
		Optional<Pedido> cat = repository.findById(id);
		Pedido entity = cat.orElseThrow(() -> new ResourceNotFoundException("Id não encontrado!"));
		return new PedidoDTO(entity, entity.getItens());
	}
	
	@Transactional(readOnly = true)
	public List<PedidoDTO> findAll(){
		List<Pedido> entity = repository.findAll();
		return entity.stream().map(x -> new PedidoDTO(x, x.getItens())).collect(Collectors.toList());
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(LocalDateTime.now());
		obj.setCliente(clienteService.findById(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preenchimentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		System.out.println(obj);
		emailService.sendOrderConfirmationEmail(obj);
		return obj;
	}
}
