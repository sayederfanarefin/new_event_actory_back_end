package com.sweetitech.tiger.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.News;


public interface INewsService {

	News addNews(News news);
	Page<News> findAllNews(int page);
	//void deleteNews(News news);
	News findById(Long id);
	
	News updateNews(News news);
	void deleteNews(News news);
	
	Page<News> findByTitleContaining(String title, int page);
}
