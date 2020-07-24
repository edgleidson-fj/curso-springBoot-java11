package com.edgleidson.curso.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgleidson.curso.entidade.Pedido;
import com.edgleidson.curso.repositorios.PedidoRepository;
 
@Service
public class PedidoService {

	// Injeção de dependência.
	@Autowired
	private PedidoRepository repositorio;
	
	public List<Pedido> buscarTudo(){
		return repositorio.findAll();
	}
	
	public Pedido buscarPorId(Long id) {
		Optional<Pedido> obj = repositorio.findById(id);
		return obj.get();
	}
}
