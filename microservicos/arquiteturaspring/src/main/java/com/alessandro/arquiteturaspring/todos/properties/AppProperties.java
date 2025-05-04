package com.alessandro.arquiteturaspring.todos.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "app.config")
@Data
public class AppProperties {
	private String variavel;
	private Integer valor1;

}
