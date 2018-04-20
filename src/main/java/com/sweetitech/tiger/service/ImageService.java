package com.sweetitech.tiger.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.repository.ImageRepository;
import com.sweetitech.tiger.service.interfaces.IImageService;


@Service
@Transactional
public class ImageService implements IImageService {

	@Autowired
	ImageRepository imageRepository;
	
	
	@Override
	public Image addImage(Image image) {
		
		Image i =findByUrl(image.getUrl());
		if(i==null) {
			return imageRepository.save(image);
		}
		return i;
	}
	@Override
	public void deleteImage(Image image) {
		
		imageRepository.delete(image);
		
	}
	@Override
	public Image findById(Long id) {
		
		return imageRepository.findById(id);
	}
	@Override
	public Image findByUrl(String url) {
		
		return imageRepository.findByUrl(url);
	}
	@Override
	public Image updateImage(Image image) {
		// TODO Auto-generated method stub
		return imageRepository.save(image);
	}
	
	

}
