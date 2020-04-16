/**
 * 
 */
package com.paypal.assignment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.assignment.dto.request.CreateLibraryRequest;
import com.paypal.assignment.entities.Library;
import com.paypal.assignment.repositories.LibraryRepository;

/**
 * @author gaurav
 *
 */
@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private LibraryRepository libraryRepository;

	@Override
	public Library create(CreateLibraryRequest createLibraryRequest) {
		Library library = new Library(createLibraryRequest.getName(), createLibraryRequest.getLocation());
		return libraryRepository.save(library);
	}

}
