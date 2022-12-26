package com.example.security6testing.api;

import com.example.security6testing.model.request.LoginRequest;
import com.example.security6testing.model.response.LoginResponse;
import com.example.security6testing.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){

        return this.userService.login(loginRequest);
    }

    @GetMapping("/get")
    String login() {
        return "Security work correctly";
    }
}
