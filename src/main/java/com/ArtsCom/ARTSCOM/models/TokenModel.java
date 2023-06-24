package com.ArtsCom.ARTSCOM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class TokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String confirmationToken;


    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    private User user;

    public TokenModel(){

    }

    public TokenModel(User user){
        this.user = user;
        confirmationToken = UUID.randomUUID().toString();
    }

}
