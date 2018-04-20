package com.sweetitech.tiger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.GalleryImage;

@Repository("galleryImageRepository")
public interface GalleryImageRepository extends JpaRepository<GalleryImage, Integer>{
	
	 public List<GalleryImage> findAllByOrderByIdAsc();
	 
	 public GalleryImage findById(Long id);
	 
	 public GalleryImage save(GalleryImage galleryImage);
	
}

