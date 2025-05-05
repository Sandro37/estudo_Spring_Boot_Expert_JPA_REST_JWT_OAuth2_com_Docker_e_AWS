package com.alessandro.libraryappi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String isbn;

	private String titulo;

	private LocalDate dataPublicacao;

	@Enumerated(EnumType.STRING)
	private GeneroLivro genero;

	@Column(name = "preco", precision = 18, scale = 2)
	private BigDecimal preco;

	@ManyToOne
	@JoinColumn(name = "id_autor")
	private Autor autor;
}
