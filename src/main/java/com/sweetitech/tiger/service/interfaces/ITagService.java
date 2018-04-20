package com.sweetitech.tiger.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.Banner;
import com.sweetitech.tiger.model.Tag;
import com.sweetitech.tiger.model.Video;



public interface ITagService {

	
	Tag addTag(Tag tag);
	Tag findById(Long id);
	Tag findByTag(String tag);
	List<Tag> findAll();
	Tag updateTag(Tag tag);
	

	
	void deleteTag(Tag tag);
}
