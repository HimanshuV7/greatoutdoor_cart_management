package com.capgemini.go;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CartOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartOrderApplication.class, args);
	}

}
