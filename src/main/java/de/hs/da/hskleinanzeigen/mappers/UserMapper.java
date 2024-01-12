package de.hs.da.hskleinanzeigen.mappers;

import de.hs.da.hskleinanzeigen.dtos.CreationUserDTO;
import de.hs.da.hskleinanzeigen.dtos.UserDTO;
import de.hs.da.hskleinanzeigen.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO (User user);

    User userDTOtoUser(UserDTO userDTO);

    CreationUserDTO userToCreationUserDTO(User user);

    User creationUserDTOToUser(CreationUserDTO creationUserDTO);

    List<UserDTO> listUserToListUserDTO(List<User> userList);
}
