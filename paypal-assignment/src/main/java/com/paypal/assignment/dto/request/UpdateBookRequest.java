/**
 * 
 */
package com.paypal.assignment.dto.request;

/**
 * @author gaurav
 *
 */
public class UpdateBookRequest extends CreateBookRequest {

	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

}
