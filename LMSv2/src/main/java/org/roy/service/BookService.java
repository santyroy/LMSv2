package org.roy.service;

import java.util.List;

import org.roy.model.Book;

public interface BookService {

	public Book getBook(Integer id);
	
	public Book getBook(String name);
	
	public List<Book> getAllBook();
	
	public void addBook(Book book);
	
	public void deleteBook(Integer id);
	
	public void deleteBook(String name);
	
	public void updateBook(Integer id, Book book);

	public List<Book> findByName(String name);
}
