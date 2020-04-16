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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
 * Unit test for LibraryController
 * <p>
 * Using {@link MockMvc}
 * 
 * @author gaurav
 *
 */
@WebMvcTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BookController.class, BookService.class })
public class BookControllerUnitTest {

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
		library = new Library(1l, "ABC Library", "Bangalore");
		book = new Book(1l, "The Da Vinci Code", "Dan Brown", library);
	}

	@Test
	@DisplayName("Test Book registration")
	public void testCreateBook() throws Exception {
		CreateBookRequest createBookRequest = new CreateBookRequest();

		given(bookService.create(createBookRequest)).willReturn(book);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/book/create").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(createBookRequest)))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	@DisplayName("Test Get Book")
	public void testGetBook() throws Exception {

		given(bookService.get(any(Long.class))).willReturn(book);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/book/get/{id}", 1l).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("id").value(1));
	}

	@Test
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
	@DisplayName("Test Book update")
	public void testUpdateBook() throws Exception {
		UpdateBookRequest updateBookRequest = new UpdateBookRequest();

		given(bookService.update(updateBookRequest)).willReturn(book);

		this.mockMvc
				.perform(MockMvcRequestBuilders.put("/book/update").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updateBookRequest)))
				.andDo(print()).andExpect(status().isOk());
	}
}
