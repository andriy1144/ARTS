package com.ArtsCom.ARTSCOM.repos;

import com.ArtsCom.ARTSCOM.models.TokenModel;
import com.ArtsCom.ARTSCOM.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<TokenModel,Long> {
    TokenModel findByConfirmationToken(String confirmationToken);
    TokenModel findByUserId(Long user_id);
}
