package org.roy.service;

import java.util.List;

import org.roy.dao.BookRepository;
import org.roy.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book getBook(Integer id) {
		Book book = bookRepository.getOne(id);

		if (book == null) {
			return null;
		}
		return book;
	}

	@Override
	public Book getBook(String name) {
		Book book = bookRepository.findByName(name);

		if (book == null) {
			return null;
		}
		return book;
	}

	@Override
	public List<Book> getAllBook() {
		return bookRepository.findAll();
	}

	@Override
	public void addBook(Book book) {
		if (book != null) {
			bookRepository.save(book);
		}
	}

	@Override
	public void deleteBook(Integer id) {
		Book book = bookRepository.getOne(id);
		if (book != null) {
			bookRepository.deleteById(id);
		}
	}

	@Override
	public void deleteBook(String name) {
		Book book = bookRepository.findByName(name);
		if (book != null) {
			bookRepository.deleteById(book.getId());
		}

	}

	@Override
	public void updateBook(Integer id, Book book) {
		Book b = bookRepository.findById(id).get();
		if (b != null) {
			b.setName(book.getName());
			b.setAuthor(book.getAuthor());
			b.setPurchase(book.getPurchase());
			bookRepository.save(b);
		}
	}
	
	@Override
	public List<Book> findByName(String name) {
		return bookRepository.findByNameLike("%"+name+"%");
	}

}
