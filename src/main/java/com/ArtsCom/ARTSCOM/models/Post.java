package com.ArtsCom.ARTSCOM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "descr",columnDefinition = "text")
    private String descr;

    @Column(name = "tags")
    private List<String> tags = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "post")
    private List<Image> imagesList = new ArrayList<>();

    @Column(name = "imagePreviewId")
    private Long imagePreviewId;

    @Column(name = "dateOfCreating")
    private LocalDateTime dateOfCreating;

//    public void addTags(String tag){
//        tags.add(tag);
//    }
    public void addImages(Image img){ imagesList.add(img); }

    @PrePersist
    public void init(){
        dateOfCreating = LocalDateTime.now();
    }
}
