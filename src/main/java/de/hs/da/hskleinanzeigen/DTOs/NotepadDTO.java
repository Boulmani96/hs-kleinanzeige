package de.hs.da.hskleinanzeigen.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotepadDTO {
    private Integer id;
    private Integer advertisementId;
    private String note;
}
