package com.ArtsCom.ARTSCOM.controllers;

import com.ArtsCom.ARTSCOM.models.Post;
import com.ArtsCom.ARTSCOM.repos.PostRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    //Request variables
    private final PostRepo postRepo;

    //Mapping of the post page
    @GetMapping("/{id}")
    public String viewPost(@PathVariable(name = "id") Long ID, Model model){
        Post post = postRepo.findById(ID).orElse(null);
        model.addAttribute("post", post);
        model.addAttribute("images", post != null ? post.getImagesList() : null);

        return "postPage";
    }
}
