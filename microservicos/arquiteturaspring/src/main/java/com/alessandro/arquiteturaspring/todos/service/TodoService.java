package com.alessandro.arquiteturaspring.todos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alessandro.arquiteturaspring.todos.MailSender;
import com.alessandro.arquiteturaspring.todos.TodoEntity;
import com.alessandro.arquiteturaspring.todos.repository.TodoRepository;
import com.alessandro.arquiteturaspring.todos.validations.TodoValidator;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TodoValidator todoValidator;

	@Autowired
	private MailSender mailSender;

	public TodoEntity salvar(TodoEntity novoTodo) {
		todoValidator.validar(novoTodo);
		mailSender.enviar(novoTodo.getDescricao());
		return todoRepository.save(novoTodo);
	}
}
