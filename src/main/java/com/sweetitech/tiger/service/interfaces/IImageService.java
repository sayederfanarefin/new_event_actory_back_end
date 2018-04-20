package com.sweetitech.tiger.service.interfaces;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.Video;


public interface IImageService {
	
	Image addImage(Image image);
	void deleteImage(Image image);
	Image findById(Long id);
	Image findByUrl(String url);
	
	Image updateImage(Image image);
	
}
