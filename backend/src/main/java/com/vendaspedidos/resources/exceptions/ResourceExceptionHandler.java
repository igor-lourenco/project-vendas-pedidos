package com.vendaspedidos.resources.exceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vendaspedidos.services.exception.AuthorizationException;
import com.vendaspedidos.services.exception.DatabaseException;
import com.vendaspedidos.services.exception.EmailException;
import com.vendaspedidos.services.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<StandardError> entityNotFound(EmailException e, HttpServletRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		StandardError err = new StandardError();
		err.setTimestamp(LocalDateTime.now().format(formatter));
		err.setStatus(status.value());
		err.setError("Erro de e-mail");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND; // 404
		StandardError err = new StandardError();
		err.setTimestamp(LocalDateTime.now().format(formatter));
		err.setStatus(status.value());
		err.setError("Recurso não encontrado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST; //400
		StandardError err = new StandardError();
		err.setTimestamp(LocalDateTime.now().format(formatter));
		err.setStatus(status.value());
		err.setError("Exceção no banco");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.FORBIDDEN; //403
		StandardError err = new StandardError();
		err.setTimestamp(LocalDateTime.now().format(formatter));
		err.setStatus(status.value());
		err.setError("Acesso negado");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(JDBCException.class)
	public ResponseEntity<StandardError> jdbcException(JDBCException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST; //400
		StandardError err = new StandardError();
		err.setTimestamp(LocalDateTime.now().format(formatter));
		err.setStatus(status.value());
		err.setError("Exceção no banco");
		err.setMessage("E-mail já existe!");
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // 422
		ValidationError err = new ValidationError();
		err.setTimestamp(LocalDateTime.now().format(formatter));
		err.setStatus(status.value());
		err.setError("Exceção na validação");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		for(FieldError f : e.getBindingResult().getFieldErrors()) { //percorre o erro dos campos que tem a excecao MethodArgumentNotValidException 
			err.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
	}
}
