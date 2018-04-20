package com.sweetitech.tiger.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.TopUpdate;
import com.sweetitech.tiger.model.Video;
import com.sweetitech.tiger.repository.VideoRepository;
import com.sweetitech.tiger.service.interfaces.ITopUpdateService;
import com.sweetitech.tiger.service.interfaces.IVideoService;

@Service
@Transactional
public class VideoService implements IVideoService {

	private static final int PAGE_SIZE = 2;
	@Autowired
	VideoRepository videoRepository;
	
	@Autowired
	ITopUpdateService topUpdateService;
	
	@Override
	public Video addVideo(Video video) {
		
		topUpdateService.addTopUpdate(new TopUpdate(video));
		return videoRepository.save(video);
	}

	@Override
	public Page<Video> findAllVideos(int page) {
		
		PageRequest request =
	            new PageRequest(page, PAGE_SIZE, Sort.Direction.DESC, "id");
	        return videoRepository.findAll(request);
	}
	
	
	@Override
	public Video findById(Long id) {
		
		return videoRepository.findById(id);
	}

	@Override
	public Video updateVideo(Video video) {
		return videoRepository.save(video);
		
	}

	@Override
	public void deleteVideo(Video video) {
		videoRepository.delete(video);
		
	}

	@Override
	public Page<Video> findByTitleContaining(String title, int page) {
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return videoRepository.findByTitleContaining(title, request);
	}
}
