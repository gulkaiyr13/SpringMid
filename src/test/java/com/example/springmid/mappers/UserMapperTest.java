package com.example.springmid.mappers;

import com.example.springmid.dto.UserDTO;
import com.example.springmid.entities.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    @Test
    public void testUserToUserDTO() {
        User user = new User(userId);
        user.setId(1L);
        user.setUsername("gulkaiyr");
        user.setEmail("gulkaiyr@gmail.com");

        UserDTO userDTO = userMapper.userToUserDTO(user);

        assertNotNull(userDTO);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getEmail(), userDTO.getEmail());
    }

    @Test
    public void testUserDTOToUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("gulkaiyr");
        userDTO.setEmail("gulkaiyr@gmail.com");

        User user = userMapper.userDTOToUser(userDTO);

        assertNotNull(user);
        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getEmail(), user.getEmail());
    }
}
