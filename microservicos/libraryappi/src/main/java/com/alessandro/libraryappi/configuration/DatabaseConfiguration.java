package com.alessandro.libraryappi.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driver;

	@Bean
	public DataSource hikariDataSource() {
		HikariConfig config = new HikariConfig();

		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.setDriverClassName(driver);
		
		config.setMaximumPoolSize(10); // máximo de conexões liberadas
		config.setMinimumIdle(1); // tamanho inicial do pool
		config.setPoolName("library-db-pool");
		config.setMaxLifetime(600000); // 600 mil ms (10 minutos)
		config.setConnectionTimeout(100000); // timeout para conseguir uma conexão
		config.setConnectionTestQuery("select 1"); // query de teste

		return new HikariDataSource(config);
	}

}
