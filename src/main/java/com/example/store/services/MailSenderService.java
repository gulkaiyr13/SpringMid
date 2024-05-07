package com.example.store.services;


import com.example.store.entities.ConfirmationToken;
import jakarta.mail.MessagingException;

public interface MailSenderService {
    void sendConfirmationEmail(ConfirmationToken confirmationToken, String confirmationUrl) throws MessagingException;
}
