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
    String from;
    @Email
    @NotNull
    @NotBlank
    String to;
    @NotNull
    @NotBlank
    String subject;
    @NotNull
    @NotBlank
    String content;

}
