package com.hasimsolak.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.hasimsolak")
@EnableJpaRepositories("com.hasimsolak.repository") 
@EntityScan("com.hasimsolak.entity")
public class KutuphaneKitapTakipSistemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KutuphaneKitapTakipSistemiApplication.class, args);
	}

}
