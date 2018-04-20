package com.sweetitech.tiger.service.interfaces;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.TopUpdate;
import com.sweetitech.tiger.model.Video;


public interface ITopUpdateService {
	
	void addTopUpdate(TopUpdate topUpdate);
	void deleteTopUpdate(TopUpdate topUpdate);
	TopUpdate findById(Long id);
	Page<TopUpdate> findAllTopUpdate(int page);
	
	TopUpdate updateTopUpdate(TopUpdate topUpdate);
	
}
