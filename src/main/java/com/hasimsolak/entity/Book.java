package com.hasimsolak.entity;

import jakarta.persistence.Entity;

@Entity
public class Book {
	private String name;
	private String writer;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}

	//Parametresiz Yapici
	public Book() {
		
	}
	
	//Parametreli Yapici
	public Book(String name , String writer ) {
		this.name = name;
		this.writer = writer;
	}
}
