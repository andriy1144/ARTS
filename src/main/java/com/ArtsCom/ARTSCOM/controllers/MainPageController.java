package com.ArtsCom.ARTSCOM.controllers;



import com.ArtsCom.ARTSCOM.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainPageController {
    // Required variables
    private final PostService postService;

    //Controller of the mainPage(greetingPage)
    @GetMapping("/")
    public String mainPage(@RequestParam(name = "title" ,required = false) String title, Model model, Principal principal){
        System.out.println(title);
        model.addAttribute("Posts",postService.getPosts(title));
        model.addAttribute("user",postService.getUserByPrincipal(principal));

        return "mainPage";
    }
}
