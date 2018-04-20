package com.sweetitech.tiger.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetitech.tiger.config.Constants;
import com.sweetitech.tiger.model.ecommerce.ProductGroup;
import com.sweetitech.tiger.repository.ecommerce.GroupRepository;
import com.sweetitech.tiger.service.interfaces.IGroupService;


@Service
@Transactional
public class GroupService implements IGroupService {

	@Autowired
	private GroupRepository groupRepository;
	
	@Override
	public ProductGroup addProductGroup(ProductGroup productGroup) {
		return groupRepository.save(productGroup);
	}

	@Override
	public Page<ProductGroup> findAllProductGroup(int page) {
		PageRequest request =
	            new PageRequest(page, Constants.PAGE_SIZE, Sort.Direction.DESC, "id");
		
	        return groupRepository.findAll(request);
		
	}

	@Override
	public ProductGroup findById(Long id) {
		
		return groupRepository.findById(id);
	}

	@Override
	public ProductGroup updateProductGroup(ProductGroup productGroup) {
		
		return groupRepository.save(productGroup);
	}

	@Override
	public void deleteProductGroup(ProductGroup productGroup) {
		
		groupRepository.delete(productGroup);
	}

	

}
