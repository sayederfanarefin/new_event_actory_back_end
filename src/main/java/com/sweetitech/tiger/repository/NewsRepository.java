package com.sweetitech.tiger.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sweetitech.tiger.model.News;

@Repository("newsRepository")
public interface NewsRepository extends JpaRepository<News, Integer>{
	
	 public List<News> findAllByOrderByIdAsc();
	 
	 public News findById(Long id);
	 
	 public News save(News news);
	
	 
//	 @Query("Select c from News c where c.title like:place")
//     List<News> findByPlaceContaining(@Param("title")String title);
	 
	 Page<News> findByTitleContaining(String title, Pageable pageable);
}

