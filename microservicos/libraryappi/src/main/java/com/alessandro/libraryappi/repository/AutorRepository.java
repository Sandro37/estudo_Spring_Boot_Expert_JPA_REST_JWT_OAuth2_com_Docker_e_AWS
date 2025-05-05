package com.alessandro.libraryappi.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alessandro.libraryappi.model.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
