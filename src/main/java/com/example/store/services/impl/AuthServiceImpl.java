package com.example.store.services.impl;

import com.example.store.dto.request.AuthRequestDTO;
import com.example.store.dto.request.AuthResponseDTO;
import com.example.store.dto.request.RefreshTokenRequestDTO;
import com.example.store.dto.request.UserRequestDTO;
import com.example.store.entities.ConfirmationToken;
import com.example.store.entities.RefreshToken;
import com.example.store.entities.User;
import com.example.store.enums.Role;
import com.example.store.enums.Status;
import com.example.store.exceptions.GeneralException;
import com.example.store.mappers.UserMapper;
import com.example.store.repositories.ConfirmationTokenRepository;
import com.example.store.repositories.UserRepository;
import com.example.store.services.AuthService;
import com.example.store.services.MailSenderService;
import com.example.store.services.RefreshTokenService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final MailSenderService emailService;

    @Override
    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return AuthResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                    .refreshToken(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @Override
    public AuthResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.GenerateToken(user.getUsername());
                    return AuthResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

    @Override
    public ResponseEntity<?> signUp(UserRequestDTO requestDTO, HttpServletRequest servletRequest) throws MessagingException {
        if (userRepository.existsByUsername(requestDTO.getUsername())) {
            throw new GeneralException("Username already exists");
        }

        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new GeneralException("Email already exists");
        }
        User user = userMapper.toEntity(requestDTO);
        user.setRole(Role.USER);
        user.setStatus(Status.NOT_VERIFIED);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        sendConfirmationToken(user, servletRequest);
        return new ResponseEntity<>("Successfully signed up! Email verification link was sent to your email.", HttpStatus.CREATED);
    }

    @Override
    public String verifyEmail(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new GeneralException("Such token does not exist"));

        if (isTokenExpired(confirmationToken)) {
            throw new GeneralException("Token is expired");
        }
        User user = confirmationToken.getUser();
        user.setStatus(Status.VERIFIED);
        userRepository.save(user);

        confirmationTokenRepository.delete(confirmationToken);

        return "You successfully verified your email!";
    }

    public void sendConfirmationToken(User user, HttpServletRequest request) throws MessagingException {
        ConfirmationToken token = createConfirmationToken(user);
        String confirmationUrl = getConfirmationUrl(request, token.getToken());

        emailService.sendConfirmationEmail(token, confirmationUrl);
    }

    private ConfirmationToken createConfirmationToken(User user) {
        String randomString = UUID.randomUUID().toString();

        Optional<ConfirmationToken> token = confirmationTokenRepository.findByUser(user);
        if (token.isPresent()) {
            token.get().setToken(randomString);
            token.get().setDates(LocalDateTime.now());
            return confirmationTokenRepository.save(token.get());
        } else {
            ConfirmationToken newToken = new ConfirmationToken(randomString, user);
            return confirmationTokenRepository.save(newToken);
        }
    }

    private String getConfirmationUrl(HttpServletRequest servletRequest, String token) {
        return "http://" + servletRequest.getServerName() + ":"
                + servletRequest.getServerPort() + "/api/v1/verify?token=" + token;
    }

    private boolean isTokenExpired(ConfirmationToken token) {
        LocalDateTime currentDate = LocalDateTime.now();
        return currentDate.isAfter(token.getExpiredAt());
    }
}
