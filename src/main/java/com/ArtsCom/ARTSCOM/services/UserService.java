package com.ArtsCom.ARTSCOM.services;

import com.ArtsCom.ARTSCOM.models.User;
import com.ArtsCom.ARTSCOM.models.enums.Role;
import com.ArtsCom.ARTSCOM.repos.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    // Required variables
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepo.findUserByEmail(email) !=null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoleSet().add(Role.ROLE_USER);
        userRepo.save(user);
        log.info("Creating new user: {}, date of creating: {}" ,user.getEmail(),user.getDateOfCreate());
        return true;
    }
}
