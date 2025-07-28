package com.hasimsolak.repository;



import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


import com.hasimsolak.entity.Book;
import com.hasimsolak.entity.User;



@Repository
public interface BookRepository extends JpaRepository<Book , Long>{
	
	List<Book> findByUser(User user);

    Optional<Book> findByBookIdAndUser(Long bookId, User user);
	
	
}
