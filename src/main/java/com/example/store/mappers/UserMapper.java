package com.example.store.mappers;

import com.example.store.dto.response.UserResponseDTO;
import com.example.store.dto.request.UserRequestDTO;
import com.example.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponseDTO toDTO(User user);
    User toEntity(UserRequestDTO userRequestDTO);
}
