package com.bookerinio.ecommercesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EcommerceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceSystemApplication.class, args);
	}
}