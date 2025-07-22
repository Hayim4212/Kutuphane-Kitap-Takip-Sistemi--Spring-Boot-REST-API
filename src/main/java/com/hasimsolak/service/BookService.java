package com.hasimsolak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hasimsolak.entity.Book;
import com.hasimsolak.repository.BookRepository;

@Service
public class BookService {

	@Autowired
    private BookRepository bookRepository;
    

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
