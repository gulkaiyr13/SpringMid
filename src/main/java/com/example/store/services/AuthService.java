package com.example.store.services;

import com.example.store.dto.request.AuthRequestDTO;
import com.example.store.dto.request.AuthResponseDTO;
import com.example.store.dto.request.RefreshTokenRequestDTO;
import com.example.store.dto.request.UserRequestDTO;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO);

    AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    ResponseEntity<?> signUp(UserRequestDTO requestDTO, HttpServletRequest servletRequest) throws MessagingException;

    String verifyEmail(String token);
}
