package com.hasimsolak.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hasimsolak.dto.BookDTO;
import com.hasimsolak.entity.Book;
import com.hasimsolak.entity.User;
import com.hasimsolak.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final UserDetailsService userDetailsService;
	private final BookService bookService;
	
	public BookController(BookService bookService, UserDetailsService userDetailsService) {
		
		this.userDetailsService = userDetailsService;;
		
		this.bookService = bookService;	
	
	}
	

	@GetMapping
	public ResponseEntity<Optional<Book>> getBooks() {

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
	    String username = authentication.getName();  
	
        User currentUser = (User) userDetailsService.loadUserByUsername(username);

        Optional<Book> myBooks = bookService.getBooks(currentUser);
        
        return ResponseEntity.ok(myBooks);
	}
	
	@PostMapping
	public Book saveBook(@RequestBody BookDTO bookDTO) {
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    String username = authentication.getName();  
	
        User currentUser = (User) userDetailsService.loadUserByUsername(username);
		
		
		
		return bookService.saveBook(bookDTO.getName() , bookDTO.getAuthor() , currentUser);
		
	}

}
