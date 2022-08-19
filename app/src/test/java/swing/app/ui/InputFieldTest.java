package swing.app.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.jupiter.api.Test;

class InputFieldTest {

	@Test
	void clientGetsNotifiedWhenInputChanges() {
		var providedInput = "Hello";
		final var gotNotified = new AtomicBoolean(false);
		var inputField = InputField.builder()
				.validateInputBy(input -> !input.isBlank())
				.addInputValidityListener(
					evt -> {
						var source = (InputField) evt.getSource();
						var becomesValid = (boolean) evt.getNewValue();
						if (becomesValid) {
							gotNotified.set(source.isValidInput());
						}
					})
				.build();
		assertFalse(gotNotified.get(), "It should'nt get notified before insertion.");
		inputField.getInputTextField().setText(providedInput);
		var input = inputField.getInputTextField().getText();
		assertEquals(providedInput, input, "Same String should match");
		assertTrue(inputField.isValidInput(), "Input should return 'true'.");
		assertTrue(gotNotified.get(), "It should get notified when insertion happens.");
	}
}
