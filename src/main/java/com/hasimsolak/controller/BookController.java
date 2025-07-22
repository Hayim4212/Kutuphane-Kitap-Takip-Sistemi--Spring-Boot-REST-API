package com.hasimsolak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hasimsolak.entity.Book;
import com.hasimsolak.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	
	@GetMapping
	public List<Book> getBooks(){
		
		return bookService.getBooks();
		
	}

}
