package de.hs.da.hskleinanzeigen.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data //That bundles the features of @ToString, @EqualsAndHashCode, @Getter/@Setter and @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "NOTEPAD")
public class Notepad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private AD ad;

    private String note;

    private LocalDateTime created;
}