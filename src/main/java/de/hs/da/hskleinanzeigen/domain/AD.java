package de.hs.da.hskleinanzeigen.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor //Will lead to an empty constructor generation.
@Table(name = "AD")
public class AD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    private int price;

    private String location;

    private LocalDateTime created;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;
}
