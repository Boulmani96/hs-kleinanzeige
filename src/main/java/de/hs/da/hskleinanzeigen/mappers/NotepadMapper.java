package de.hs.da.hskleinanzeigen.mappers;

import de.hs.da.hskleinanzeigen.dtos.*;
import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Notepad;
import de.hs.da.hskleinanzeigen.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotepadMapper {

    @Mapping(target = "id", source = "advertisementId")
    Notepad notepadDTOtoNotepad(NotepadDTO notepadDTO);

    @Mapping(target = "advertisementId", source = "id")
    NotepadDTO notepadToNotepadDTO(Notepad notepad);

    @Mapping(target = "id", source = "advertisementId")
    Notepad creationNotepadDTOtoNotepad(CreationNotepadDTO creationNotepadDTO);

   // @Mapping(target = "advertisement", source = "ad")
    List<GetNotepadDTO> notepadListToGetNotepadDTOList(List<Notepad> notepadList);

    @Mapping(target = "advertisement", source = "ad")
    GetNotepadDTO notepadToGetNotepadDTO(Notepad notepad);

    UserDTO userToUserDTO (User user);

    User userDTOtoUser (UserDTO userDTO);

    AdDTO adToAdDTO (AD ad);

    AD adDTOtoAd (AdDTO adDTO);
}
