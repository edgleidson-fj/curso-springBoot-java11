package com.edgleidson.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgleidson.curso.entidade.Produto;
import com.edgleidson.curso.servicos.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	// Injeção de dependência.
	@Autowired
	private ProdutoService service;

	
	// Tipo específico do (Spring Boot) para retornar respostas de requisições Web.
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		List<Produto> lista = service.buscarTudo();		
		return ResponseEntity.ok().body(lista);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id){
		Produto obj = service.buscarPorId(id);
		return ResponseEntity.ok(obj);
	}
}
