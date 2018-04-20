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
import com.sweetitech.tiger.repository.TopUpdateRepository;
import com.sweetitech.tiger.service.interfaces.ITopUpdateService;


@Service
@Transactional
public class TopUpdateService implements ITopUpdateService {

	@Autowired
	TopUpdateRepository topUpdateRepository;
	
	
	@Override
	public Page<TopUpdate> findAllTopUpdate(int page) {
		
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
	        return topUpdateRepository.findAll(request);
	}
	
	
	
	@Override
	public TopUpdate findById(Long id) {
		
		return topUpdateRepository.findById(id);
	}
	
	
	@Override
	public void addTopUpdate(TopUpdate topUpdate) {
		topUpdateRepository.save(topUpdate);
		
		
//		List<TopUpdate> allTopUpdates= topUpdateRepository.findAll();
//		if(allTopUpdates.size() > Constants.TOP_UPDATE_TABLE_LIMIT) {
//			for(int i = 0 ; i < allTopUpdates.size() -Constants.TOP_UPDATE_TABLE_LIMIT; i ++) {
//				topUpdateRepository.delete(allTopUpdates.get(i));
//			}
//		}
		
		
	}
	
	
	@Override
	public void deleteTopUpdate(TopUpdate topUpdate) {
		
		topUpdateRepository.delete(topUpdate);
	}



	@Override
	public TopUpdate updateTopUpdate(TopUpdate topUpdate) {
		return topUpdateRepository.save(topUpdate);
	}
	
	
}
