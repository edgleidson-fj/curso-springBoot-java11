package com.edgleidson.curso.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgleidson.curso.entidade.Produto;
import com.edgleidson.curso.repositorios.ProdutoRepository;

@Service
public class ProdutoService {

	// Injeção de dependência.
	@Autowired
	private ProdutoRepository repositorio;
	
	public List<Produto> buscarTudo(){
		return repositorio.findAll();
	}
	
	public Produto buscarPorId(Long id) {
		Optional<Produto> obj = repositorio.findById(id);
		return obj.get();
	}
}
