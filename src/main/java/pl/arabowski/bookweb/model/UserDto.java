package pl.arabowski.bookweb.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @Size(min = 2, message = "Username should be at least 2 characters long")
    private String username;
    @NotNull(message = "Email address can not be null")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull(message = "Password can not be null")
    @Size(min = 2, message = "Password should be at least 2 characters long")
    private String password;
    private String passwordConfirmed;
}
