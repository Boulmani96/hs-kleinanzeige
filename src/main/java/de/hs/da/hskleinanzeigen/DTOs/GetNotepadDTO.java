package de.hs.da.hskleinanzeigen.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetNotepadDTO {
    private Integer id;
    private AdDTO advertisement;
    private String note;
}
