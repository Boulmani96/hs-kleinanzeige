package de.hs.da.hskleinanzeigen.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CreationNotepadDTO {
    @NotBlank(message = "Advertisement ID cannot be empty")
    @NotNull(message = "Advertisement ID cannot be null")
    private Integer advertisementId;

    @NotBlank(message = "Note cannot be empty")
    @NotNull(message = "Note cannot be null")
    private String note;
}
