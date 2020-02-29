package org.roy.controller;

import javax.validation.Valid;

import org.roy.model.Book;
import org.roy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@Autowired
	private BookService bookService;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@GetMapping("/books")
	public ModelAndView getAllBooks() {
		System.out.println("Inside all books");
		ModelAndView mv = new ModelAndView();
		mv.addObject("books", bookService.getAllBook());
		mv.setViewName("all-books");
		return mv;
	}

	@GetMapping("/add-book")
	public ModelAndView showAddBookPage() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("book", new Book());
		mv.setViewName("book-form");
		return mv;
	}

	@PostMapping(value = { "/add-book" })
	public ModelAndView processAddBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
		System.out.println("PROCESS BOOK");
		ModelAndView mv = new ModelAndView();
		if(bindingResult.hasErrors()) {
			System.out.println("ERROR");
			mv.setViewName("book-form");
			return mv;
		}
		System.out.println("NEW BOOK: " + book.toString());
		bookService.addBook(book);
		mv.setViewName("redirect:/books");
 		return mv;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView processDeleteBook(@PathVariable(value = "id") Integer id) {
		System.out.println("DELETE BOOK");
		ModelAndView mv = new ModelAndView();
		bookService.deleteBook(id);
		mv.setViewName("redirect:/books");
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView showEditBook(@PathVariable("id") Integer id) {
		System.out.println("EDIT BOOK");
		ModelAndView mv = new ModelAndView();
		Book book = bookService.getBook(id);
		mv.addObject("book", book);
		mv.setViewName("edit-form");
		return mv;
	}

	@PostMapping(value = { "/update-book" })
	public ModelAndView processUpdateBook(@ModelAttribute @Valid Book book, BindingResult bindingResult) {
		System.out.println("UPDATE BOOK");
		ModelAndView mv = new ModelAndView();
		
		if(bindingResult.hasErrors()) {
			mv.setViewName("redirect:/update-book");
		}
		
		System.out.println("OLD BOOK: " + book.toString());
		bookService.updateBook(book.getId(), book);
		mv.setViewName("redirect:/books");
		return mv;
	}
	
	@GetMapping("/search-books")
	public ModelAndView listUsers(@RequestParam(defaultValue = "") String name) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("books", bookService.findByName(name));
		mv.setViewName("all-books");
		return mv;
	}

}
