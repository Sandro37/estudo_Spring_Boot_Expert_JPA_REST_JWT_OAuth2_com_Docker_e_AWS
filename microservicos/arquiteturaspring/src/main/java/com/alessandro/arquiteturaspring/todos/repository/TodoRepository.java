package com.alessandro.arquiteturaspring.todos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alessandro.arquiteturaspring.todos.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

	boolean existsByDescricao(String descricao);
	TodoEntity findByDescricao(String descricao);
}
