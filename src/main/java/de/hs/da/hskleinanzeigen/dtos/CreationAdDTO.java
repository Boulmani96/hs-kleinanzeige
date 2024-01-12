package de.hs.da.hskleinanzeigen.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
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

    private Integer price;

    private String location;
}
