package com.alessandro.libraryappi.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.alessandro.libraryappi.model.Autor;
import com.alessandro.libraryappi.repository.AutorRepository;
import com.alessandro.libraryappi.validator.AutorValidator;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorService {

	private final AutorRepository autorRepository;
	private final AutorValidator autorValidator;

	public Autor salvar(Autor autor) {
		autorValidator.validar(autor);
		return autorRepository.save(autor);
	}

	public Autor getById(UUID id) {
		return autorRepository.findById(id).orElse(null);
	}

	public void delete(UUID idAutor) {
		autorValidator.validarSeTemLivrosCadastrados(idAutor);
		autorRepository.deleteById(idAutor);
	}

	public List<Autor> search(String nome, String nacionalidade) {
		Autor autor = new Autor();
		autor.setNome(nome);
		autor.setNacionalidade(nacionalidade);

		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);

		Example<Autor> exampleAutor = Example.of(autor, matcher);

		return autorRepository.findAll(exampleAutor);

		/*
		 * if (nome != null && nacionalidade != null) return
		 * autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
		 * 
		 * if (nome != null) return autorRepository.findByNome(nome);
		 * 
		 * if (nacionalidade != null) return
		 * autorRepository.findByNacionalidade(nacionalidade);
		 * 
		 * return autorRepository.findAll();
		 */
	}

}
