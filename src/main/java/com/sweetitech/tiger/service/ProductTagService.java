package com.sweetitech.tiger.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.ecommerce.Ptag;
import com.sweetitech.tiger.repository.ecommerce.PTagRepository;
import com.sweetitech.tiger.service.interfaces.IProductTagService;


@Service
@Transactional
public class ProductTagService implements IProductTagService {

	@Autowired
	PTagRepository pTagRepository;

	@Override
	public Page<Ptag> findAllByPage(int page) {
		
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
		return pTagRepository.findAll(request);
	}

	@Override
	public Ptag findById(long id) {
		
		return pTagRepository.findById(id);
	}

	@Override
	public Ptag findByTag(String tag) {
		return  pTagRepository.findByTag(tag);
	}

	@Override
	public Ptag updatePtag(Ptag ptag) {
		
		return pTagRepository.save(ptag);
	}

	@Override
	public void deletePtag(Ptag ptag) {
		pTagRepository.delete(ptag);
		
	}


}
