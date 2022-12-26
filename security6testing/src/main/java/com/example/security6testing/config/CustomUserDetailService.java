package com.example.security6testing.config;

import com.example.security6testing.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.example.security6testing.model.User user = this.userRepo.findUserByEmail(email);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException(
                    "There is no user with following email address:"+email
            );
        }
        return new MyUserDetails(user);
    }
}
