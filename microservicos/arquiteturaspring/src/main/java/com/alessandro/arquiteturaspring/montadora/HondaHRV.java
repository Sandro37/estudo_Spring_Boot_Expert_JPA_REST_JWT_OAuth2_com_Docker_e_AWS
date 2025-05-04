package com.alessandro.arquiteturaspring.montadora;

import java.awt.Color;
import java.io.Serializable;

public class HondaHRV extends Carro implements Serializable {
	private static final long serialVersionUID = -181069051214950021L;

	public HondaHRV(Motor motor) {
		super(motor);
		this.setModelo("HRV");
		this.setCor(Color.black);
		this.setMontadora(Montadora.HONDA);
	}

}
