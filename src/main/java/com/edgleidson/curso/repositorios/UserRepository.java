package com.edgleidson.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edgleidson.curso.entidade.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
