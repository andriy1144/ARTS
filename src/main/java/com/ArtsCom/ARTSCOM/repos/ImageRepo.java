package com.ArtsCom.ARTSCOM.repos;

import com.ArtsCom.ARTSCOM.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image,Long> {
}
