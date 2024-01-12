package de.hs.da.hskleinanzeigen.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class NotepadDTO {
    private Integer id;
    private Integer advertisementId;
    private String note;
}
