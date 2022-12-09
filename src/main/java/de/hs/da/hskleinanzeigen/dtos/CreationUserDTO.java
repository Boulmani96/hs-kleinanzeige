package de.hs.da.hskleinanzeigen.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class CreationUserDTO {
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
                    message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be null")
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "password should not be less than 6")
    private String password;

    @NotBlank(message = "First name cannot be null")
    @NotNull(message = "First name cannot be null")
    @Size(max = 255, message = "first name should not be greater than 255")
    private String firstName;

    @NotBlank(message = "Last name cannot be null")
    @NotNull(message = "Last name cannot be null")
    @Size(max = 255, message = "Last name should not be greater than 255")
    private String lastName;

    private String phone;

    private String location;
}
