/**
 * 
 */
package com.paypal.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.assignment.dto.request.CreateBookRequest;
import com.paypal.assignment.dto.request.UpdateBookRequest;
import com.paypal.assignment.dto.response.ListBookResponse;
import com.paypal.assignment.entities.Book;
import com.paypal.assignment.services.BookService;

/**
 * CRUD controller for book
 * 
 * @author gaurav
 *
 */
@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * API to register a book
	 * 
	 * @param createBookRequest
	 */
	@PostMapping("/create")
	public Book create(@RequestBody CreateBookRequest createBookRequest) {
		return bookService.create(createBookRequest);
	}

	/**
	 * API to update a book
	 * 
	 * @param updateBookRequest
	 */
	@PutMapping("/update")
	public Book update(@RequestBody UpdateBookRequest updateBookRequest) {
		return bookService.update(updateBookRequest);
	}

	/**
	 * API to get a book by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/get/{id}")
	public Book get(@PathVariable(value = "id") Long id) {
		return bookService.get(id);
	}

	/**
	 * API to get all books of a library
	 * 
	 * @param libraryId
	 * @return
	 */
	@GetMapping("/getAllByLibrary/{libraryId}")
	public ListBookResponse getAllByLibrary(@PathVariable(value = "libraryId") Long libraryId) {
		return bookService.getAllByLibrary(libraryId);
	}

}
