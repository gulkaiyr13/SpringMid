package com.example.store.services;


import com.example.store.dto.response.UserResponseDTO;
import com.example.store.dto.request.UserRequestDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO create(UserRequestDTO userRequestDTO);
    UserResponseDTO update(Long id, UserRequestDTO userRequestDTO);
    UserResponseDTO get(Long id);
    List<UserResponseDTO> getAll();
}
