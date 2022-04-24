package com.example.letscode;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@AllArgsConstructor
@Configuration
public class LetscodeApplication /*implements CommandLineRunner*/ {

	public static void main(String[] args) {
		SpringApplication.run(LetscodeApplication.class, args);
	}

}

