/**
 * 
 */
package com.paypal.assignment.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paypal.assignment.dto.request.CreateLibraryRequest;
import com.paypal.assignment.entities.Library;

/**
 * Integration Test for Library
 * 
 * @author gaurav
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryControllerIntegrationTest {

	@LocalServerPort
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testCreateStudent() throws Exception {
		CreateLibraryRequest createLibraryRequest = new CreateLibraryRequest();
		createLibraryRequest.setName("ABC Library");
		createLibraryRequest.setLocation("Bangalore");
		ResponseEntity<Library> response = restTemplate.postForEntity(createURLWithPort("/library/create"),
				createLibraryRequest, Library.class);
		assertEquals("ABC Library", response.getBody().getName());
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
