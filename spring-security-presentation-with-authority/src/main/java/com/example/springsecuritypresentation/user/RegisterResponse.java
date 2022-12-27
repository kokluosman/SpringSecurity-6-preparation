package com.example.springsecuritypresentation.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {

    private String firstname;
    private String lastname;
    private String email;

}
