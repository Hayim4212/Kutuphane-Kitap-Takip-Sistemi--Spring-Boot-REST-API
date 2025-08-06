package com.hasimsolak.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hasimsolak.dto.BookDTO;
import com.hasimsolak.entity.Book;
import com.hasimsolak.entity.User;
import com.hasimsolak.repository.BookRepository;

@Service
public class BookService {

	
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
    	
    	this.bookRepository = bookRepository;
    
    }
    
    
    public void saveBook(String name , String author , User user) {
    	
    	Book newBook = new Book();
    	
    	newBook.setName(name);
    	
    	newBook.setAuthor(author);
    	
    	newBook.setUser(user);
    	
    	bookRepository.save(newBook);
    	
    }
    

    public List<Book> getBooks(User user) {
    	
        return bookRepository.findByUser(user);
    }
    
    public Optional<Book> updateBook(Long bookId , User user , BookDTO updatedBook){
    	
    	Optional<Book> optionalBook = bookRepository.findByBookIdAndUser(bookId, user);
    	
    	if (optionalBook.isPresent()) {
    		
    		Book book = optionalBook.get();
			
	    	book.setAuthor(updatedBook.getAuthor());
	    	
	    	book.setName(updatedBook.getName());
	    	
	    	return Optional.of(bookRepository.save(book));
    	}
    	
    	return Optional.empty();
    	
    }
    

    public boolean deleteBook(Long bookId , User user) {
    	
    	Optional<Book> optionalBook = bookRepository.findByBookIdAndUser(bookId, user);
    	
    	if (optionalBook.isPresent()) {
			
    		bookRepository.delete(optionalBook.get());
    		return true;
		}
    	
    	return false;
    }
    
    
    
}
