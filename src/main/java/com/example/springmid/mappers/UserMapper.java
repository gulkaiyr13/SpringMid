package com.example.springmid.mappers;

import com.example.springmid.dto.response.UserResponseDTO;
import com.example.springmid.dto.reuest.UserRequestDTO;
import com.example.springmid.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toDTO(User user);
    User toEntity(UserRequestDTO userRequestDTO);
}
