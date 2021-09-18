package com.vendaspedidos.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.vendaspedidos.dto.ClienteNewDTO;
import com.vendaspedidos.entities.enums.TipoCliente;
import com.vendaspedidos.resources.exceptions.FieldMessage;
import com.vendaspedidos.services.validation.cpfoucnpj.CpfOuCnpjValidation;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> { //classe para testar as validação da anotation customizada
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		// inclua os testes aqui, inserindo erros na lista
		
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCod()) && CpfOuCnpjValidation.isValidCpf(objDto.getCpfOuCnpj()))
			// se o objDto for igual ao código da Pessoa fisica na classe enumerada TipoCliente e se o cpf não for valido na classe isValidCpf
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && CpfOuCnpjValidation.isValidCnpj(objDto.getCpfOuCnpj()))
			// se o objDto for igual ao código da Pessoa juridica na classe enumerada TipoCliente e se o cnpj não for valido na classe isValidCnpj
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));

		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
