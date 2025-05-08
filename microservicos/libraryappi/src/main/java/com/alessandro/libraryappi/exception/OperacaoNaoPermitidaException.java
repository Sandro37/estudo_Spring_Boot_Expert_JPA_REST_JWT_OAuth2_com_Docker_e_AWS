package com.alessandro.libraryappi.exception;

public class OperacaoNaoPermitidaException extends RuntimeException {

	private static final long serialVersionUID = 8480197619382218668L;

	public OperacaoNaoPermitidaException(String message) {
		super(message);
	}

}
