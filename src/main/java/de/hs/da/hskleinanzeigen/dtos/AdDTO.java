package de.hs.da.hskleinanzeigen.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdDTO {
    private int id;
    private String type;
    private CategoryDTO category;
    private UserDTO user;
    private String title;
    private String description;
    private int price;
    private String location;
}
