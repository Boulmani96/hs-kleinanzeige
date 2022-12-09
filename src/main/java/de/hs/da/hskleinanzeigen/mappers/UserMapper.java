package de.hs.da.hskleinanzeigen.mappers;

import de.hs.da.hskleinanzeigen.DTOs.CreationUserDTO;
import de.hs.da.hskleinanzeigen.DTOs.UserDTO;
import de.hs.da.hskleinanzeigen.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO (User user);

    User userDTOtoUser(UserDTO userDTO);

    CreationUserDTO userToCreationUserDTO(User user);

    User creationUserDTOToUser(CreationUserDTO creationUserDTO);

    List<UserDTO> listUserToListUserDTO(List<User> userList);
}
