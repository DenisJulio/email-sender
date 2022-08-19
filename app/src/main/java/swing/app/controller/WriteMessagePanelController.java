package swing.app.controller;

import lombok.Setter;
import swing.app.model.Message;
import swing.app.validation.ValidatorProvider;

@Setter
public final class WriteMessagePanelController {
	
	private String from;
	private String to;
	private String subject;
	private String content;
	
	public boolean isValidToInputField(String text) {
		var violations = ValidatorProvider.getValidator().validateValue(Message.class, "to", text);
		return violations.isEmpty();
	}
	
	public boolean isValidSubjectInputField(String text) {
		var violations = ValidatorProvider.getValidator().validateValue(Message.class, "subject", text);
		return violations.isEmpty();
	}
}
