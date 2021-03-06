package com.edgleidson.curso.servicos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.edgleidson.curso.entidade.User;
import com.edgleidson.curso.repositorios.UserRepository;
import com.edgleidson.curso.servicos.exceptions.DatabaseException;
import com.edgleidson.curso.servicos.exceptions.ResourceNotFoundException;

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
		
		// orElseThrow = Traga o resultado ou execute uma execerção.
		// -> Expressão Lambda com excerção personalizada.
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User salvar(User obj) {
		return repositorio.save(obj);
	}
	
	public void excluir(Long id) {
		try {
		repositorio.deleteById(id);
		}
		catch(EmptyResultDataAccessException ex) {
			throw new ResourceNotFoundException(id);
		}
		catch(DataIntegrityViolationException ex) {
			throw new DatabaseException(ex.getMessage());
		}
	}
	
	
	public User editar(Long id, User obj) {
		try {
		User entidade = repositorio.getOne(id);
		atualizarDados(entidade, obj);
		return repositorio.save(entidade);
		}
		catch(EntityNotFoundException ex) {
			throw new ResourceNotFoundException(id);
		}
	}

	// Método para atualizar dados, exceto ID e Senha.
	private void atualizarDados(User entidade, User obj) {
		entidade.setNome(obj.getNome());
		entidade.setEmail(obj.getEmail());
		entidade.setTelefone(obj.getTelefone());
	}
}
