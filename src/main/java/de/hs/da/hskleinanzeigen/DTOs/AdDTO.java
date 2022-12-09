package de.hs.da.hskleinanzeigen.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
