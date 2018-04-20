package com.sweetitech.tiger.service.interfaces;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.GalleryImage;
import com.sweetitech.tiger.model.Video;



public interface IGalleryImageService {

	GalleryImage addGalleryImage(GalleryImage galleryImage);
	Page<GalleryImage> findAllGalleryImage(int page);
	//void deleteNews(News news);
	GalleryImage findById(Long id);
	
	GalleryImage updateGalleryImage(GalleryImage galleryImage);
	
	void deleteGalleryImage(GalleryImage galleryImage);
}
