package de.hs.da.hskleinanzeigen.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CreationAdDTO {
    @NotNull(message = "Type cannot be null")
    private String type;

    @NotNull(message = "Category cannot be null")
    private CategoryDTO category;

    @NotNull(message = "User cannot be null")
    private UserDTO user;

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    private int price;

    private String location;
}
