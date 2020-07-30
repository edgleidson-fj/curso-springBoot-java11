//Excerção = Recurso não encontrado.

package com.edgleidson.curso.servicos.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	
	public ResourceNotFoundException(Object id) {
		super("Recurso não encontrado. ID: "+id);
	}
}
