package com.edgleidson.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgleidson.curso.entidade.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
