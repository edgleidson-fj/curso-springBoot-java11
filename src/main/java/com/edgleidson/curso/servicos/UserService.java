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
	
	public User salvar(User obj) {
		return repositorio.save(obj);
	}
	
	public void excluir(Long id) {
		repositorio.deleteById(id);
	}
	
	public User editar(Long id, User obj) {
		User entidade = repositorio.getOne(id);
		atualizarDados(entidade, obj);
		return repositorio.save(entidade);
	}

	// Método para atualizar dados, exceto ID e Senha.
	private void atualizarDados(User entidade, User obj) {
		entidade.setNome(obj.getNome());
		entidade.setEmail(obj.getEmail());
		entidade.setTelefone(obj.getTelefone());
	}
}
