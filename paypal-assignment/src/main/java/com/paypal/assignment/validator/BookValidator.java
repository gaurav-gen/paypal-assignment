package com.paypal.assignment.validator;

import org.springframework.stereotype.Component;

import com.paypal.assignment.dto.request.CreateBookRequest;
import com.paypal.assignment.exception.ValidationException;

@Component
public class BookValidator implements ValidateRequest<CreateBookRequest<?>> {

	@Override
	public String validate(CreateBookRequest<?> entity) throws ValidationException {
		// TODO Auto-generated method stub
		return null;
	}

}
