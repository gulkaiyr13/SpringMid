package com.example.store.repositories;

import com.example.store.entities.ConfirmationToken;
import com.example.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByUser(User user);

    Optional<ConfirmationToken> findByToken(String token);
}
