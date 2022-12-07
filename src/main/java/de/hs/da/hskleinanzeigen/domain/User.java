package de.hs.da.hskleinanzeigen.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Data //That bundles the features of @ToString, @EqualsAndHashCode, @Getter/@Setter and @RequiredArgsConstructor
@NoArgsConstructor //Will lead to an empty constructor generation.
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be null")
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "password should not be less than 6")
    private String password;

    @NotBlank(message = "First name cannot be null")
    @NotNull(message = "First name cannot be null")
    @Size(max = 255, message = "First name should not be greater than 255")
    @Column(name = "first_Name")
    private String firstName;

    @NotBlank(message = "Last name cannot be null")
    @NotNull(message = "Last name cannot be null")
    @Size(max = 255, message = "Last name should not be greater than 255")
    @Column(name = "last_Name")
    private String lastName;

    private String phone;

    private String location;

    private LocalDateTime created;
}