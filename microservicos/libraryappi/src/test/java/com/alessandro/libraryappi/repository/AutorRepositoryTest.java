package com.alessandro.libraryappi.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alessandro.libraryappi.model.Autor;
import com.alessandro.libraryappi.model.GeneroLivro;
import com.alessandro.libraryappi.model.Livro;

@SpringBootTest
class AutorRepositoryTest {

	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private LivroRepository livroRepository;

	@Test
	void salvarTest() {
		Autor autor = new Autor();
		autor.setNome("Maria");
		autor.setNacionalidade("Brasileira");
		autor.setDataNascimento(LocalDate.of(1951, 1, 31));

		var autorSalvo = autorRepository.save(autor);
		System.out.println("Autor Salvo: " + autorSalvo);
	}

	@Test
	void atualizarTest() {
		var id = UUID.fromString("2449f4e4-ee1a-4a71-8aa3-e9d46306fe8a");

		Optional<Autor> possivelAutor = autorRepository.findById(id);

		if (possivelAutor.isPresent()) {

			Autor autorEncontrado = possivelAutor.get();
			System.out.println("Dados do Autor:");
			System.out.println(autorEncontrado);

			autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

			autorRepository.save(autorEncontrado);

		}
	}

	@Test
	void listarTest() {
		List<Autor> lista = autorRepository.findAll();
		lista.forEach(System.out::println);
	}

	@Test
	void countTest() {
		System.out.println("Contagem de autores: " + autorRepository.count());
	}

	@Test
	void deletePorIdTest() {
		var id = UUID.fromString("2449f4e4-ee1a-4a71-8aa3-e9d46306fe8a");
		autorRepository.deleteById(id);
	}

	@Test
	void deleteTest() {
		var id = UUID.fromString("abc082bf-1d23-4767-b3d9-9f322856ca6a");
		var maria = autorRepository.findById(id).get();
		autorRepository.delete(maria);
	}

	@Test
	void salvarAutorComLivrosTest() {
		Autor autor = new Autor();
		autor.setNome("Antonio");
		autor.setNacionalidade("Americana");
		autor.setDataNascimento(LocalDate.of(1970, 8, 5));

		Livro livro = new Livro();
		livro.setIsbn("20847-84874");
		livro.setPreco(BigDecimal.valueOf(204));
		livro.setGenero(GeneroLivro.MISTERIO);
		livro.setTitulo("O roubo da casa assombrada");
		livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
		livro.setAutor(autor);

		Livro livro2 = new Livro();
		livro2.setIsbn("99999-84874");
		livro2.setPreco(BigDecimal.valueOf(650));
		livro2.setGenero(GeneroLivro.MISTERIO);
		livro2.setTitulo("O roubo da casa assombrada");
		livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
		livro2.setAutor(autor);

		autor.setLivros(new ArrayList<>());
		autor.getLivros().add(livro);
		autor.getLivros().add(livro2);

		autorRepository.save(autor);

//        livroRepository.saveAll(autor.getLivros());
	}

	@Test
	void listarLivrosAutor() {
		var id = UUID.fromString("27c51581-8dfb-4b78-8c52-3965f6496f01");
		var autor = autorRepository.findById(id).get();

		// buscar os livros do autor

		List<Livro> livrosLista = livroRepository.findByAutor(autor);
		autor.setLivros(livrosLista);

		autor.getLivros().forEach(System.out::println);
	}
}
