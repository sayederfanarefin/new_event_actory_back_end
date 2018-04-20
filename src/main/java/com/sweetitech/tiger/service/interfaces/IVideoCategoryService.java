package com.sweetitech.tiger.service.interfaces;

import com.sweetitech.tiger.model.Video;
import com.sweetitech.tiger.model.VideoCategory;


public interface IVideoCategoryService {
	
	VideoCategory addVideoCategory(VideoCategory videoCategory);
	VideoCategory findById(Long id);
	VideoCategory findByName(String name);
	
	VideoCategory updateVideoCategory(VideoCategory videoCategory);
	
	void deleteVideoCategory(VideoCategory videoCategory);
}
