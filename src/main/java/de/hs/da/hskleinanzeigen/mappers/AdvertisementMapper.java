package de.hs.da.hskleinanzeigen.mappers;

import de.hs.da.hskleinanzeigen.DTOs.AdDTO;
import de.hs.da.hskleinanzeigen.DTOs.CategoryDTO;
import de.hs.da.hskleinanzeigen.DTOs.CreationAdDTO;
import de.hs.da.hskleinanzeigen.DTOs.UserDTO;
import de.hs.da.hskleinanzeigen.domain.AD;
import de.hs.da.hskleinanzeigen.domain.Category;
import de.hs.da.hskleinanzeigen.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertisementMapper {

    AdDTO adToAdDTO(AD ad);

    AD adDTOtoAD(AdDTO adDTO);

    CreationAdDTO CreationAdDTOtoAd(AD ad);

    AD creationAdDTOtoAd(CreationAdDTO creationAdDTO);

    UserDTO userToUserDTO (User user);

    User userDTOtoUser (UserDTO userDTO);

    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOtoCategory(CategoryDTO categoryDTO);

    List<AdDTO> listAdToAdDTO(List<AD> AdList);
}
