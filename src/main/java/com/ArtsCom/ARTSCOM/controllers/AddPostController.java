package com.ArtsCom.ARTSCOM.controllers;

import com.ArtsCom.ARTSCOM.models.Post;
import com.ArtsCom.ARTSCOM.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/addPost")
@AllArgsConstructor
public class AddPostController {
    private final PostService addPostService;

        @GetMapping("/")
        public String addPostPage (Model model, Principal principal){
        model.addAttribute("user", addPostService.getUserByPrincipal(principal));
        return "addPostPage";

    }

        @PostMapping("/")
        public String addPostPagePOST (@RequestParam(name = "tags") String TAGS, Post
        post, @RequestParam(name = "files") MultipartFile[]multipartFiles, Principal pr){
            try {
                addPostService.savePost(multipartFiles, post, TAGS, pr);
            }catch (Exception e){
                System.out.println(e);
                return "errorPage";
            }
        return "redirect:/";
    }
}
