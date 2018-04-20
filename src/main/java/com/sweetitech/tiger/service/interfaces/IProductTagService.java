package com.sweetitech.tiger.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sweetitech.tiger.model.Video;
import com.sweetitech.tiger.model.ecommerce.Product;
import com.sweetitech.tiger.model.ecommerce.Ptag;



public interface IProductTagService {

	Page<Ptag> findAllByPage(int page);
	Ptag findById(long id);
	Ptag findByTag(String tag);
	
	Ptag updatePtag(Ptag ptag);
	
	void deletePtag(Ptag ptag);
}
