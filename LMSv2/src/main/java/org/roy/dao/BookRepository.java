package org.roy.dao;

import java.util.List;

import org.roy.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

	public Book findByName(String name);
	
	List<Book> findByNameLike(String name);
	
}
