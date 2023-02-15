package com.teste.attornatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.teste.repository.PessoaRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = PessoaRepository.class)
@EntityScan(basePackages =  "com.teste.entity")
@ComponentScan(basePackages = "com.teste.controller," + "com.teste.service")
public class AttornatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttornatusApplication.class, args);

	}

}
