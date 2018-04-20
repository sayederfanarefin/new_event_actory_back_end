package com.sweetitech.tiger.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sweetitech.tiger.model.Image;
import com.sweetitech.tiger.model.VideoCategory;


public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Long> {

    VideoCategory findById (long id);
    VideoCategory findByName (String name);

}
