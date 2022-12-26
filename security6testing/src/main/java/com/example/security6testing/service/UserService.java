package com.example.security6testing.service;

import com.example.security6testing.model.request.LoginRequest;
import com.example.security6testing.model.response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<LoginResponse> login(LoginRequest loginRequest);

}
