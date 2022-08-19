package swing.app.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.Validator;
import swing.app.validation.ValidatorProvider;

class MessageTest {
	
	private Validator validator;

	@BeforeEach
	void setup() {
		validator = ValidatorProvider.getValidator();
	}
	
	@Test
	void acceptValidMessageFormat() {
		var message = new Message("sender@host", "receiver@host", "testing", "content");
		var violations = validator.validate(message);
		assertTrue(violations.isEmpty());
	}
	
	@Test
	void rejectInvaliMessageFormat() {
		var message = new Message("sender", "receiver", "", "");
		var violations = validator.validate(message);
		assertTrue(!violations.isEmpty());
		assertEquals(4, violations.size());
	}
}
