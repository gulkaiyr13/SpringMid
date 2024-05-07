package com.example.store.controllers;

import com.example.store.dto.response.UserResponseDTO;
import com.example.store.dto.request.UserRequestDTO;
import com.example.store.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(
        name = "Controller of users"
)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(
            summary = "Creating user"
    )
    @SecurityRequirement(name = "JWT")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.create(userRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Updating user"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return new ResponseEntity<>(userService.update(id, userRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Getting user by id"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Getting all users"
    )
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

}
