package com.alessandro.produtosapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alessandro.produtosapi.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String>{
	List<Produto> findByNome(String nome);

}
