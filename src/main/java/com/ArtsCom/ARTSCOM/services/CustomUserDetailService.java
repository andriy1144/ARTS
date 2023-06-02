package com.ArtsCom.ARTSCOM.services;

import com.ArtsCom.ARTSCOM.models.User;
import com.ArtsCom.ARTSCOM.repos.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByEmail(username);
        if(user !=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    user.getRoleSet().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getAuthority()))
                            .collect(Collectors.toList())
            );
        }else{
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
