/**
 * 
 */
package com.paypal.assignment.dto.response;

import java.util.List;

import com.paypal.assignment.entities.Book;

/**
 * @author gaurav
 *
 */
public class ListBookResponse {

	private List<Book> books;

	/**
	 * @return the books
	 */
	public List<Book> getBooks() {
		return books;
	}

	/**
	 * @param books the books to set
	 */
	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
