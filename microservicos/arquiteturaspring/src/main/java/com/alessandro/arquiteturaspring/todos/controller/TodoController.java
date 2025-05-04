package com.alessandro.arquiteturaspring.todos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alessandro.arquiteturaspring.todos.TodoEntity;
import com.alessandro.arquiteturaspring.todos.properties.AppProperties;
import com.alessandro.arquiteturaspring.todos.service.TodoService;

@RestController
@RequestMapping(value = "todos")
public class TodoController {
	
	@Autowired
	private AppProperties appProperties;

	@Autowired
	private TodoService service;

	@PostMapping
	public TodoEntity salvar(@RequestBody TodoEntity entity) {
		System.out.println(appProperties.toString());
		return service.salvar(entity);
	}

}
