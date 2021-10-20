package com.example.chainalysis.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.example.chainalysis"} )
public class ChainalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChainalysisApplication.class, args);
	}

}
