package com.alessandro.arquiteturaspring.montadora;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Chave implements Serializable {
	private static final long serialVersionUID = -3317756511728679854L;

	private Montadora montadora;
	private String tipo;

}
