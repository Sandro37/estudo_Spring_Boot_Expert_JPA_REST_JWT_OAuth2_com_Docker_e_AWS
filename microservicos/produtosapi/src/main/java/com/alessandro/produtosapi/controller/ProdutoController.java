package com.alessandro.produtosapi.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alessandro.produtosapi.model.Produto;
import com.alessandro.produtosapi.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping("/{id}")
	public Produto getById(@PathVariable("id") String id) throws Exception {
		return produtoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não existe!"));
	}

	@PutMapping
	public Produto update(@RequestBody Produto produto) {
		Produto prod = produtoRepository.findById(produto.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não existe!"));
		
		prod.setDescricao(produto.getDescricao());
		prod.setNome(produto.getNome());
		prod.setPreco(produto.getPreco());
		
		return produtoRepository.save(prod);
	}

	@PostMapping
	public Produto save(@RequestBody Produto produto) {
		produto.setId(UUID.randomUUID().toString());
		return produtoRepository.save(produto);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) throws Exception {
		produtoRepository.deleteById(id);
	}
	
	@GetMapping()
	public List<Produto> getByParams(@RequestParam("nome") String nome){
		return produtoRepository.findByNome(nome);
		
	}
}
