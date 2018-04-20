package com.sweetitech.tiger.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.model.News;
import com.sweetitech.tiger.model.TopUpdate;
import com.sweetitech.tiger.repository.NewsRepository;
import com.sweetitech.tiger.service.interfaces.INewsService;
import com.sweetitech.tiger.service.interfaces.ITopUpdateService;
import com.sweetitech.tiger.config.Constants;


@Service
@Transactional
public class NewsService implements INewsService {

	@Autowired
	NewsRepository newsRepository;
	
	@Autowired
	ITopUpdateService topUpdateService;
	
	
	@Override
	public News addNews(News news) {
		// TODO Auto-generated method stub
		
		topUpdateService.addTopUpdate(new TopUpdate(news));
		 return newsRepository.save(news);
		
	}
	@Override
	public Page<News> findAllNews(int page) {
		
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
	        return newsRepository.findAll(request);
	}
	@Override
	public News findById(Long id) {
		
		return newsRepository.findById(id);
	}
	@Override
	public News updateNews(News news) {
		return newsRepository.save(news);
		
	}
	@Override
	public Page<News> findByTitleContaining(String title, int page) {
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		return newsRepository.findByTitleContaining(title, request);
	}
	@Override
	public void deleteNews(News news) {
		
		newsRepository.delete(news);
	}
	
	

}
