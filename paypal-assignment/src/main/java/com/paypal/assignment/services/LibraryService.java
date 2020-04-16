/**
 * 
 */
package com.paypal.assignment.services;

import com.paypal.assignment.dto.request.CreateLibraryRequest;
import com.paypal.assignment.entities.Library;

/**
 * Business logics related to Library
 * 
 * @author gaurav
 *
 */
public interface LibraryService {

	/**
	 * To register a new library
	 * 
	 * @param createLibraryRequest
	 * @return
	 */
	Library create(CreateLibraryRequest createLibraryRequest);
}
