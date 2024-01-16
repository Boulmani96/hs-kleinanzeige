package de.hs.da.hskleinanzeigen.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "AD")
public class AD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    private String title;

    private String description;

    private int price;

    private String location;

    private LocalDateTime created;
}

