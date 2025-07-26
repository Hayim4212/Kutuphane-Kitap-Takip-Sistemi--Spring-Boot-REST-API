package com.hasimsolak.repository;



import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


import com.hasimsolak.entity.Book;
import com.hasimsolak.entity.User;



@Repository
public interface BookRepository extends JpaRepository<Book , Long>{
	
	Optional<Book> findByUser(User user);

}
