package com.example.springsecuritypresentation.user;

import com.example.springsecuritypresentation.core.exceptions.BusinessException;
import com.example.springsecuritypresentation.outservices.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final EmailVerificationService emailVerificationService;


    @Override
    public ResponseEntity<RegisterResponse> register(RegisterRequest request) {
        checkEmail(request.getEmail());
        emailVerificationService.emailVerification(request.getEmail());
        User user =
                User.builder()
                        .id(0)
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .authority(request.getAuthority())
                        .build();
        RegisterResponse registerResponse =
                RegisterResponse.builder()
                        .email(user.getEmail())
                        .lastname(user.getLastname())
                        .firstname(user.getFirstname())
                        .build();

        this.userDao.save(user);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> adminAuthorityAcceptOnly() {
        return new ResponseEntity<>("Admin authorization has been succeed", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> userAuthorityAcceptOnly() {
        return new ResponseEntity<>("User authorization has been succeed", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> editorAuthorityAcceptOnly() {
        return new ResponseEntity<>("Editor authorization has been succeed", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> acceptsAnyAuthority() {
        return new ResponseEntity<>("Accepts any of authorities", HttpStatus.OK);
    }

    private void checkEmail(String email){
        if (this.userDao.existsByEmail(email)){
            throw new BusinessException("This is email is already using");
        }
    }
}
