package de.hs.da.hskleinanzeigen.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreationCategoryDTO {
    private String name;
    private int parentId;
}
