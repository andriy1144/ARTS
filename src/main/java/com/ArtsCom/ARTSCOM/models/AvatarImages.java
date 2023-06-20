package com.ArtsCom.ARTSCOM.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AvatarImages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "size")
    private Long size;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] bytes;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "IconImage")
    private User user;
}
