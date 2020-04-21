package com.paypal.assignment.validator;

import com.paypal.assignment.exception.ValidationException;

public interface ValidateRequest<T> {

	/**
	 * Will return error message if any
	 * 
	 * @param entity
	 * @return
	 * @throws ValidationException
	 */
	String validate(T entity) throws ValidationException;
}
