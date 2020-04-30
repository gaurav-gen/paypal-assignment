/**
 * 
 */
package com.paypal.assignment.dto.request;

/**
 * @author gaurav
 *
 */
public class UpdateBookRequest<B extends UpdateBookRequest.UpdateBookRequestBuilder<B>> extends CreateBookRequest<B> {

	private Long id;

	public UpdateBookRequest() {
		// Do Nothing
	}

	public UpdateBookRequest(UpdateBookRequestBuilder<B> updateBookRequestBuilder) {
		super(updateBookRequestBuilder);
		this.id = updateBookRequestBuilder.id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	public static class UpdateBookRequestBuilder<B extends UpdateBookRequest.UpdateBookRequestBuilder<B>>
			extends CreateBookRequestBuilder<B> {

		private Long id;

		public B id(Long id) {
			this.id = id;
			return (B) this;
		}

		@Override
		public UpdateBookRequest<B> build() {
			return new UpdateBookRequest<>(this);
		}

	}

}
