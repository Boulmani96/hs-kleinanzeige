package de.hs.da.hskleinanzeigen.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetNotepadDTO {
    private Integer id;
    private AdDTO advertisement;
    private String note;
}
