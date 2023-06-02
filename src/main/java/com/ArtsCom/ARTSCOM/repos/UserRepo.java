package com.ArtsCom.ARTSCOM.repos;

import com.ArtsCom.ARTSCOM.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findUserByEmail(String email);
}
