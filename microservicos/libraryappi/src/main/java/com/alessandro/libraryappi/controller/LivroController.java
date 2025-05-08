package com.alessandro.libraryappi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alessandro.libraryappi.controller.dto.CadastroLivroDTO;
import com.alessandro.libraryappi.controller.dto.ErroResposta;
import com.alessandro.libraryappi.exception.RegistroDuplicadoException;
import com.alessandro.libraryappi.service.LivroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "livros")
@RequiredArgsConstructor
public class LivroController {

	private final LivroService livroService;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid CadastroLivroDTO cadastroLivro) {
		try {

			return null;
		} catch (RegistroDuplicadoException e) {
			var erroDto = ErroResposta.respostaConflito(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		}
	}
}
