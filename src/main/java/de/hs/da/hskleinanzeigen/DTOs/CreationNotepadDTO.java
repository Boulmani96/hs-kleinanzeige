package de.hs.da.hskleinanzeigen.DTOs;

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
