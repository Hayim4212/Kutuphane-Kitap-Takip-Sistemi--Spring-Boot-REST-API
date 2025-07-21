package com.hasimsolak.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.hasimsolak")
@SpringBootApplication
public class KutuphaneKitapTakipSistemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KutuphaneKitapTakipSistemiApplication.class, args);
	}

}
