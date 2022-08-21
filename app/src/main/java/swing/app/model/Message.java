package swing.app.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class Message {

	@NotNull
	@Email
	@NotBlank
	private String from;
	@Email
	@NotNull
	@NotBlank
	private String to;
	@NotNull
	@NotBlank
	private String subject;
	@NotNull
	@NotBlank
	private String content;

}
