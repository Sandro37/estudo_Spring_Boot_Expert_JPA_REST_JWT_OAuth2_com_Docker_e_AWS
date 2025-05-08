package com.alessandro.libraryappi.controller.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.alessandro.libraryappi.model.Autor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record AutorDTO(UUID id,

		@NotBlank(message = "Campo obrigatório") 
		@Size(min = 2, max = 100, message = "tamanho deve ser entre 2 e 100")
		String nome,

		@NotNull(message = "Campo obrigatório") 
		@Past(message = "Não pode ser uma data futura")
		LocalDate dataNascimento,

		@Size(min = 1, max = 50, message = "tamanho deve ser entre 1 e 100")
		@NotBlank(message = "Campo obrigatório") String nacionalidade) {

	public Autor mapearParaAutor() {
		Autor autor = new Autor();

		autor.setNome(nome);
		autor.setDataNascimento(dataNascimento);
		autor.setNacionalidade(nacionalidade);
		return autor;
	}

	public Autor atualizarAutor(Autor autor) {
		autor.setNome(nome);
		autor.setDataNascimento(dataNascimento);
		autor.setNacionalidade(nacionalidade);
		return autor;
	}

}
