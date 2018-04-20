package com.sweetitech.tiger.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.GalleryImage;
import com.sweetitech.tiger.model.Tag;

@Repository("tagRepository")
public interface TagRepository extends JpaRepository<Tag, Integer>{
	
	 public Tag findByTag(String tag);
	 public Tag findByGalleryImages(List<GalleryImage> galleryImages);
	 public Tag findById(long id);
}

