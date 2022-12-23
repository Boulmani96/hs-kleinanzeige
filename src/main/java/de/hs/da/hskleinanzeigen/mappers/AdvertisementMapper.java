package de.hs.da.hskleinanzeigen.mappers;

import de.hs.da.hskleinanzeigen.dtos.AdDTO;
import de.hs.da.hskleinanzeigen.dtos.CategoryDTO;
import de.hs.da.hskleinanzeigen.dtos.CreationAdDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {

    AdDTO adToAdDTO(AD ad);

    AD creationAdDTOtoAd(CreationAdDTO creationAdDTO);

    UserDTO userToUserDTO (User user);

    User userDTOtoUser (UserDTO userDTO);

    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOtoCategory(CategoryDTO categoryDTO);

    List<AdDTO> listAdToAdDTO(List<AD> AdList);
}
