package com.hasimsolak.service;


import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hasimsolak.entity.Book;
import com.hasimsolak.entity.User;
import com.hasimsolak.repository.BookRepository;

@Service
public class BookService {

	
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
    	
    	this.bookRepository = bookRepository;
    
    }
    
    
    public Book saveBook(String name , String author , User user) {
    	
    	Book newBook = new Book();
    	
    	newBook.setName(name);
    	
    	newBook.setAuthor(author);
    	
    	newBook.setUser(user);
    	
    	return bookRepository.save(newBook);
    	
    }
    

    public Optional<Book> getBooks(User user) {
    	
        return bookRepository.findByUser(user);
    }
}
