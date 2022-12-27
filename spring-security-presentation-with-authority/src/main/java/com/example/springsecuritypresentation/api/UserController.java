package com.example.springsecuritypresentation.api;

import com.example.springsecuritypresentation.user.RegisterRequest;
import com.example.springsecuritypresentation.user.RegisterResponse;
import com.example.springsecuritypresentation.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping(path = "/register")
    ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request){
        return this.userService.register(request);
    }

    @GetMapping(path = "/admin-login")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<String> adminAuthorityAcceptOnly(){
        return this.userService.adminAuthorityAcceptOnly();
    }
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("/user-login")
    public ResponseEntity<String> userLogin() {
        return this.userService.userAuthorityAcceptOnly();
    }

    @PreAuthorize("hasAnyAuthority('EDITOR','ADMIN')")
    @GetMapping("/editor-login")
    public ResponseEntity<String> editorLogin() {
        return this.userService.editorAuthorityAcceptOnly();
    }

    @GetMapping("/any-of-request-login")
    public ResponseEntity<String> anyOfAuthorityLogin() {
        return this.userService.acceptsAnyAuthority();
    }
}
