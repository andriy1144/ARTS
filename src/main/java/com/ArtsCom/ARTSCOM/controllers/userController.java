package com.ArtsCom.ARTSCOM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class userController {
    @GetMapping("/login")
    public String createUser(){
        return "loginPage";
    }
}
