package com.alessandro.libraryappi.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.alessandro.libraryappi.model.GeneroLivro;

public record PesquisaLivroDTO(
		UUID id,
		String isbn,
		String titulo,
		LocalDate dataPublicacao,
		GeneroLivro generoLivro,
		BigDecimal preco,
		AutorDTO autor
		) {

}
