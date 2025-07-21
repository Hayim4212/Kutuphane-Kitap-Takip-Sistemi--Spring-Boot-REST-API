package com.hasimsolak.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hasimsolak.entity.Book;

@Repository
public class BookRepository {
	
	@Autowired
	private List<Book> books;
	
	public List<Book> geBooks(){
		
		return books;
	}

}
