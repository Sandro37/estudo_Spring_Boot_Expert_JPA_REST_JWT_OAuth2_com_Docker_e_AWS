package com.alessandro.arquiteturaspring.todos.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alessandro.arquiteturaspring.todos.TodoEntity;
import com.alessandro.arquiteturaspring.todos.repository.TodoRepository;

@Component
public class TodoValidator {

	@Autowired
	private TodoRepository todoRepository;

	public void validar(TodoEntity todo) {
		if (Boolean.TRUE.equals(existsTodoWithDescricao(todo))) {
			throw new IllegalArgumentException("Descrição já existe!");
		}
	}

	private Boolean existsTodoWithDescricao(TodoEntity todo) {
		return todoRepository.existsByDescricao(todo.getDescricao());
	}
}
