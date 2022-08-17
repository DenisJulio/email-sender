package swing.app.validation;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ValidatorProvider {
	private static final Validator VALIDATOR;
	
	static {
		var factory = Validation.buildDefaultValidatorFactory();
		VALIDATOR = factory.getValidator();
	}
	
	public static Validator getValidator() {
		return VALIDATOR;
	}
}
