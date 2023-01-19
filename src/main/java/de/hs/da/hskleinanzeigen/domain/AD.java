package de.hs.da.hskleinanzeigen.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor //Will lead to an empty constructor generation.
@AllArgsConstructor
@Table(name = "AD")
public class AD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Type cannot be null")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull(message = "Category cannot be null")
    @ManyToOne
    private Category category;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    private User user;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    private int price;

    private String location;

    @NotNull(message = "Created cannot be null")
    private LocalDateTime created;
}

