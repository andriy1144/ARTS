package com.ArtsCom.ARTSCOM.controllers;

import com.ArtsCom.ARTSCOM.models.User;
import com.ArtsCom.ARTSCOM.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class userController {
    // Required variables
    private final UserService userService;

    @GetMapping("/login")
    public String createUser(){
        return "loginPage";
    }

    @GetMapping("/registration")
    public String registUser(){
        return "registPage";
    }

    @PostMapping("/registration")
    public String createUserPost(User user , Model model){
        if(!userService.createUser(user)) {
            model.addAttribute("errorMassage", "User with email:" + user.getEmail() + " already exists");
            return "registPage";
        }
        return "redirect:/login";
    };
}
