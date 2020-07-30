// Manipulador de Exceção de Recursos. {StandardErro e ResourceNotFoundException}.

package com.edgleidson.curso.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edgleidson.curso.servicos.exceptions.DatabaseException;
import com.edgleidson.curso.servicos.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> recursoNaoEncontrado(ResourceNotFoundException ex,	HttpServletRequest request) {

		String erro = "Recurso não encontrado!";
		HttpStatus estado = HttpStatus.NOT_FOUND; //404.
		StandardError erroPadrao = 
				new StandardError(Instant.now(), estado.value(), erro, ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(estado).body(erroPadrao);
	}
	//-------------------------------------------------------
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> ErroBancoDeDados(DatabaseException ex,	HttpServletRequest request) {

		String erro = "Erro no Banco de Dados!";
		HttpStatus estado = HttpStatus.BAD_REQUEST; //400.
		StandardError erroPadrao = 
				new StandardError(Instant.now(), estado.value(), erro, ex.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(estado).body(erroPadrao);
	}

}
