package com.edgleidson.curso.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.Servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edgleidson.curso.entidade.User;
import com.edgleidson.curso.servicos.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	// Injeção de dependência.
	@Autowired
	private UserService service;

	
	// GetMapping = Tipo específico do (Spring Boot) para retornar respostas de requisições Web.
	@GetMapping
	public ResponseEntity<List<User>> buscarTudo(){
		List<User> lista = service.buscarTudo();		
		return ResponseEntity.ok().body(lista);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> buscarPorId(@PathVariable Long id){
		User obj = service.buscarPorId(id);
		return ResponseEntity.ok(obj);
	}
	
	// Insert.
	// PostMapping = Tipo específico do (Spring Boot) para enviar requisições Web.
	@PostMapping
	public ResponseEntity<User> salvar(@RequestBody User obj){
		obj = service.salvar(obj);
		
		//Pegar o ID inserido.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}
}
