package com.ArtsCom.ARTSCOM.controllers;

import com.ArtsCom.ARTSCOM.models.Post;
import com.ArtsCom.ARTSCOM.repos.PostRepo;
import com.ArtsCom.ARTSCOM.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    //Request variables
    private final PostRepo postRepo;
    private final PostService postService;

    //Mapping of the post page
    @GetMapping("/{id}")
    public String viewPost(@PathVariable(name = "id") Long ID, Model model, Principal principal){
        Post post = postRepo.findById(ID).orElse(null);
        model.addAttribute("post", post);
        model.addAttribute("user",postService.getUserByPrincipal(principal));
        model.addAttribute("images", post != null ? post.getImagesList() : null);

        return "postPage";
    }
    @GetMapping("/{id}/delete")
    public String deletePost(@PathVariable(name = "id") Long ID,Principal principal,Model model){
        model.addAttribute("user",postService.getUserByPrincipal(principal));
        postService.deletePostById(ID);
        return "redirect:/home";
    }

    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable(name = "id") Long ID,Principal principal,Model model){
        model.addAttribute("user",postService.getUserByPrincipal(principal));
        Post post = postRepo.findById(ID).orElseThrow();

        model.addAttribute("post",post);

        return "editPage";
    }

    @PostMapping("/{id}/edit")
    public String editPostPost(@RequestParam(name = "tags") String TAGS,
                               Post post,
                               Principal pr,
                               @PathVariable(name = "id") Long id) throws IOException {
        postService.EditPost(post,TAGS,pr,id);
        return "redirect:/home";
    }
}
