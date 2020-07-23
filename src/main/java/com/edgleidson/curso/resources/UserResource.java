package com.edgleidson.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgleidson.curso.entidade.User;
import com.edgleidson.curso.servicos.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	// Injeção de dependência.
	@Autowired
	private UserService service;

	
	// Tipo específico do (Spring Boot) para retornar respostas de requisições Web.
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> lista = service.buscarTudo();		
		return ResponseEntity.ok().body(lista);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.buscarPorId(id);
		return ResponseEntity.ok(obj);
	}
}
