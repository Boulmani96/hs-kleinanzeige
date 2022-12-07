package de.hs.da.hskleinanzeigen.DTOs;

import javax.validation.constraints.*;

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

    public CreationUserDTO(){

    }

    public CreationUserDTO(String email, String password, String firstName, String lastName, String phone, String location) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
