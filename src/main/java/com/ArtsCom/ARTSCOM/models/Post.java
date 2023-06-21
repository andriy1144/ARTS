package com.ArtsCom.ARTSCOM.models;

import jakarta.persistence.*;
import lombok.Data;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @Column(name = "imagePreviewId")
    private Long imagePreviewId;

    @Column(name = "dateOfCreating")
    private String dateOfCreating;

    public void addImages(Image img){ imagesList.add(img); }

    @PrePersist
    private void init(){
        LocalDateTime l = LocalDateTime.now();
        dateOfCreating = l.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }
}
