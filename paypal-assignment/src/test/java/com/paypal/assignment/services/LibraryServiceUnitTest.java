/**
 * 
 */
package com.paypal.assignment.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paypal.assignment.dto.request.CreateLibraryRequest;
import com.paypal.assignment.entities.Library;
import com.paypal.assignment.repositories.LibraryRepository;

/**
 * @author gaurav
 *
 */
@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class LibraryServiceUnitTest {

	@Mock
	private LibraryRepository libraryRepository;

	@InjectMocks // auto inject libraryRepository
	private LibraryService libraryService = new LibraryServiceImpl();

	@BeforeEach
	void setMockOutput() {
		Library library = new Library(1l, "ABC Library", "Bangalore");
		when(libraryRepository.save(any(Library.class))).thenReturn(library);
	}

	@Test
	@DisplayName("Test Library registration")
	public void testLibraryCreate() {
		CreateLibraryRequest createLibraryRequest = new CreateLibraryRequest();
		createLibraryRequest.setName("ABC Library");
		createLibraryRequest.setLocation("Bangalore");
		Library library = libraryService.create(createLibraryRequest);
		assertEquals("ABC Library", library.getName());
		assertEquals("Bangalore", library.getLocation());
	}

}
