package com.edgleidson.curso.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgleidson.curso.entidade.Categoria;
import com.edgleidson.curso.repositorios.CategoriaRepository;

@Service
public class CategoriaService {

	// Injeção de dependência.
	@Autowired
	private CategoriaRepository repositorio;
	
	public List<Categoria> buscarTudo(){
		return repositorio.findAll();
	}
	
	public Categoria buscarPorId(Long id) {
		Optional<Categoria> obj = repositorio.findById(id);
		return obj.get();
	}
}
