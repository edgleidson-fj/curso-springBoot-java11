package com.edgleidson.curso.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edgleidson.curso.entidade.Pedido;
import com.edgleidson.curso.servicos.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	// Injeção de dependência.
	@Autowired
	private PedidoService service;

	@GetMapping
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> lista = service.buscarTudo();		
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Long id){
		Pedido obj = service.buscarPorId(id);
		return ResponseEntity.ok(obj);
	}
}
