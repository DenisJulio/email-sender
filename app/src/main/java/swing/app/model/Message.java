package swing.app.model;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class Message {

	@NotNull
	private String from;
	@NotNull
	private String to;
	@NotNull
	private String subject;
	@NotNull
	private String content;

	public Message(String from, String to, String subject, String content) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.content = content;
	}
}
