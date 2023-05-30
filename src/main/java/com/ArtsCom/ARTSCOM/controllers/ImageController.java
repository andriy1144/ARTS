package com.ArtsCom.ARTSCOM.controllers;

import com.ArtsCom.ARTSCOM.models.Image;
import com.ArtsCom.ARTSCOM.repos.ImageRepo;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@AllArgsConstructor
public class ImageController {

    private final ImageRepo imageRepo;

    @GetMapping("/image/{id}")
    public ResponseEntity<?> imageView(@PathVariable(name = "id") Long id){
        Image image = imageRepo.findById(id).orElse(null);

        return ResponseEntity.ok()
                .header("imageName",image.getName())
                .contentLength(image.getSize())
                .contentType(MediaType.valueOf(image.getContentType()))
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
