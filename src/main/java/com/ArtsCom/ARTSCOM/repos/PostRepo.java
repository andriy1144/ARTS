package com.ArtsCom.ARTSCOM.repos;


import com.ArtsCom.ARTSCOM.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post> findByTitle(String title);
}
