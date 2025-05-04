package com.alessandro.arquiteturaspring.montadora;

import java.awt.Color;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Carro implements Serializable {
	private static final long serialVersionUID = -6574967073595797498L;

	private String modelo;
	private Color cor;
	private Motor motor;
	private Montadora montadora;

	public Carro(Motor motor) {
		this.motor = motor;
	}

	public CarroStatus darIgnicao(Chave chave) {
		if (chave.getMontadora() != this.getMontadora())
			return new CarroStatus("Não é possível iniciar o carro com essa chave");

		return new CarroStatus("Carro ligado. Rodando com o motor " + motor.toString());
	}

}
