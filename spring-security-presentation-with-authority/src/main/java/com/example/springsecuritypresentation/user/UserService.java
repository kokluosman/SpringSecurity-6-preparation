package com.example.springsecuritypresentation.user;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<RegisterResponse> register(RegisterRequest request);

    ResponseEntity<String> adminAuthorityAcceptOnly();

    ResponseEntity<String> userAuthorityAcceptOnly();

    ResponseEntity<String> editorAuthorityAcceptOnly();

    ResponseEntity<String> acceptsAnyAuthority();

}
