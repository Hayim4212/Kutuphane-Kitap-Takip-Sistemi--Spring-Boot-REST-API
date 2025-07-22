package com.hasimsolak.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@Column(name = "name",nullable = false,unique = false,length = 255)
	private String name;
	
	@Column(name = "author",nullable = false,unique = false,length = 255)
	private String author;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public Book() {
		
	}
	
	public Book(String name , String author , User user) {
		this.name = name;
		this.author = author;
		this.user = user;
	}


}
