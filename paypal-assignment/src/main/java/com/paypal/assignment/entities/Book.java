/**
 * 
 */
package com.paypal.assignment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity representing book
 * 
 * @author gaurav
 *
 */
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 200)
	private String title;

	@Column(length = 200)
	private String author;

	@ManyToOne
	private Library library;

	public Book() {

	}

	public Book(BookBuilder bookBuilder) {
		this.id = bookBuilder.id;
		this.title = bookBuilder.title;
		this.author = bookBuilder.author;
		this.library = bookBuilder.library;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the library
	 */
	public Library getLibrary() {
		return library;
	}

	/**
	 * @param library the library to set
	 */
	public void setLibrary(Library library) {
		this.library = library;
	}

	public static class BookBuilder {

		private Long id;

		private String title;

		private String author;

		private Library library;

		public BookBuilder id(Long id) {
			this.id = id;
			return this;
		}

		public BookBuilder title(String title) {
			this.title = title;
			return this;
		}

		public BookBuilder author(String author) {
			this.author = author;
			return this;
		}

		public BookBuilder library(Library library) {
			this.library = library;
			return this;
		}

		public Book build() {
			return new Book(this);
		}

	}

}
