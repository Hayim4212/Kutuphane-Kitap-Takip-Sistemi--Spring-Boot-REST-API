package com.hasimsolak.config;

import java.util.ArrayList;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hasimsolak.entity.Book;

@Configuration
public class AppConfig {
	

	@Bean
	public List<Book> books(){
		List<Book> books = new ArrayList<>();
		books.add(new Book("Caglar Boyu Savas", "Hasim Solak"));
		books.add(new Book("Caglarin Hukmu", "Hasim Solak"));
		books.add(new Book("Cagin Yok Olusu", "Serif Solak"));
		books.add(new Book("Karanlik Diyarlar", "Diyar Sonmez"));
		
		return books;
	}
}
