package de.hs.da.hskleinanzeigen.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data //That bundles the features of @ToString, @EqualsAndHashCode, @Getter/@Setter and @RequiredArgsConstructor
@NoArgsConstructor //Will lead to an empty constructor generation.
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String lastName;

    private String phone;

    private String location;

    private LocalDateTime created;
}