/**
 * 
 */
package com.paypal.assignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paypal.assignment.entities.Book;

/**
 * To perform DB operations on Book entity
 * 
 * @author gaurav
 *
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	/**
	 * Fetch all books for a given library
	 * 
	 * @param libraryId
	 * @return
	 */
	List<Book> findAllByLibraryId(Long libraryId);
}
