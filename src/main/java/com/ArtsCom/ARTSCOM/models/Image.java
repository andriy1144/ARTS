package com.ArtsCom.ARTSCOM.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "orgName")
    private String orgName;

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "size")
    private Long size;

    @Column(name = "isPreview")
    private boolean isPreview;

    @Column(name = "bytes",columnDefinition = "LONGBLOB")
    private byte[] bytes;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REFRESH)
    private Post post;
}
