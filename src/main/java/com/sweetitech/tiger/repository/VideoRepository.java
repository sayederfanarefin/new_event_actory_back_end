package com.sweetitech.tiger.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.Video;


@Repository("videoRepository")
public interface VideoRepository extends JpaRepository<Video, Integer> {
	Video findById(long id);
	
	 
	 Page<Video> findByTitleContaining(String title, Pageable pageable);
}