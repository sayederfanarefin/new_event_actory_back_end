package com.sweetitech.tiger.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.GalleryImage;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.TopUpdate;
import com.sweetitech.tiger.repository.GalleryImageRepository;
import com.sweetitech.tiger.service.interfaces.IGalleryImageService;
import com.sweetitech.tiger.service.interfaces.ITopUpdateService;


@Service
@Transactional
public class GalleryImageService implements IGalleryImageService {

	@Autowired
	GalleryImageRepository galleryImageRepository;
	
	@Autowired
	ITopUpdateService topUpdateService;
	
	
	@Override
	public Page<GalleryImage> findAllGalleryImage(int page) {
		
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
	        return galleryImageRepository.findAll(request);
	}
	
	
	
	
	@Override
	public GalleryImage addGalleryImage(GalleryImage galleryImage) {
		
		topUpdateService.addTopUpdate(new TopUpdate(galleryImage));
		return galleryImageRepository.save(galleryImage);
	}
	
	@Override
	public GalleryImage findById(Long id) {
		
		return galleryImageRepository.findById(id);
	}

	@Override
	public GalleryImage updateGalleryImage(GalleryImage galleryImage) {
		
		return galleryImageRepository.save(galleryImage);
	}




	@Override
	public void deleteGalleryImage(GalleryImage galleryImage) {
		galleryImageRepository.delete(galleryImage);
	}
	
	

}
