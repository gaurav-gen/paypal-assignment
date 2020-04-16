/**
 * 
 */
package com.paypal.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.assignment.dto.request.CreateLibraryRequest;
import com.paypal.assignment.entities.Library;
import com.paypal.assignment.services.LibraryService;

/**
 * @author gaurav
 *
 */
@RestController
@RequestMapping("/library")
public class LibraryController {

	@Autowired
	private LibraryService libraryService;

	/**
	 * API to register a library
	 * 
	 * @param createLibraryRequest
	 */
	@PostMapping("/create")
	public Library create(@RequestBody CreateLibraryRequest createLibraryRequest) {
		return libraryService.create(createLibraryRequest);
	}

}
