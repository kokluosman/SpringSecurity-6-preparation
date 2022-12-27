package com.example.springsecuritypresentation.outservices;

public interface EmailVerificationService {
    boolean emailVerification(String email);
}