package de.hs.da.hskleinanzeigen.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data //That bundles the features of @ToString, @EqualsAndHashCode, @Getter/@Setter and @RequiredArgsConstructor
@NoArgsConstructor //Will lead to an empty constructor generation.
@AllArgsConstructor
@Table(name = "NOTEPAD")
public class Notepad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User cannot be null")
    @ManyToOne
    private User user;

    @NotNull(message = "Advertisement cannot be null")
    @ManyToOne
    private AD ad;

    private String note;

    @NotNull(message = "Created cannot be null")
    private LocalDateTime created;
}