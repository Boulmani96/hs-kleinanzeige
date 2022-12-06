package de.hs.da.hskleinanzeigen.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor //Will lead to an empty constructor generation.
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private Category parent;
}
