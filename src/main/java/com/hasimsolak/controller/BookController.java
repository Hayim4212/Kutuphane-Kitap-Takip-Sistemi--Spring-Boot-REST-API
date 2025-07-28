package com.hasimsolak.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<List<Book>> getBooks() {

	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
	    String username = authentication.getName();  
	
        User currentUser = (User) userDetailsService.loadUserByUsername(username);

        List<Book> myBooks = bookService.getBooks(currentUser);
        
        return ResponseEntity.ok(myBooks);
        
	}
	
	@PostMapping
	public Book saveBook(@RequestBody BookDTO bookDTO) {
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    String username = authentication.getName();  
	
        User currentUser = (User) userDetailsService.loadUserByUsername(username);
		
		
		
		return bookService.saveBook(bookDTO.getName() , bookDTO.getAuthor() , currentUser);
		
	}
	
	@PutMapping(path = "/{bookId}")
	public ResponseEntity<Optional<Book>> updateBook(@PathVariable Long bookId , @RequestBody BookDTO bookDTO){
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    String username = authentication.getName();  
	
        User currentUser = (User) userDetailsService.loadUserByUsername(username);
        
		
        Optional<Book> updatedBook = bookService.updateBook(bookId, currentUser, bookDTO);
        
        return ResponseEntity.ok(updatedBook);
		
	}
	
	
	@DeleteMapping(path = "/{bookId}")
	public boolean deleteBook(@PathVariable Long bookId)
	{
		
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    
	    String username = authentication.getName();  
	
        User currentUser = (User) userDetailsService.loadUserByUsername(username);
        
        return bookService.deleteBook(bookId, currentUser);
		
	}

}
