/**
 * 
 */
package com.paypal.assignment.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paypal.assignment.dto.request.CreateBookRequest;
import com.paypal.assignment.dto.request.UpdateBookRequest;
import com.paypal.assignment.dto.response.ListBookResponse;
import com.paypal.assignment.entities.Book;
import com.paypal.assignment.entities.Library;
import com.paypal.assignment.repositories.BookRepository;
import com.paypal.assignment.repositories.LibraryRepository;
import com.paypal.assignment.services.BookService;

/**
 * Integration Test for Book.
 * <p>
 * Using Mock Repository
 * 
 * @author gaurav
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

	@Autowired
	private BookService bookService;

	@MockBean
	private BookRepository bookRepository;

	@MockBean
	private LibraryRepository libraryRepository;

	private static Book book;

	private static Library library;

	@BeforeAll
	public static void init() {
		library = new Library(1l, "ABC Library", "Bangalore");
		book = new Book(1l, "The Da Vinci Code", "Dan Brown", library);
	}

	@Test
	@DisplayName("Integration test for Book registration")
	public void testCreate() throws Exception {
		Optional<Library> optionalLibrary = Optional.of(library);
		when(libraryRepository.findById(any(Long.class))).thenReturn(optionalLibrary);
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		CreateBookRequest createBookRequest = new CreateBookRequest();
		createBookRequest.setTitle("The Da Vinci Code");
		createBookRequest.setAuthor("Dan Brown");
		createBookRequest.setLibraryId(1l);
		Book book = bookService.create(createBookRequest);
		assertEquals("The Da Vinci Code", book.getTitle());
	}

	@Test
	@DisplayName("Integration test for fetching a book")
	public void testGet() throws Exception {
		Optional<Book> optionalBook = Optional.of(book);
		when(bookRepository.findById(any(Long.class))).thenReturn(optionalBook);
		Book book = bookService.get(1l);
		assertEquals("The Da Vinci Code", book.getTitle());
	}

	@Test
	@DisplayName("Integration test for fetching all books of library")
	public void testGetAllByLibrary() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(book);
		when(bookRepository.findAllByLibraryId(any(Long.class))).thenReturn(books);
		ListBookResponse listBookResponse = bookService.getAllByLibrary(1l);
		assertEquals("The Da Vinci Code", listBookResponse.getBooks().get(0).getTitle());
	}

	@Test
	@DisplayName("Integration test to update a book")
	public void testUpdate() throws Exception {
		Optional<Book> optionalBook = Optional.of(book);
		when(bookRepository.findById(any(Long.class))).thenReturn(optionalBook);
		Optional<Library> optionalLibrary = Optional.of(library);
		when(libraryRepository.findById(any(Long.class))).thenReturn(optionalLibrary);
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		UpdateBookRequest updateBookRequest = new UpdateBookRequest();
		updateBookRequest.setId(1l);
		updateBookRequest.setTitle("Outliers");
		updateBookRequest.setAuthor("Malcom Gladwell");
		updateBookRequest.setLibraryId(1l);
		Book book = bookService.update(updateBookRequest);
		assertEquals("Outliers", book.getTitle());
	}
}
