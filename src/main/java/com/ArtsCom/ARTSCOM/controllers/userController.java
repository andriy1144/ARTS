package com.ArtsCom.ARTSCOM.controllers;

import com.ArtsCom.ARTSCOM.models.User;
import com.ArtsCom.ARTSCOM.repos.UserRepo;
import com.ArtsCom.ARTSCOM.services.PostService;
import com.ArtsCom.ARTSCOM.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class userController {
    // Required variables
    private final UserService userService;
    private final UserRepo userRepo;
    private final PostService postService;

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
    }

    @GetMapping("/profile/{id}")
    public String homePage(Model model, Principal principal, @PathVariable(name = "id") Long id){
        model.addAttribute("user",postService.getUserByPrincipal(principal));
        User user = userRepo.findById(id).orElse(null);

        if(user != null){
            model.addAttribute("userInfo",user);
            model.addAttribute("Posts",user.getPostList());
        }

        return "homePage";
    }
}
