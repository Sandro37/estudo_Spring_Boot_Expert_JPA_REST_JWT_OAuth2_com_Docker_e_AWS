package com.alessandro.libraryappi.controller.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {

	public static ErroResposta respostaPadrao(String message) {
		return new ErroResposta(HttpStatus.BAD_REQUEST.value(), message, List.of());
	}

	public static ErroResposta respostaConflito(String message) {
		return new ErroResposta(HttpStatus.CONFLICT.value(), message, List.of());
	}

}
