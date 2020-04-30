/**
 * 
 */
package com.paypal.assignment.services;

import com.paypal.assignment.dto.request.CreateBookRequest;
import com.paypal.assignment.dto.request.UpdateBookRequest;
import com.paypal.assignment.dto.response.ListBookResponse;
import com.paypal.assignment.entities.Book;

/**
 * Business logics related to Books
 * 
 * @author gaurav
 *
 */
public interface BookService {

	/**
	 * To register a Book
	 * 
	 * @param createBookRequest
	 * @return
	 */
	Book create(CreateBookRequest<?> createBookRequest);

	/**
	 * To edit a Book
	 * 
	 * @param updateBookRequest
	 */
	Book update(UpdateBookRequest<?> updateBookRequest);

	/**
	 * To get a Book
	 * 
	 * @param updateBookRequest
	 */
	Book get(Long id);

	/**
	 * To get all books of a library
	 * 
	 * @param libraryId
	 * @return
	 */
	ListBookResponse getAllByLibrary(Long libraryId);
}
