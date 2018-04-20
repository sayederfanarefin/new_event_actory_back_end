package com.sweetitech.tiger.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.Video;

public interface IVideoService {

	Video addVideo(Video video);
	Page<Video> findAllVideos(int page);
	
	Video findById(Long id) ;
	
	Video updateVideo(Video video);
	
	void deleteVideo(Video video);
	
	Page<Video> findByTitleContaining(String title, int page);
}
