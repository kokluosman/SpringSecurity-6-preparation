package com.example.security6testing.service;

import com.example.security6testing.model.request.LoginRequest;
import com.example.security6testing.model.response.LoginResponse;
import com.example.security6testing.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService
{

    private final UserRepo userRepo;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        final var userByEmail = Optional.of(this.userRepo.findUserByEmail(loginRequest.getEmail()));

        final var user =
                userByEmail.orElseThrow(() -> {
                    throw new UsernameNotFoundException(
                            "There is no user with following email address:" + loginRequest.getEmail()
                    );
                });
        return new ResponseEntity<>(
                new LoginResponse(
                        user.getFirstname(),
                        user.getEmail(),
                        user.getLastname())
                ,HttpStatus.OK);
    }
}
