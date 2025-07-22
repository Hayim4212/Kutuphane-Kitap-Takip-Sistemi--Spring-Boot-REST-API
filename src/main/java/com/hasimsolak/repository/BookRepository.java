package com.hasimsolak.repository;



import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import com.hasimsolak.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book , Long>{
	


}
