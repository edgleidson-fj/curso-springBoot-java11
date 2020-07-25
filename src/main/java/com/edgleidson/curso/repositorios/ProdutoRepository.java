package com.edgleidson.curso.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgleidson.curso.entidade.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
