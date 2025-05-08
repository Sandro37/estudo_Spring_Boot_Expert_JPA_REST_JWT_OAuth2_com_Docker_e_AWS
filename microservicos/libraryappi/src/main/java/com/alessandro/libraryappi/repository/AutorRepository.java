package com.alessandro.libraryappi.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alessandro.libraryappi.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {
	List<Autor> findByNome(String nome);

	List<Autor> findByNacionalidade(String nacionalidade);

	List<Autor> findByNomeAndNacionalidade(String nome, String nacionalidade);

	Autor findByNomeAndDataNascimentoAndNacionalidade(String nome, LocalDate dataNascimento, String nacionalidade);

}
