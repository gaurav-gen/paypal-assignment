package com.paypal.assignment.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.paypal.assignment.dto.request.CreateBookRequest;
import com.paypal.assignment.exception.ValidationException;

@RunWith(JUnitPlatform.class)
@ExtendWith(MockitoExtension.class)
public class BookValidatorTest {

	private ValidateRequest<CreateBookRequest> validateRequest = new BookValidator();

//	@befor

	@Test
	@DisplayName("Test the request validation")
	void testRequestValidation() {
		CreateBookRequest createBookRequest = new CreateBookRequest();
		createBookRequest.setAuthor("Dan Brown");
		String errorMsg = "";
		try {
			errorMsg = validateRequest.validate(createBookRequest);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		assertEquals(null, errorMsg);
//		assertThrows(ValidationException.class, () -> )
	}

}
