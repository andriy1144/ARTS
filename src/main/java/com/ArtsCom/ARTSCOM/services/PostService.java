package com.ArtsCom.ARTSCOM.services;

import com.ArtsCom.ARTSCOM.models.Image;
import com.ArtsCom.ARTSCOM.models.Post;
import com.ArtsCom.ARTSCOM.models.User;
import com.ArtsCom.ARTSCOM.repos.ImageRepo;
import com.ArtsCom.ARTSCOM.repos.PostRepo;
import com.ArtsCom.ARTSCOM.repos.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PostService {
    private final PostRepo postRepo;
    private final ImageRepo imageRepo;
    private final UserRepo userRepo;

    public void savePost(MultipartFile[] list, Post post, String tags, Principal principal) throws IOException{
        post.setUser(getUserByPrincipal(principal));

        for (MultipartFile image : list) {
            if(image.getSize() !=0){
                Image img = toImage(image);
                img.setPost(post);
                post.addImages(img);
            }
        }


        Post postSaved = postRepo.save(post);

        if(postSaved.getImagesList().size() != 0) {
            post.getImagesList().get(0).setPreview(true);

            post.setImagePreviewId(post.getImagesList().get(0).getId());
        }

        log.info("Saving post : Title: {} , images number : {}, user : {}" ,post.getTitle(),post.getImagesList().size(),principal.getName() );

        postRepo.save(post);

    }

    public void EditPost(Post post, String tags, Principal principal,Long id) throws IOException{
        Post post1 = postRepo.findById(id).orElseThrow();

        //Editing simple inputs
        post1.setTitle(post.getTitle());
        post1.setDescr(post.getDescr());
        post1.setTags(new ArrayList<>(Collections.singleton(tags)));

        postRepo.save(post1);
        log.info("Edited post with id : {}" , id);
    }

    private Image toImage(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setBytes(file.getBytes());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setOrgName(file.getOriginalFilename());
        return image;
    }

    public List<Post> getPosts(String title){
        if(title != null) return postRepo.findByTitle(title);
        return postRepo.findAll();
    }

    public User getUserByPrincipal(Principal principal){
        if(principal == null) return new User();
        return userRepo.findUserByEmail(principal.getName());
    }

    public void deletePostById(Long id){
        postRepo.deleteById(id);
        log.info("Post has been deleted with id : {}", id);
    }
}
