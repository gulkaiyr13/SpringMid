package com.example.springmid.mappers;

import com.example.springmid.dto.UserDTO;
import com.example.springmid.entities.User;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    User userDtoToUser(UserDTO userDTO);
    UserDTO userToUserDto(User user);
}
