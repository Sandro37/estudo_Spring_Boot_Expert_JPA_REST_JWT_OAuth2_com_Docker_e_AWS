package com.alessandro.libraryappi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LibraryappiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryappiApplication.class, args);
	}
}
