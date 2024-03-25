package com.example.springmid.services;


import com.example.springmid.dto.response.UserResponseDTO;
import com.example.springmid.dto.reuest.UserRequestDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO create(UserRequestDTO userRequestDTO);
    UserResponseDTO update(Long id, UserRequestDTO userRequestDTO);
    UserResponseDTO get(Long id);
    List<UserResponseDTO> getAll();
}
