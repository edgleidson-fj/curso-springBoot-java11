package com.edgleidson.curso.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgleidson.curso.entidade.User;
import com.edgleidson.curso.repositorios.UserRepository;

// Anotação SpringBoot para poder ser injetado automaticamente com (Autowired). 
@Service
public class UserService {

	// Injeção de dependência.
	@Autowired
	private UserRepository repositorio;
	
	public List<User> buscarTudo(){
		return repositorio.findAll();
	}
	
	public User buscarPorId(Long id) {
		Optional<User> obj = repositorio.findById(id);
		return obj.get();
	}
}
