package com.alessandro.libraryappi.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alessandro.libraryappi.controller.dto.AutorDTO;
import com.alessandro.libraryappi.controller.dto.ErroResposta;
import com.alessandro.libraryappi.exception.OperacaoNaoPermitidaException;
import com.alessandro.libraryappi.exception.RegistroDuplicadoException;
import com.alessandro.libraryappi.model.Autor;
import com.alessandro.libraryappi.service.AutorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "autores")
@RequiredArgsConstructor
public class AutorController {

	private final AutorService autorService;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody @Valid AutorDTO autor) {
		try {
			Autor autorEntity = autorService.salvar(autor.mapearParaAutor());

			URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
					.buildAndExpand(autorEntity.getId()).toUri();

			return ResponseEntity.created(location).build();
		} catch (RegistroDuplicadoException e) {
			var erroDto = ErroResposta.respostaConflito(e.getMessage());
			return ResponseEntity.status(erroDto.status()).body(erroDto);
		}

	}

	@GetMapping("{id}")
	public ResponseEntity<AutorDTO> getById(@PathVariable("id") String id) {
		UUID idAutor = UUID.fromString(id);
		Autor autor = autorService.getById(idAutor);
		if (autor != null) {
			AutorDTO autorDTO = new AutorDTO(idAutor, autor.getNome(), autor.getDataNascimento(),
					autor.getNacionalidade());
			return ResponseEntity.ok(autorDTO);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		UUID idAutor = UUID.fromString(id);

		Autor autor = autorService.getById(idAutor);

		if (autor != null) {
			try {
				autorService.delete(idAutor);
				return ResponseEntity.noContent().build();
			} catch (OperacaoNaoPermitidaException e) {
				var erroDto = ErroResposta.respostaPadrao(e.getMessage());
				return ResponseEntity.status(erroDto.status()).body(erroDto);
			}

		}

		return ResponseEntity.notFound().build();

	}

	@GetMapping
	public ResponseEntity<List<AutorDTO>> search(@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "nacionalidade", required = false) String nacionalidade) {

		List<Autor> autores = autorService.search(nome, nacionalidade);
		List<AutorDTO> autoresDTOs = autores.stream()
				.map(e -> new AutorDTO(e.getId(), e.getNome(), e.getDataNascimento(), e.getNacionalidade())).toList();

		return ResponseEntity.ok(autoresDTOs);

	}

	@PutMapping("{id}")
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody @Valid AutorDTO autorDTO) {
		UUID idAutor = UUID.fromString(id);

		Autor autor = autorService.getById(idAutor);

		if (autor != null) {
			try {
				autorService.salvar(autorDTO.atualizarAutor(autor));
				return ResponseEntity.noContent().build();
			} catch (RegistroDuplicadoException e) {
				var erroDto = ErroResposta.respostaConflito(e.getMessage());
				return ResponseEntity.status(erroDto.status()).body(erroDto);
			}

		}

		return ResponseEntity.notFound().build();
	}
}
