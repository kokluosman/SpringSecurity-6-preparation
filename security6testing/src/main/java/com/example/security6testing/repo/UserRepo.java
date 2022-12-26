package com.example.security6testing.repo;

import com.example.security6testing.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findUserByEmail(String email);

}
