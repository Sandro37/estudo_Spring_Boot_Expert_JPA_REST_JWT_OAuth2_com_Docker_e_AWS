package com.alessandro.arquiteturaspring.montadora;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motor implements Serializable {
	private static final long serialVersionUID = 3226315565900925266L;

	private String modelo;
	private Integer cavalos;
	private Integer cilindros;
	private Double litragem;
	private TipoMotor tipoMotor;

}
