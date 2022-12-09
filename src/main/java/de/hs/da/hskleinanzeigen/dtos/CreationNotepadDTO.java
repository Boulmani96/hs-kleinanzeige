package de.hs.da.hskleinanzeigen.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreationNotepadDTO {
    private Integer advertisementId;
    private String note;
}
