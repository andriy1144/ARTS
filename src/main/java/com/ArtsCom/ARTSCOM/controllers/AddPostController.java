package com.ArtsCom.ARTSCOM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Controller
@RequestMapping("/addPost")
public class AddPostController {

    @GetMapping("/")
    public String addPostPage(){
        return "addPostPage";
    }

    @PostMapping("/")
    public String addPostPagePOST(@RequestParam(name = "files") MultipartFile[] multipartFiles){
        return "redirect:/";
    }
}
