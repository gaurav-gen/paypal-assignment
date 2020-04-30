/**
 * 
 */
package com.paypal.assignment.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.assignment.dto.request.CreateBookRequest;
import com.paypal.assignment.dto.request.UpdateBookRequest;
import com.paypal.assignment.dto.response.ListBookResponse;
import com.paypal.assignment.entities.Book;
import com.paypal.assignment.entities.Library;
import com.paypal.assignment.services.BookService;

/**
 * Unit test for LibraryController. Demonstrating usage of {@link MockMvc} via
 * {@link WebMvcTest}
 * <p>
 * Using {@link MockMvc}
 * 
 * @author gaurav
 *
 */
@WebMvcTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration(classes = { BookController.class, BookService.class })
public class BookControllerUnitTest {

	private static final Logger Log = LoggerFactory.getLogger(BookControllerUnitTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BookService bookService;

	private static Book book;

	private static Library library;

	@BeforeAll
	public static void init() {
		Log.debug("Init called");
		library = new Library(1l, "ABC Library", "Bangalore");
		book = new Book.BookBuilder().id(1l).title("The Da Vinci Code").author("Dan Brown").library(library).build();
	}

	@Test
	@Order(1)
	@DisplayName("Test Book registration")
	public void testCreateBook() throws Exception {
		CreateBookRequest<?> createBookRequest = new CreateBookRequest.CreateBookRequestBuilder<>("title", "author", 1l)
				.build();

		given(bookService.create(createBookRequest)).willReturn(book);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/book/create").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(createBookRequest)))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@Order(2)
	@DisplayName("Test Get Book")
	public void testGetBook() throws Exception {

		given(bookService.get(any(Long.class))).willReturn(book);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/book/get/{id}", 1l).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").value(1));
	}

	@Test
	@Order(3)
	@DisplayName("Test Get Books By Library")
	public void testGetBookByLibrary() throws Exception {
		List<Book> books = new ArrayList<>();
		books.add(book);
		ListBookResponse listBookResponse = new ListBookResponse();
		listBookResponse.setBooks(books);

		given(bookService.getAllByLibrary(any(Long.class))).willReturn(listBookResponse);

		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/book/getAllByLibrary/{libraryId}", 1l).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@Order(4)
	@DisplayName("Test Book update")
	public void testUpdateBook() throws Exception {
		UpdateBookRequest<?> updateBookRequest = new UpdateBookRequest.UpdateBookRequestBuilder<>().id(1l)
				.title("Outliers").author("Malcom Gladwell").libraryId(1l).build();

		given(bookService.update(updateBookRequest)).willReturn(book);

		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/book/update").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updateBookRequest)))
				.andDo(print()).andExpect(status().isOk());
	}
}
