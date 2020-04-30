/**
 * 
 */
package com.paypal.assignment.dto.request;

/**
 * Demonstrating builder design pattern to create immutable CreateBookRequest
 * object
 * 
 * @author gaurav
 *
 */
public class CreateBookRequest<B extends CreateBookRequest.CreateBookRequestBuilder<B>> {

	private String title;

	private String author;

	private Long libraryId;

	public CreateBookRequest() {
		// Do Nothing
	}

	public CreateBookRequest(CreateBookRequestBuilder<B> createBookRequestBuilder) {
		this.title = createBookRequestBuilder.title;
		this.author = createBookRequestBuilder.author;
		this.libraryId = createBookRequestBuilder.libraryId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @return the libraryId
	 */
	public Long getLibraryId() {
		return libraryId;
	}

	@SuppressWarnings("unchecked")
	public static class CreateBookRequestBuilder<B extends CreateBookRequest.CreateBookRequestBuilder<B>> {

		private String title;

		private String author;

		private Long libraryId;

		public CreateBookRequestBuilder() {
			// Do Nothing
		}

		public CreateBookRequestBuilder(String title, String author, Long libraryId) {
			this.title = title;
			this.author = author;
			this.libraryId = libraryId;
		}

		public B title(String title) {
			this.title = title;
			return (B) this;
		}

		public B author(String author) {
			this.author = author;
			return (B) this;
		}

		public B libraryId(Long libraryId) {
			this.libraryId = libraryId;
			return (B) this;
		}

		public CreateBookRequest<B> build() {
			return new CreateBookRequest<>(this);
		}
	}

}
