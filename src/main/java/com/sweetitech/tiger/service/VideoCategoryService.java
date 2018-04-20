package com.sweetitech.tiger.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.VideoCategory;
import com.sweetitech.tiger.repository.VideoCategoryRepository;
import com.sweetitech.tiger.service.interfaces.IVideoCategoryService;



@Service
@Transactional
public class VideoCategoryService implements IVideoCategoryService {

	@Autowired
	VideoCategoryRepository videoCategoryRepository;

	@Override
	public VideoCategory addVideoCategory(VideoCategory videoCategory) {
		
		return videoCategoryRepository.save(videoCategory);
	}

	@Override
	public VideoCategory findById(Long id) {
		return videoCategoryRepository.findById(id);
	}

	@Override
	public VideoCategory findByName(String name) {
		
		return videoCategoryRepository.findByName(name);
	}

	@Override
	public VideoCategory updateVideoCategory(VideoCategory videoCategory) {
		
		return videoCategoryRepository.save(videoCategory);
	}

	@Override
	public void deleteVideoCategory(VideoCategory videoCategory) {
		 videoCategoryRepository.delete(videoCategory);
		
	}
	
	

}
