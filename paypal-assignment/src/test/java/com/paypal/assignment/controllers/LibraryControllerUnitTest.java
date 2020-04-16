/**
 * 
 */
package com.paypal.assignment.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.assignment.dto.request.CreateLibraryRequest;
import com.paypal.assignment.entities.Library;
import com.paypal.assignment.services.LibraryService;

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
@ContextConfiguration(classes = { LibraryController.class, LibraryService.class })
public class LibraryControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private LibraryService libraryService;

	@Test
	public void testCreateLibrary() throws Exception {
		CreateLibraryRequest createLibraryRequest = new CreateLibraryRequest();
		createLibraryRequest.setName("ABC Library");
		createLibraryRequest.setLocation("Bangalore");
		Library library = new Library(1l, "ABC Library", "Bangalore");
		given(libraryService.create(createLibraryRequest)).willReturn(library);

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/library/create").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(createLibraryRequest)))
				.andDo(print()).andExpect(status().isOk());
	}

}
