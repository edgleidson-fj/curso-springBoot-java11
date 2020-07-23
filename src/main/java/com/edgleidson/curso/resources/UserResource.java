package com.edgleidson.curso.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgleidson.curso.entidade.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	// Testar recurso (/users).
	// Tipo específico do (Spring Boot) para retornar respostas de requisições Web.
	@GetMapping
	public ResponseEntity<User> findAll(){
		User u = new User(1L, "Fulano", "fulano@teste.com", "1234-4321", "12345");
		return ResponseEntity.ok().body(u);
	}
}
