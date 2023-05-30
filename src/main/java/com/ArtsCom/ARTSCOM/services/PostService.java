package com.ArtsCom.ARTSCOM.services;

import com.ArtsCom.ARTSCOM.models.Image;
import com.ArtsCom.ARTSCOM.models.Post;
import com.ArtsCom.ARTSCOM.repos.ImageRepo;
import com.ArtsCom.ARTSCOM.repos.PostRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PostService {
    private final PostRepo postRepo;
    private final ImageRepo imageRepo;

    public void savePost(MultipartFile[] list, Post post, String tags) throws IOException{
        for (MultipartFile image : list) {
            if(image.getSize() !=0){
                Image img = toImage(image);
                img.setPost(post);
                post.addImages(img);
            }
        }


        Post postSaved = postRepo.save(post);

        post.getImagesList().get(0).setPreview(true);

        post.setImagePreviewId(post.getImagesList().get(0).getId());

        log.info("Saving post : Title: {} , images number : {}" ,post.getTitle(),post.getImagesList().size() );
        System.out.println(post.getTags());
        postRepo.save(post);

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
        if(title != null) postRepo.findPostByTitle(title);
        return postRepo.findAll();
    }
}
