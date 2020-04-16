/**
 * 
 */
package com.paypal.assignment.dto.request;

/**
 * 
 * @author gaurav
 *
 */
public class CreateBookRequest {

	private String title;

	private String author;

	private Long libraryId;

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
	 * @return the libraryId
	 */
	public Long getLibraryId() {
		return libraryId;
	}

	/**
	 * @param libraryId the libraryId to set
	 */
	public void setLibraryId(Long libraryId) {
		this.libraryId = libraryId;
	}

}
