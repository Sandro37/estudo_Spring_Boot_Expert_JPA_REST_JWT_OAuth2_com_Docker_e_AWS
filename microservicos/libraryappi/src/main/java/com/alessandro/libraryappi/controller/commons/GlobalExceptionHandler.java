package com.alessandro.libraryappi.controller.commons;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alessandro.libraryappi.controller.dto.ErroCampo;
import com.alessandro.libraryappi.controller.dto.ErroResposta;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		List<FieldError> fieldErros = e.getFieldErrors();
		List<ErroCampo> erroCampos = fieldErros.stream().map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
				.toList();

		return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação.", erroCampos);
	}
}
