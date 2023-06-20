package com.ArtsCom.ARTSCOM.services;

import com.ArtsCom.ARTSCOM.models.AvatarImages;
import com.ArtsCom.ARTSCOM.models.Image;
import com.ArtsCom.ARTSCOM.models.User;
import com.ArtsCom.ARTSCOM.models.enums.Role;
import com.ArtsCom.ARTSCOM.repos.AvatartImageRepo;
import com.ArtsCom.ARTSCOM.repos.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    // Required variables
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AvatartImageRepo avatartImageRepo;

    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepo.findUserByEmail(email) !=null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoleSet().add(Role.ROLE_USER);
        user.setIconImage(null);
        userRepo.save(user);
        log.info("Creating new user: {}, date of creating: {}" ,user.getEmail(),user.getDateOfCreate());
        return true;
    }

    private AvatarImages toImage(MultipartFile file) throws IOException {
        AvatarImages image = new AvatarImages();
        image.setName(file.getName());
        image.setBytes(file.getBytes());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        avatartImageRepo.save(image);
        return image;
    }

    public void addIconImage(User user, MultipartFile file) throws IOException{
        AvatarImages image = toImage(file);
        user.setIconImage(image);
        userRepo.save(user);
        log.info("Avatar is set");
    }
}
