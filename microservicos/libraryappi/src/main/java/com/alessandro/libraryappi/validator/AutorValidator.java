package com.alessandro.libraryappi.validator;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.alessandro.libraryappi.exception.OperacaoNaoPermitidaException;
import com.alessandro.libraryappi.exception.RegistroDuplicadoException;
import com.alessandro.libraryappi.model.Autor;
import com.alessandro.libraryappi.model.Livro;
import com.alessandro.libraryappi.repository.AutorRepository;
import com.alessandro.libraryappi.repository.LivroRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AutorValidator {

	private final AutorRepository autorRepository;
	private final LivroRepository livroRepository;

	public void validar(Autor autor) {
		if (existeAutorCadastrado(autor)) {
			throw new RegistroDuplicadoException("Autor já cadastrado!");
		}
	}

	private boolean existeAutorCadastrado(Autor autor) {
		Autor autorExist = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(autor.getNome(),
				autor.getDataNascimento(), autor.getNacionalidade());

		if (autor.getId() == null) {
			return autorExist != null;
		}

		return autorExist != null && !autor.getId().equals(autorExist.getId());
	}

	public void validarSeTemLivrosCadastrados(UUID idAutor) {
		List<Livro> list = livroRepository.findByAutor(autorRepository.findById(idAutor).get());
		if (!list.isEmpty()) {
			throw new OperacaoNaoPermitidaException("Autor possui livros cadastrados");
		}

	}

}
