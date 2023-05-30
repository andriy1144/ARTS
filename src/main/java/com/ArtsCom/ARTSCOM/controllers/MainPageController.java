package com.ArtsCom.ARTSCOM.controllers;


import com.ArtsCom.ARTSCOM.repos.PostRepo;
import com.ArtsCom.ARTSCOM.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MainPageController {
    // Required variables
    private final PostService postService;

    //Controller of the mainPage(greetingPage)
    @GetMapping("/")
    public String mainPage(@RequestParam(name = "SearchWord" ,required = false) String NAME, Model model){
        model.addAttribute("Posts",postService.getPosts(NAME));
        model.addAttribute("user",true);
        return "mainPage";
    }
}
