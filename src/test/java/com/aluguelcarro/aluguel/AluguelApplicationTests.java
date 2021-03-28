package com.aluguelcarro.aluguel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;


@SpringBootApplication
@Profile("test")
class AluguelApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(AluguelApplicationTests.class, args);
	}


}