package swing.app.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		inputField.getInputTextComponent().setText(providedInput);
		var input = inputField.getInputTextComponent().getText();
		assertEquals(providedInput, input, "Same String should match");
		assertTrue(inputField.isValidInput(), "Input should return 'true'.");
		assertTrue(gotNotified.get(), "It should get notified when insertion happens.");
	}
	
	@Test
	void testDefaultTextComponentSubclass() {
		var inputField = InputField.builder()
				.build();
		var textComponent = inputField.getInputTextComponent();
		assertTrue(textComponent instanceof JTextField, "InputTextComponent should be an instance of JTextField");
		assertFalse(textComponent instanceof JTextArea);
	}
	
	@Test
	void setTextAreaAsTextComponentSubclass() {
		var inputField = InputField.builder()
				.textComponentType(InputField.TEXT_AREA)
				.build();
		var textComponent = inputField.getInputTextComponent();
		assertTrue(textComponent instanceof JTextArea, "InputTextComponent should be an instance of JTextArea");
	}
}
