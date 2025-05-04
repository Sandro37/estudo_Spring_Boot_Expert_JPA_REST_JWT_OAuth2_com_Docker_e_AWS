package com.alessandro.arquiteturaspring.montadora.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alessandro.arquiteturaspring.montadora.Carro;
import com.alessandro.arquiteturaspring.montadora.CarroStatus;
import com.alessandro.arquiteturaspring.montadora.Chave;
import com.alessandro.arquiteturaspring.montadora.HondaHRV;
import com.alessandro.arquiteturaspring.montadora.Motor;

@RestController
@RequestMapping(value = "carros")
public class TesteFabricaController {

	@Autowired
	@Qualifier("motorTurbo")
	private Motor motor;

	@PostMapping
	public CarroStatus ligarCarro(@RequestBody Chave chave) {
		Carro carro = new HondaHRV(motor);
		return carro.darIgnicao(chave);
	}
}
