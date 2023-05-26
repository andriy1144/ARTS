package com.ArtsCom.ARTSCOM.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {

    //Controller of the mainPage(greetingPage)
    @GetMapping("/")
    public String mainPage(@RequestParam(name = "SearchWord" ,required = false) String NAME){
        return "mainPage";
    }
}
