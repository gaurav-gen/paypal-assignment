/**
 * 
 */
package com.paypal.assignment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paypal.assignment.dto.request.CreateBookRequest;
import com.paypal.assignment.dto.request.UpdateBookRequest;
import com.paypal.assignment.dto.response.ListBookResponse;
import com.paypal.assignment.entities.Book;
import com.paypal.assignment.entities.Library;
import com.paypal.assignment.repositories.BookRepository;
import com.paypal.assignment.repositories.LibraryRepository;

/**
 * @author gaurav
 *
 */
@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceUnitTest {

	@Mock
	private BookRepository bookRepository;

	@Mock
	private LibraryRepository libraryRepository;

	@InjectMocks
	private BookService bookService = new BookServiceImpl();

	private static Book book;

	private static Library library;

	@BeforeAll
	public static void init() {
		library = new Library(1l, "ABC Library", "Bangalore");
		book = new Book(1l, "The Da Vinci Code", "Dan Brown", library);
	}

	@Test
	@Order(1)
	@DisplayName("Test Book registration")
	public void testBookCreate() {
		Optional<Library> optionalLibrary = Optional.of(library);
		when(libraryRepository.findById(any(Long.class))).thenReturn(optionalLibrary);
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		CreateBookRequest createBookRequest = new CreateBookRequest();
		createBookRequest.setTitle("The Da Vinci Code");
		createBookRequest.setAuthor("Dan Brown");
		createBookRequest.setLibraryId(1l);
		Book book = bookService.create(createBookRequest);
		assertEquals("The Da Vinci Code", book.getTitle());
		assertEquals("Dan Brown", book.getAuthor());
	}

	@Test
	@Order(2)
	@DisplayName("Test Get Book")
	public void testGetBook() {
		Optional<Book> optionalBook = Optional.of(book);
		when(bookRepository.findById(any(Long.class))).thenReturn(optionalBook);
		Book book = bookService.get(1l);
		assertEquals("The Da Vinci Code", book.getTitle());
		assertEquals("Dan Brown", book.getAuthor());
	}

	@Test
	@Order(3)
	@DisplayName("Test Get Book with Book Not found")
	public void testGetBookWithBookNotFound() {
		assertThrows(EntityNotFoundException.class, () -> bookService.get(1l));
	}

	@Test
	@Order(4)
	@DisplayName("Test Get Books By Library")
	public void testGetBooksByLibrary() {
		List<Book> books = new ArrayList<>();
		books.add(book);
		when(bookRepository.findAllByLibraryId(any(Long.class))).thenReturn(books);
		ListBookResponse listBookResponse = bookService.getAllByLibrary(1l);
		assertEquals("The Da Vinci Code", listBookResponse.getBooks().get(0).getTitle());
	}

	@Test
	@Order(5)
	@DisplayName("Test Book update")
	public void testBookUpdate() {
		Optional<Library> optionalLibrary = Optional.of(library);
		when(libraryRepository.findById(any(Long.class))).thenReturn(optionalLibrary);
		Optional<Book> optionalBook = Optional.of(book);
		when(bookRepository.findById(any(Long.class))).thenReturn(optionalBook);
		when(bookRepository.save(any(Book.class))).thenReturn(book);
		UpdateBookRequest updateBookRequest = new UpdateBookRequest();
		updateBookRequest.setId(1l);
		updateBookRequest.setTitle("Outliers");
		updateBookRequest.setAuthor("Malcom Gladwell");
		updateBookRequest.setLibraryId(1l);
		Book book = bookService.update(updateBookRequest);
		assertEquals("Outliers", book.getTitle());
		assertEquals("Malcom Gladwell", book.getAuthor());
	}

	@Test
	@Order(6)
	@DisplayName("Test Book update with Book Not found")
	public void testBookUpdateWithBookNotFound() {
		UpdateBookRequest updateBookRequest = new UpdateBookRequest();
		updateBookRequest.setId(1l);
		updateBookRequest.setTitle("Outliers");
		updateBookRequest.setAuthor("Malcom Gladwell");
		updateBookRequest.setLibraryId(1l);
		assertThrows(EntityNotFoundException.class, () -> bookService.update(updateBookRequest));
	}

	@Test
	@Order(7)
	@DisplayName("Test Book update with Library Not found")
	public void testBookUpdateWithLibraryNotFound() {
		Optional<Book> optionalBook = Optional.of(book);
		when(bookRepository.findById(any(Long.class))).thenReturn(optionalBook);
		UpdateBookRequest updateBookRequest = new UpdateBookRequest();
		updateBookRequest.setId(1l);
		updateBookRequest.setTitle("Outliers");
		updateBookRequest.setAuthor("Malcom Gladwell");
		updateBookRequest.setLibraryId(1l);
		assertThrows(EntityNotFoundException.class, () -> bookService.update(updateBookRequest));
	}
}
