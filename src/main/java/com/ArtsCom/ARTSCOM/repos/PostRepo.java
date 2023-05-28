package com.ArtsCom.ARTSCOM.repos;


import com.ArtsCom.ARTSCOM.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {

}
