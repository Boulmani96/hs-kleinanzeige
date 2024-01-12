package de.hs.da.hskleinanzeigen.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class CreationUserDTO {

    @NotBlank(message = "E-Mail cannot be empty")
    @NotNull(message = "E-Mail cannot be null")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
                    message = "E-Mail should be valid")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "password should not be less than 6")
    private String password;

    @NotBlank(message = "First name cannot be empty")
    @NotNull(message = "First name cannot be null")
    @Size(max = 255, message = "first name should not be greater than 255")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @NotNull(message = "Last name cannot be null")
    @Size(max = 255, message = "Last name should not be greater than 255")
    private String lastName;

    private String phone;

    private String location;
}
